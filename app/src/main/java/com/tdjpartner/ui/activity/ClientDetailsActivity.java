package com.tdjpartner.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.tdjpartner.adapter.HistoryInfoAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.StoreInfoAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.HistoryInfo;
import com.tdjpartner.model.StoreInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;
import com.tdjpartner.widget.MyRecyclerView;
import com.tdjpartner.widget.ScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClientDetailsActivity extends BaseActivity {
    @BindView(R.id.store_info_list)
    MyRecyclerView store_info_list;
    @BindView(R.id.history_info_list)
    MyRecyclerView history_info_list;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private List<StoreInfo> storeInfoList=new ArrayList<>();
    private List<HistoryInfo> historyInfoList=new ArrayList<>();
    private StoreInfoAdapter storeInfoAdapter;
    private HistoryInfoAdapter historyInfoAdapter;
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
        storeInfoList.add(new StoreInfo());
        storeInfoList.add(new StoreInfo());
        storeInfoList.add(new StoreInfo());
        storeInfoList.add(new StoreInfo());
        storeInfoList.add(new StoreInfo());

        historyInfoList.add(new HistoryInfo());
        historyInfoList.add(new HistoryInfo());
        historyInfoList.add(new HistoryInfo());
        historyInfoList.add(new HistoryInfo());
        CustomLinearLayout customLinearLayout=  new CustomLinearLayout(getContext(), LinearLayoutManager.VERTICAL, false);
        customLinearLayout.setScrollEnabled(false);
        ScrollLinearLayoutManager layout = new ScrollLinearLayoutManager(getContext(), 4);
        layout.setScrollEnable(false);
        store_info_list.setLayoutManager(customLinearLayout);
        history_info_list.setLayoutManager( layout);
        storeInfoAdapter=new StoreInfoAdapter(R.layout.storeinfo_item_layout,storeInfoList);
        store_info_list.setAdapter(storeInfoAdapter);
        historyInfoAdapter=new HistoryInfoAdapter(R.layout.hisroryinfo_item_layout,historyInfoList);
        history_info_list.setAdapter(historyInfoAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.client_details_layout;
    }
}