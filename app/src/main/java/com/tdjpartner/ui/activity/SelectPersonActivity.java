package com.tdjpartner.ui.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.SelectPersonAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.SelectPerson;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectPersonActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<SelectPerson> selectPersonList=new ArrayList<>();
    private SelectPersonAdapter settingPersonAdapter;
    public int pageNo = 1;//翻页计数器
    private Handler handler=new Handler();
    private FollowUpPopuWindow followUpPopuWindow;

    @BindView(R.id.btn_back)
    ImageView btn_back;
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
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        settingPersonAdapter=new SelectPersonAdapter(R.layout.select_person_item,selectPersonList);
        settingPersonAdapter.setOnItemClickListener(this);
        recyclerView_list.setAdapter(settingPersonAdapter);
        settingPersonAdapter.setOnLoadMoreListener(this,recyclerView_list);
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.select_person_layout;
    }

    protected  void getData(int pn){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                get_client_success();
            }
        }, 3000);//3秒后执行Runnable中的run方法

    }
    public void get_client_success(){
        if (swipeRefreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(selectPersonList)) {
                selectPersonList.clear();
            }
        }

        selectPersonList.add(new SelectPerson());
        settingPersonAdapter.setNewData(selectPersonList);
        settingPersonAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);
//        mStateView.showEmpty();
        stop();
    }
    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }
    @Override
    public void onRefresh() {
        pageNo=1;
        getData(1);
    }
    public void  get_client_Failed(){
        stop();
        if (ListUtils.isEmpty(selectPersonList)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }

    }
    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

        if (selectPersonList.get(i).isF()){
            selectPersonList.get(i).setF(false);
        }else {
            for (SelectPerson selectPerson:selectPersonList){
                selectPerson.setF(false);
            }
            selectPersonList.get(i).setF(true);
        }

        settingPersonAdapter.notifyDataSetChanged();

        if (followUpPopuWindow!=null){
            if (followUpPopuWindow.isShowing()){
                return;
            }
            followUpPopuWindow.showPopupWindow();
        }else {

            followUpPopuWindow = new FollowUpPopuWindow(this,"SELECTPERSON");
            followUpPopuWindow.setDismissWhenTouchOutside(false);
            followUpPopuWindow.setInterceptTouchEvent(false);
            followUpPopuWindow.setPopupWindowFullScreen(true);//铺满
            followUpPopuWindow.showPopupWindow();
        }

    }


    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (settingPersonAdapter.isLoadMoreEnable()){
            settingPersonAdapter.loadMoreComplete();
        }
        LogUtils.e(settingPersonAdapter.isLoadMoreEnable());
    }

    @Override
    public void onPause() {
        super.onPause();
        stop();
    }

    @Override
    public void onLoadMoreRequested() {
        swipeRefreshLayout.setRefreshing(false);
            getData(++pageNo);

    }
}
