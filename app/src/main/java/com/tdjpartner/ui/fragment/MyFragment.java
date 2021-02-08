package com.tdjpartner.ui.fragment;;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.MyFragmentAdapter;
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.ImageUploadOk;
import com.tdjpartner.model.LocationBean;
import com.tdjpartner.model.MyFragmentBottom;
import com.tdjpartner.model.UserInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.MyFragmentPresneter;
import com.tdjpartner.ui.activity.AddBaifangActivity;
import com.tdjpartner.ui.activity.AdministrationPaifangHistoryActivity;
import com.tdjpartner.ui.activity.EarningsActivity;
import com.tdjpartner.ui.activity.EarningsHistoryActivity;
import com.tdjpartner.ui.activity.MenberPaifangHistoryActivity;
import com.tdjpartner.ui.activity.MessageActivity;
import com.tdjpartner.ui.activity.RealNameAuthenticationActivity;
import com.tdjpartner.ui.activity.SettingActivity;
import com.tdjpartner.ui.activity.ToMakeMoneyActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ViewUrils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.popuwindow.SetHeadImagePopu;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static com.umeng.socialize.utils.ContextUtil.getContext;

public class MyFragment extends BaseFrgment<MyFragmentPresneter> implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener , View.OnClickListener
,SetHeadImagePopu.SetHeadImageListener{
    @BindView(R.id.rv_recyclerView)
    RecyclerView rv_recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
   private RelativeLayout rl_sy,rl_more,rl;
    private RoundedImageView image;
    private MyFragmentAdapter  myFragmentAdapter;
    private List<MyFragmentBottom> list =new ArrayList();
    private SetHeadImagePopu setHeadImagePopu;
    private TextView tv_name,tv_phone,tv_money,tv_pmcount;
    private RxPermissions rxPermissions;
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
                    setHeadImagePopu.setSetHeadImageListener(this);
                    setHeadImagePopu.setInterceptTouchEvent(false);
                    setHeadImagePopu.showPopupWindow();
                }
                break;

        }
    }
    @Override
    protected void initView(View view) {
        rxPermissions = new RxPermissions(getActivity());
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this);
        if (UserUtils.getInstance().getLoginBean().getGrade()!=3){
            list.add(new MyFragmentBottom("专员拜访记录",false));
        }else {
            list.add(new MyFragmentBottom("拜访记录",false));
        }

        list.add(new MyFragmentBottom("实名认证",false));
        list.add(new MyFragmentBottom("去赚钱",false));
        list.add(new MyFragmentBottom("设置",false));
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myFragmentAdapter = new MyFragmentAdapter(R.layout.my_fragment_item,list);
        myFragmentAdapter.setOnItemClickListener(this);
        rv_recyclerView.setAdapter(myFragmentAdapter);
        View view1 = ViewUrils.getFragmentView(rv_recyclerView, R.layout.myfragment_head);
        rl_sy=view1.findViewById(R.id.rl_sy);
        image=view1.findViewById(R.id.image);
        tv_name=view1.findViewById(R.id.tv_name);
        tv_phone=view1.findViewById(R.id.tv_phone);
        tv_pmcount=view1.findViewById(R.id.tv_pmcount);
        tv_money=view1.findViewById(R.id.tv_money);
        image.setOnClickListener(this);
        rl_more=view1.findViewById(R.id.rl_more);
        rl=view1.findViewById(R.id.rl);
        rl_more.setOnClickListener(this);
        rl_sy.setOnClickListener(this);
        rl.setOnClickListener(this);
        myFragmentAdapter.addHeaderView(view1);



    }
    @Override
    public void onUserVisible() {//再次可见，不会加载initData
        super.onUserVisible();
        LogUtils.e(222);

    }
    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        LogUtils.e("我的");

        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    public void setMyData(){
        LogUtils.e((UserUtils.getInstance().getLoginBean()));
        if (UserUtils.getInstance().getLoginBean()!=null){
            if (UserUtils.getInstance().getLoginBean().getGradeName()!=null){
                tv_name.setText(UserUtils.getInstance().getLoginBean().getGradeName());
            }

            tv_phone.setText(UserUtils.getInstance().getLoginBean().getPhoneNumber().substring(0,3)+"****"+UserUtils.getInstance().getLoginBean().getPhoneNumber()
                    .substring(7,UserUtils.getInstance().getLoginBean().getPhoneNumber().length()));
            if (UserUtils.getInstance().getLoginBean().getSurplusAmount()!=null){
                tv_money.setText(UserUtils.getInstance().getLoginBean().getSurplusAmount()+"");

            }
            if (UserUtils.getInstance().getLoginBean().getPmCount()!=null&&UserUtils.getInstance().getLoginBean().getPmCount()!=0){
                    tv_pmcount.setText(UserUtils.getInstance().getLoginBean().getPmCount()+"");

                }else {
                       tv_pmcount.setText("");
            }


            ImageLoad.loadImageViewLoding(UserUtils.getInstance().getLoginBean().getAvatarUrl(),image);
        }


        list.get(1).setF(!GeneralUtils.isNullOrZeroLenght(UserUtils.getInstance().getLoginBean().getIdCard()));
        myFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected MyFragmentPresneter loadPresenter() {
        return new MyFragmentPresneter();
    }

    @Override
    protected int getContentId() {
        return R.layout.my_fragment;
    }

    @Override
    public void onRefresh() {
        LogUtils.e(UserUtils.getInstance().getLoginBean());
        mPresenter.customer_refreshInfo(UserUtils.getInstance().getLoginBean().getEntityId(),UserUtils.getInstance().getLoginBean().getLoginUserId());



    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerEventBus(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unregisterEventBus(this);
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
            if (UserUtils.getInstance().getLoginBean().getGrade()==3){
                Intent intent=new Intent(getContext(), MenberPaifangHistoryActivity.class);
                startActivity(intent);

            }else {
                if (UserUtils.getInstance().getLoginBean().getGrade()==1||UserUtils.getInstance().getLoginBean().getGrade()==2){
                    Intent intent=new Intent(getContext(), AdministrationPaifangHistoryActivity.class);
                    startActivity(intent);
                }else {
                    GeneralUtils.showToastshort("暂无访问权限");
                }


            }


        }else if (i==2){
            Intent intent=new Intent(getContext(), ToMakeMoneyActivity.class);
            startActivity(intent);

        }else {
                Intent intent=new Intent(getContext(), RealNameAuthenticationActivity.class);
                startActivity(intent);


        }

    }

    public void getrefreshInfo(UserInfo userInfo) {
        stop();
        if (userInfo!=null){
            UserUtils.getInstance().update(userInfo);
            setMyData();
        }

    }

    public void getrefreshInfo_failed() {
        stop();
    }

    public void getImage_Success(String data) {

    }


    @Override
    public void onCancel() {
        setHeadImagePopu.dismiss();

    }

    @Override
    public void onUpload() {
        GeneralUtils.getImage(rxPermissions,getActivity());
        setHeadImagePopu.dismiss();

    }

    @Subscribe( threadMode = ThreadMode.MAIN)
    public void eventCode(ImageUploadOk imageUploadOk) {
        LogUtils.e(imageUploadOk);
        ImageLoad.loadImageViewLoding(imageUploadOk.getPath(),image);
        Map<String,Object>map=new HashMap<>();
        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("avatarUrl",imageUploadOk.getPath());
        mPresenter.modifyAvatarUrl(map);

    }

}
