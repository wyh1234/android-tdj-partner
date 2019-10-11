package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.WithdrawDetalisFilterAdapter;
import com.tdjpartner.adapter.home.TodyDataAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.Filterinfo;
import com.tdjpartner.model.SelectPerson;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawDetalisFilterActivity extends BaseActivity implements WithdrawDetalisFilterAdapter.OnItemClickListener {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.recyclerview_time)
    RecyclerView recyclerview_time;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private WithdrawDetalisFilterAdapter withdrawDetalisFilterAdapter,withdrawDetalisFilterAdapterone;
    private List<Filterinfo> list=new ArrayList<>();
    private List<Filterinfo> listone=new ArrayList<>();
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        list.add(new Filterinfo("全部"));
        list.add(new Filterinfo("提现成功"));
        list.add(new Filterinfo("提现失败"));
        list.add(new Filterinfo("提现中"));
        listone.add(new Filterinfo("今日"));
        listone.add(new Filterinfo("近一周"));
        listone.add(new Filterinfo("近一月"));
        listone.add(new Filterinfo("近半年"));
        tv_title.setText("提现筛选");
        ScrollLinearLayoutManager layoutManager=   new ScrollLinearLayoutManager(this, 4);
        ScrollLinearLayoutManager layoutManage1r=   new ScrollLinearLayoutManager(this, 4);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview_time.setLayoutManager(layoutManage1r);
        withdrawDetalisFilterAdapter = new WithdrawDetalisFilterAdapter(R.layout.withdraw_details_filter_item,list);
        withdrawDetalisFilterAdapterone = new WithdrawDetalisFilterAdapter(R.layout.withdraw_details_filter_item,listone);
        recyclerview.setAdapter(withdrawDetalisFilterAdapter);
        recyclerview_time.setAdapter(withdrawDetalisFilterAdapterone);
        withdrawDetalisFilterAdapter.setOnItemClickListener(this);
        withdrawDetalisFilterAdapterone.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.withdraw_filter_layout;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (baseQuickAdapter.getData() .containsAll(list) ){
            LogUtils.i("list");
            if (list.get(i).isF()){
                list.get(i).setF(false);
            }else {
                for (Filterinfo filterinfo:list){
                    filterinfo.setF(false);
                }
                list.get(i).setF(true);
            }

            withdrawDetalisFilterAdapter.notifyDataSetChanged();
        }else {
            LogUtils.i("listone");
            if (listone.get(i).isF()){
                listone.get(i).setF(false);
            }else {
                for (Filterinfo filterinfo:listone){
                    filterinfo.setF(false);
                }
                listone.get(i).setF(true);
            }

            withdrawDetalisFilterAdapterone.notifyDataSetChanged();
        }

    }
}
