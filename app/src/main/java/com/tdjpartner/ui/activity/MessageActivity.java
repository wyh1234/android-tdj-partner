package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.InvitationHistoryAdapter;
import com.tdjpartner.adapter.PartnerMessageAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.InvitationHistory;
import com.tdjpartner.model.Message;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private List<PartnerMessageInfo> messageInfoList=new ArrayList<>();
    private PartnerMessageAdapter messageAdapter;
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
        messageInfoList.add(new PartnerMessageInfo());
        messageInfoList.add(new PartnerMessageInfo());
        messageInfoList.add(new PartnerMessageInfo());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        messageAdapter=new PartnerMessageAdapter(R.layout.message_item_layout,messageInfoList);
        recyclerView_list.setAdapter(messageAdapter);
        messageAdapter.setOnItemClickListener(this);
        tv_title.setText("消息");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message_layout;
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intent intent=new Intent(this,MessageItemActivity.class);
        startActivity(intent);

    }
}
