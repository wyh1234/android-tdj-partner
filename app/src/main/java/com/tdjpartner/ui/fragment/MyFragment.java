package com.tdjpartner.ui.fragment;;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MyFragmentAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.MyFragmentBottom;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.ui.activity.AddBaifangActivity;
import com.tdjpartner.ui.activity.EarningsActivity;
import com.tdjpartner.ui.activity.EarningsHistoryActivity;
import com.tdjpartner.ui.activity.MessageActivity;
import com.tdjpartner.ui.activity.RealNameAuthenticationActivity;
import com.tdjpartner.ui.activity.SettingActivity;
import com.tdjpartner.ui.activity.ToMakeMoneyActivity;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.popuwindow.SetHeadImagePopu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyFragment extends BaseFrgment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener , View.OnClickListener {
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
   private RelativeLayout rl_sy,rl_more,rl;
    private RoundedImageView image;
    private MyFragmentAdapter  myFragmentAdapter;
    private List<MyFragmentBottom> list =new ArrayList();
    private SetHeadImagePopu setHeadImagePopu;
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_sy:
                Intent intent=new Intent(getContext(), EarningsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_more:
                Intent intent1=new Intent(getContext(), EarningsHistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl:
                Intent intent2=new Intent(getContext(), MessageActivity.class);
                startActivity(intent2);
                break;
            case R.id.image:
                if (setHeadImagePopu!=null){
                    if (setHeadImagePopu.isShowing()){
                        return;
                    }
                    setHeadImagePopu.showPopupWindow();
                }else {

                    setHeadImagePopu = new SetHeadImagePopu(getContext());
                    setHeadImagePopu.setPopupWindowFullScreen(true);//铺满
                    setHeadImagePopu.setDismissWhenTouchOutside(false);
                    setHeadImagePopu.setInterceptTouchEvent(false);
                    setHeadImagePopu.showPopupWindow();
                }
                break;

        }
    }
    @Override
    protected void initView(View view) {
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        list.add(new MyFragmentBottom("新增拜访",false));
        list.add(new MyFragmentBottom("实名认证",true));
        list.add(new MyFragmentBottom("去赚钱",false));
        list.add(new MyFragmentBottom("设置",false));
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myFragmentAdapter = new MyFragmentAdapter(R.layout.my_fragment_item,list);
        myFragmentAdapter.setOnItemClickListener(this);
        rv_recyclerView.setAdapter(myFragmentAdapter);
        View view1 = ViewUrils.getFragmentView(rv_recyclerView, R.layout.myfragment_head);
        rl_sy=view1.findViewById(R.id.rl_sy);
        image=view1.findViewById(R.id.image);
        image.setOnClickListener(this);
        rl_more=view1.findViewById(R.id.rl_more);
        rl=view1.findViewById(R.id.rl);
        rl_more.setOnClickListener(this);
        rl_sy.setOnClickListener(this);
        rl.setOnClickListener(this);
        myFragmentAdapter.addHeaderView(view1);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected int getContentId() {
        return R.layout.my_fragment;
    }

    @Override
    public void onRefresh() {
        stop();
    }

    public void stop(){
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LogUtils.e(i);
        if (i==3){
            Intent intent=new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
        }else if (i==0){
            Intent intent=new Intent(getContext(), AddBaifangActivity.class);
            startActivity(intent);

        }else if (i==2){
            Intent intent=new Intent(getContext(), ToMakeMoneyActivity.class);
            startActivity(intent);

        }else {
            Intent intent=new Intent(getContext(), RealNameAuthenticationActivity.class);
            startActivity(intent);

        }

    }
}
