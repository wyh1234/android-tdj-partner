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
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class PartnerCheckFragment extends BaseFrgment<PartnerCheckPresenter>  implements OnRefreshListener,OnLoadmoreListener, BaseQuickAdapter.OnItemClickListener{
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private int index=0;
    private int pn=1;
    private List<PartnerCheck.ObjBean> data=new ArrayList<>();
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
        refreshLayout.setOnLoadmoreListener(this);
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
        intent.putExtra("id",data.get(i).getId()+"");
        intent.putExtra("userId",data.get(i).getUserId()+"");
        intent.putExtra("seachTag",getSeachTag());
        startActivity(intent);

    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        LogUtils.e(index);
        pn=1;
        getData(pn);
    }
    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", "23251");
        if (index==1){
            map.put("verifyStatus", 0);
        }else if (index==2||index==3){
            map.put("verifyStatus", index);
        }
        map.put("keyword",seachTag==null?"":seachTag.getTag());
        map.put("pn",pn);
        map.put("ps",10);
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
        if (refreshLayout.isEnableLoadmore()) {
            refreshLayout.finishLoadmore();
        }
    }


    public void verifyList_Success(PartnerCheck partnerCheckList) {
        stop();
        if (refreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(data)) {
                data.clear();
            }
        }


        if (ListUtils.isEmpty(data)) {
            if (ListUtils.isEmpty(partnerCheckList.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }
        if (ListUtils.isEmpty(partnerCheckList.getObj())) {
            //已经获取数据
            if (pn!=1){
                GeneralUtils.showToastshort("数据加载完毕");
                return;
            }else {
                GeneralUtils.showToastshort("暂无数据");
                return;
            }
        }
        data.addAll(partnerCheckList.getObj());
        partnerCheckAdapter.setNewData(data);
    }

    public void verifyList_Failed() {
        stop();
        if (ListUtils.isEmpty(data)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getData(++pn);
    }
}
