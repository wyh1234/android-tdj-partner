package com.tdjpartner.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.BankLisAdapter;
import com.tdjpartner.adapter.PartnerMessageAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.Bank;
import com.tdjpartner.model.PartnerMessageInfo;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BankListActivity extends BaseActivity {
    @BindView(R.id.recyclerView_list)
    RecyclerView recyclerView_list;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private List<Bank> bankList=new ArrayList<>();
    private BankLisAdapter bankLisAdapter;
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
        tv_title.setText("绑定银行卡");
        bankList.add(new Bank());
        bankList.add(new Bank());
        bankList.add(new Bank());
        LinearLayoutManager layout = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView_list.setLayoutManager(layout);
        bankLisAdapter=new BankLisAdapter(R.layout.bank_list_item_layout,bankList);
        recyclerView_list.setAdapter(bankLisAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.bank_list_layout;
    }
}
