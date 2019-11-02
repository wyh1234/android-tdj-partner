package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.CommonFollowUpAdapter;
import com.tdjpartner.adapter.SettingPersonAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.model.SettingPerson;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.SettingPersonPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingPersonActivity extends BaseActivity<SettingPersonPresenter>  implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.rl_xd)
    RelativeLayout rl_xd;
    @BindView(R.id.rl_bf)
    RelativeLayout rl_bf;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_num1)
    TextView tv_num1;

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.view)
    View view2;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.search_text)
    EditText search_text;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<SettingPerson.ObjBean.ListBean> data=new ArrayList<>();
    private SettingPersonAdapter settingPersonAdapter;
    private int type=1;
    private int pn=1;
    private String keyword="";
    @OnClick({R.id.rl_xd,R.id.rl_bf,R.id.btn_back,R.id.tv_list_type})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_xd:
                tv_num.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv_num1.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                tv.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv1.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                type=1;
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
                break;
            case R.id.rl_bf:
                tv_num.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                tv_num1.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                tv1.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                view2.setVisibility(View.GONE);
                view1.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
                type=2;
                break;
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_list_type:
                keyword=search_text.getText().toString();
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
                break;
        }
    }
    @Override
    protected SettingPersonPresenter loadPresenter() {
        return new SettingPersonPresenter();
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        settingPersonAdapter=new SettingPersonAdapter(R.layout.setting_person_item_layout,data);
        settingPersonAdapter.setOnItemClickListener(this);
        recyclerView_list.setAdapter(settingPersonAdapter);
        settingPersonAdapter.setOnLoadMoreListener(this,recyclerView_list);
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.setting_person_layout;
    }

    @Override
    public void onRefresh() {
        stop();
        pn=1;
        getData(pn);


    }
    public View getStateViewRoot() {
        return recyclerView_list;
    }
    public void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("type",type);
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
        map.put("pn", pn);
        map.put("ps", 10);
        map.put("keyword", keyword);
        mPresenter.managerList(map);
    }
    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);

        }
        if (settingPersonAdapter.isLoadMoreEnable()){
            settingPersonAdapter.loadMoreComplete();
        }
    }


    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(false);
        getData(++pn);
    }
    public void managerList_failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            mStateView.showEmpty();//显示重试的布局
        }
    }
    public void managerList_Success(SettingPerson settingPerson) {


        tv_num.setText(settingPerson.getObj().getNewCustomerNum()+"");
        tv_num1.setText(settingPerson.getObj().getOldCustomerNum()+"");
        settingPersonAdapter.setType(type);
        if (swipeRefreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
                settingPersonAdapter.notifyDataSetChanged();
            }
        }
        stop();

        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(settingPerson.getObj().getList())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(settingPerson.getObj().getList())) {
            //已经获取数据
            if (pn!=1){
                settingPersonAdapter.loadMoreEnd();
            }
            return;
        }
        data.addAll(settingPerson.getObj().getList());
        settingPersonAdapter.setNewData(data);
        settingPersonAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,SelectPersonActivity.class);
        intent.putExtra("ListBean",data.get(i));
        startActivity(intent);
    }
}
