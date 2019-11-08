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

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.CommonFollowUpAdapter;
import com.tdjpartner.adapter.DropOutingAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.DropOuting;
import com.tdjpartner.mvp.presenter.CommonFollowUpPresenter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class CommonFollowUpActivity extends BaseActivity<CommonFollowUpPresenter>  implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemChildClickListener,BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.RequestLoadMoreListener, FollowUpPopuWindow.FollowUpListener {
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
    @BindView(R.id.search_text)
    EditText search_text;
    @BindView(R.id.view)
    View view2;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private CommonFollowUpAdapter commonFollowUpAdapter;
    private List<DropOuting.ObjBean> dropOutingList=new ArrayList<>();
    private FollowUpPopuWindow followUpPopuWindow;
    public int pageNo = 1;//翻页计数器
    private String type="followNot";
    private int customerId;
    private int pos;
    private String keyword="";
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

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
                type="followNot";
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
                type="followYes";
                swipeRefreshLayout.setRefreshing(true);
                onRefresh();
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
    protected CommonFollowUpPresenter loadPresenter() {
        return new CommonFollowUpPresenter();
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
        commonFollowUpAdapter=new CommonFollowUpAdapter(R.layout.common_followup_item_layout,dropOutingList);
        recyclerView_list.setAdapter(commonFollowUpAdapter);
        commonFollowUpAdapter.setOnItemChildClickListener(this);
        commonFollowUpAdapter.setOnItemClickListener(this);
        commonFollowUpAdapter.setOnLoadMoreListener(this,recyclerView_list);
        commonFollowUpAdapter.setType(type);
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.common_followup_layout;
    }

    @Override
    public void onRefresh() {
        pageNo=1;
        getData(pageNo);
    }
    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);

        }
        if (commonFollowUpAdapter.isLoadMoreEnable()){
            commonFollowUpAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId()==R.id.tv_gj_status){
            if (commonFollowUpAdapter.getType().equals("followNot")){

            setCustomerId(dropOutingList.get(i).getCustomerId());
        /*    if (followUpPopuWindow!=null){
                if (followUpPopuWindow.isShowing()){
                    return;
                }
                followUpPopuWindow.showPopupWindow();
            }else {*/

                followUpPopuWindow = new FollowUpPopuWindow(this,dropOutingList.get(i).getName());
                followUpPopuWindow.setDismissWhenTouchOutside(false);
                followUpPopuWindow.setInterceptTouchEvent(false);
                followUpPopuWindow.setPopupWindowFullScreen(true);//铺满
                followUpPopuWindow.showPopupWindow();
                followUpPopuWindow.setFollowUpListener(this);
//            }

            }
        }


    }
    private void getData(int pageNo) {
        Map<String,Object> map=new HashMap<>();
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
        map.put("type", type);
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("pn", pageNo);
        map.put("ps", 10);
        map.put("keyword", keyword);
        mPresenter.followList(map);

    }

    public void followListSuccess(DropOuting dropOuting) {


        tv_num.setText(dropOuting.getObj().getFollowNum()+"");
        tv_num1.setText(dropOuting.getObj().getFollowedNum()+"");
        commonFollowUpAdapter.setType(type);
        if (swipeRefreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(dropOutingList)) {
                dropOutingList.clear();
                commonFollowUpAdapter.notifyDataSetChanged();
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
                commonFollowUpAdapter.loadMoreEnd();
            }
            return;
        }
        dropOutingList.addAll(dropOuting.getObj().getList());
        commonFollowUpAdapter.setNewData(dropOutingList);
        commonFollowUpAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置
    }

    public void getfollowList_Failed() {
        stop();
        if (ListUtils.isEmpty(dropOutingList)) {
            mStateView.showEmpty();//显示重试的布局
        }
        commonFollowUpAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(false);
        getData(++pageNo);
    }
    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    public View getStateViewRoot() {
        return recyclerView_list;
    }
    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
      Intent intent=new Intent(getContext(), ClientDetailsActivity.class);
        intent.putExtra("customerId",dropOutingList.get(i).getCustomerId()+"");
        startActivity(intent);
    }

    @Override
    public void onCancel() {
        followUpPopuWindow.dismiss();
    }

    @Override
    public void onOk() {
        followUpPopuWindow.dismiss();
        Map<String,Object> map=new HashMap<>();
        map.put("customerId", getCustomerId());
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("userName", "");
        map.put("address", "");
        map.put("lon", "");
        map.put("lat", "");
        mPresenter.internationalWaters(map);

    }

    public void internationalWatersSuccess() {
        commonFollowUpAdapter.remove(getPos());

    }
}
