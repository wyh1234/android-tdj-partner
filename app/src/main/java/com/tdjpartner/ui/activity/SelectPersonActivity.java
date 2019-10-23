package com.tdjpartner.ui.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.SelectPersonAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.SelectPerson;
import com.tdjpartner.model.SettingPerson;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.SelectPersonActivityPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.SelectPopuWindow;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectPersonActivity extends BaseActivity<SelectPersonActivityPresenter> implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener,SelectPopuWindow.SelectPopuWindowListener {
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    private List<SelectPerson.ObjBean> selectPersonList=new ArrayList<>();
    private SelectPersonAdapter settingPersonAdapter;
    public int pageNo = 1;//翻页计数器
    private SelectPopuWindow selectPopuWindow;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.search_text)
    EditText search_text;
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    private String verifyCode;
    private String keyword="";
    private SettingPerson.ObjBean.ListBean listBean;
    @OnClick({R.id.btn_back,R.id.tv_list_type})
    public void onClick(View view){
        switch (view.getId()){
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
    protected SelectPersonActivityPresenter loadPresenter() {
        return new SelectPersonActivityPresenter();
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
        listBean= (SettingPerson.ObjBean.ListBean) getIntent().getSerializableExtra("ListBean");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.select_person_layout;
    }

    protected  void getData(int pn){
        Map<String,Object> map=new HashMap<>();
        map.put("websiteId", UserUtils.getInstance().getLoginBean().getSite());
        map.put("pn", pn);
        map.put("ps", 10);
        map.put("keyword", keyword);

        mPresenter.userRelations_managerList(map);

    }
    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return recyclerView_list;
    }
    @Override
    public void onRefresh() {
        pageNo=1;
        getData(pageNo);
    }
    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

        if (selectPersonList.get(i).isF()){
            selectPersonList.get(i).setF(false);
        }else {
            for (SelectPerson.ObjBean selectPerson:selectPersonList){
                selectPerson.setF(false);
            }
            selectPersonList.get(i).setF(true);
        }

        settingPersonAdapter.notifyDataSetChanged();

        if (selectPopuWindow!=null){
            if (selectPopuWindow.isShowing()){
                return;
            }
            selectPopuWindow.showPopupWindow();
        }else {

            selectPopuWindow = new SelectPopuWindow(this,"您确定将客户"+'"'+listBean.getName()+'"'+"分配给"+selectPersonList.get(i).getNickName()+"吗?");
            selectPopuWindow.setDismissWhenTouchOutside(false);
            selectPopuWindow.setInterceptTouchEvent(false);
            selectPopuWindow.setPopupWindowFullScreen(true);//铺满
            selectPopuWindow.showPopupWindow();
            selectPopuWindow.setSelectPopuWindowListener(this);
        }
        verifyCode=selectPersonList.get(i).getVerifyCode();
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
            getData(++pageNo);

    }

    public void userRelations_managerList_Success(SelectPerson selectPerson) {
        stop();
        if (swipeRefreshLayout.isRefreshing()){
            if (!ListUtils.isEmpty(selectPersonList)) {
                selectPersonList.clear();
            }
        }

        if (ListUtils.isEmpty(selectPersonList)) {
            if (ListUtils.isEmpty(selectPerson.getObj())) {
                //获取不到数据,显示空布局
                mStateView.showEmpty();
                return;
            }
            mStateView.showContent();//显示内容
        }

        if (ListUtils.isEmpty(selectPerson.getObj())) {
            //已经获取数据
            if (pageNo!=1){
                GeneralUtils.showToastshort("数据加载完毕");
            }else {
                GeneralUtils.showToastshort("暂无数据");
            }
            return;
        }
        selectPersonList.addAll(selectPerson.getObj());
        settingPersonAdapter.setNewData(selectPersonList);
        settingPersonAdapter.disableLoadMoreIfNotFullPage(recyclerView_list);//数据项个数未满一屏幕,则不开启load more,add数据后设置
    }

    public void userRelations_managerList_Success_failed() {
        stop();
        if (ListUtils.isEmpty(selectPersonList)) {
            //如果一开始进入没有数据
            mStateView.showEmpty();//显示重试的布局
        }
    }

    @Override
    public void onCancel() {
        selectPopuWindow.dismiss();
    }

    @Override
    public void onOk() {
        Map<String,Object> map=new HashMap<>();
        map.put("verifyCode",verifyCode);
        map.put("userId",UserUtils.getInstance().getLoginBean().getEntityId());
        mPresenter.userRelations_setManager(map);


    }

    public void userRelations_setManager_Success() {
        selectPopuWindow.dismiss();
        GeneralUtils.showToastshort("设置专员成功");
    }
}
