package com.tdjpartner.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ClientListAdapter;
import com.tdjpartner.adapter.DropOutingAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.mvp.presenter.DropOutingPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
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

public class DropOutingActivity extends BaseActivity<DropOutingPresenter>  implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener
,BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemChildClickListener{
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

    @BindView(R.id.view)
    View view2;
    @BindView(R.id.view1)
    View view1;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private DropOutingAdapter dropOutingAdapter;
    private List<DropOuting.ObjBean> dropOutingList=new ArrayList<>();
    @BindView(R.id.btn_back)
    ImageView btn_back;
    public int pageNo = 1;//翻页计数器
    private String type="order";
    @OnClick({R.id.rl_xd,R.id.rl_bf,R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_xd:
                tv_num.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv_num1.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                tv.setTextColor(GeneralUtils.getColor(this,R.color.view_bg1));
                tv1.setTextColor(GeneralUtils.getColor(this,R.color.gray_6c));
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                type="order";
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
                type="call";
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected DropOutingPresenter loadPresenter() {
        return new DropOutingPresenter();
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
        dropOutingAdapter=new DropOutingAdapter(R.layout.dropouting_item_layout,dropOutingList);
        recyclerView_list.setAdapter(dropOutingAdapter);
        dropOutingAdapter.setType(type);
        dropOutingAdapter.setOnItemChildClickListener(this);
        dropOutingAdapter.setOnItemClickListener(this);
        dropOutingAdapter.setOnLoadMoreListener(this,recyclerView_list);
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dropouring_layout;
    }

    public void onRefresh() {
        pageNo=1;
        getData(pageNo);
    }

    private void getData(int pageNo) {
        Map<String,Object> map=new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("type", type);
        map.put("pn", pageNo);
        map.put("ps", 10);
        mPresenter.downList(map);

    }


    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);

        }
        if (dropOutingAdapter.isLoadMoreEnable()){
            dropOutingAdapter.loadMoreComplete();
        }
    }

    public View getStateViewRoot() {
        return recyclerView_list;
    }
    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Override
    public void onLoadMoreRequested() {
        LogUtils.e(pageNo);
        swipeRefreshLayout.setRefreshing(false);
        getData(++pageNo);
    }

    public void downListSuccess(DropOuting dropOuting) {
        tv_num.setText(dropOuting.getObj().getOrderNum()+"");
        tv_num1.setText(dropOuting.getObj().getCallNum()+"");
        dropOutingAdapter.setType(type);
        if (swipeRefreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(dropOutingList)) {
                dropOutingList.clear();
                dropOutingAdapter.notifyDataSetChanged();
            }
        }
        stop();
        if (ListUtils.isEmpty(dropOutingList)) {
            if (ListUtils.isEmpty(dropOuting.getObj().getList())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(dropOuting.getObj().getList())) {
            //已经获取数据
            if (pageNo!=1){
                dropOutingAdapter.loadMoreEnd();
            }
            return;
        }
        dropOutingList.addAll(dropOuting.getObj().getList());
        dropOutingAdapter.setNewData(dropOutingList);
        dropOutingAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    public void  getdownList_Failed(){
        stop();
        if (ListUtils.isEmpty(dropOutingList)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }
}
