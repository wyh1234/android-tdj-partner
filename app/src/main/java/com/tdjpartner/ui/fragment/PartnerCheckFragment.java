package com.tdjpartner.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.PartnerCheckAdapter;
import com.tdjpartner.R;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ClientInfo;
import com.tdjpartner.model.PartnerCheck;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.PartnerCheckPresenter;
import com.tdjpartner.ui.activity.PartnerCheckDetailsActivity;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class PartnerCheckFragment extends BaseFrgment<PartnerCheckPresenter>  implements OnRefreshListener, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index=0;
    private List<PartnerCheck> data=new ArrayList<>();
    private PartnerCheckAdapter partnerCheckAdapter;
    private SeachTag seachTag;

    public SeachTag getSeachTag() {
        return seachTag;
    }

    public void setSeachTag(SeachTag seachTag) {
        this.seachTag = seachTag;
    }

    public static PartnerCheckFragment newInstance(int str) {
        Bundle args = new Bundle();
        args.putInt("intent", str);
        PartnerCheckFragment f = new PartnerCheckFragment();
        f.setArguments(args);
        return f;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.e("onAttach");
        registerEventBus(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.e("onDetach");
        unregisterEventBus(this);
    }
    @Override
    protected void initView(View view) {
        refreshLayout.setOnRefreshListener(this);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        partnerCheckAdapter=new PartnerCheckAdapter(R.layout.partner_check_item,data);
        recyclerView_list.setAdapter(partnerCheckAdapter);
        partnerCheckAdapter.setOnItemClickListener(this);
    }
    @Override
    public void onUserVisible() {
        super.onUserVisible();//可见时
        index=getArguments().getInt("intent");

        refreshLayout.autoRefresh();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected PartnerCheckPresenter loadPresenter() {
        return new PartnerCheckPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.client_list_fragment;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(getContext(), PartnerCheckDetailsActivity.class);
        intent.putExtra("verifyStatus",data.get(i).getVerifyStatus()+"");
        startActivity(intent);

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        LogUtils.e(index);
        getData();
    }
    protected  void getData(){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", "25736");
        if (index==1){
            map.put("verifyStatus", 0);
        }else if (index==2||index==3){
            map.put("verifyStatus", index);
        }
        map.put("keyword",seachTag==null?"":seachTag.getTag());
        mPresenter.verifyList(map);


    }
    @Subscribe
    public void eventCode(SeachTag seachTag) {
        setSeachTag(seachTag);
        refreshLayout.autoRefresh();

    }
    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }


    public void stop() {
        LogUtils.i(refreshLayout.isRefreshing());
        if (refreshLayout.isRefreshing()) {
            refreshLayout.finishRefresh();
        }
    }


    public void verifyList_Success(List<PartnerCheck> partnerCheckList) {
        stop();
        if (!ListUtils.isEmpty(data)) {
            data.clear();
        }

        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(partnerCheckList)) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }


        data.addAll(partnerCheckList);
        partnerCheckAdapter.setNewData(data);
    }

    public void verifyList_Failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }
    }
}
