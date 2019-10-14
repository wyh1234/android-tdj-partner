package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
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
import com.tdjpartner.mvp.presenter.MessagePersenter;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity<MessagePersenter> implements BaseQuickAdapter.OnItemClickListener {
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
    protected MessagePersenter loadPresenter() {
        return new MessagePersenter();
    }

    @Override
    protected void initData() {
//        Map<String,Object> map= new HashMap<>();
//        map.put("userId",25653);
//        mPresenter.pushMessage(map);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String,Object> map= new HashMap<>();
        map.put("userId",25653);
        mPresenter.pushMessage(map);
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
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
        PartnerMessageInfo partnerMessageInfo = (PartnerMessageInfo) baseQuickAdapter.getData().get(i);
        Intent intent=new Intent(this,MessageItemActivity.class);
        intent.putExtra("type",partnerMessageInfo.getType()+"");
        startActivity(intent);

    }

    public void getPushMessage(List<PartnerMessageInfo> partnerMessageInfoList) {
        if (!ListUtils.isEmpty(partnerMessageInfoList)){
            messageAdapter.setNewData(partnerMessageInfoList);
         }

    }
}
