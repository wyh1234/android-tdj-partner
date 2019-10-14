package com.tdjpartner.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.BankLisAdapter;
import com.tdjpartner.adapter.PartnerMessageAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.BankList;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.mvp.presenter.BankListPresneter;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BankListActivity extends BaseActivity<BankListPresneter> implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private List<BankList> bankList=new ArrayList<>();
    private BankLisAdapter bankLisAdapter;
    @OnClick({R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
    @Override
    protected BankListPresneter loadPresenter() {
        return new BankListPresneter();
    }

    @Override
    protected void initData() {
        mPresenter.selectBankInfoList(new HashMap<>());

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        tv_title.setText("绑定银行卡");
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        bankLisAdapter=new BankLisAdapter(R.layout.bank_list_item_layout,bankList);
        recyclerView_list.setAdapter(bankLisAdapter);
        bankLisAdapter.setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bank_list_layout;
    }

    public void selectBankInfoListSuccess(List<BankList> bank) {
        if (!ListUtils.isEmpty(bank)){
            bankLisAdapter.setNewData(bank);
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        EventBus.getDefault().post(((BankList)baseQuickAdapter.getData().get(i)));
        finish();

    }
}
