package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ToMakeMoneyRankingAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.ToMakeMoney;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.ToMakeMoneyPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;
import com.tdjpartner.widget.MyRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ToMakeMoneyActivity extends BaseActivity<ToMakeMoneyPresenter> {
    @BindView(R.id.ranking_list)
    MyRecyclerView ranking_list;
    @BindView(R.id.rl_partner_sy)
    RelativeLayout rl_partner_sy;
    @BindView(R.id.rl_yq_partner)
    RelativeLayout rl_yq_partner;
    @BindView(R.id.rl_yq)
    RelativeLayout rl_yq;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.mScrollShop)
    ScrollView mScrollShop;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_newten_date)
    TextView tv_newten_date;
    @BindView(R.id.tv_customerCount)
    TextView tv_customerCount;
    @BindView(R.id.tv_myCountMoney)
    TextView tv_myCountMoney;

    private ToMakeMoneyRankingAdapter toMakeMoneyRankingAdapter;
    private List<ToMakeMoney.TopTenDateBean> toMakeMoneyyRankingList =new ArrayList<>();
    @OnClick({R.id.btn_back,R.id.rl_partner_sy,R.id.rl_yq_partner,R.id.rl_yq})
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.rl_partner_sy:
                Intent intent=new Intent(this,EarningsHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_yq_partner:
                Intent inten1=new Intent(this,InvitationHistoryActivity.class);
                startActivity(inten1);
                break;
            case R.id.rl_yq:
                Intent intens=new Intent(this,InvitationActivity.class);
                startActivity(intens);
                break;


        }

        }
    @Override
    protected ToMakeMoneyPresenter loadPresenter() {
        return new ToMakeMoneyPresenter();
    }

    @Override
    protected void initData() {
        Map<String,Object> map=new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
        mPresenter.amountAnalysisRecords(map);

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        mScrollShop.smoothScrollTo(0, 0);//置顶
        CustomLinearLayout layout = new CustomLinearLayout(this,
                LinearLayoutManager.VERTICAL, false);
        layout.setScrollEnabled(false);
        ranking_list.setLayoutManager(layout);
        toMakeMoneyRankingAdapter=new ToMakeMoneyRankingAdapter(R.layout.tomakemoney_ranking_item,toMakeMoneyyRankingList);
        ranking_list.setAdapter(toMakeMoneyRankingAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.tomakemoney_layout;
    }

    public void amountAnalysisRecordsSuccess(ToMakeMoney toMakeMoney) {
        LogUtils.e("sssss");
        StringBuilder builder=new StringBuilder();
        if (!ListUtils.isEmpty(toMakeMoney.getTopTenDate())){
            toMakeMoneyRankingAdapter.setNewData(toMakeMoney.getTopTenDate());


        }
        if (toMakeMoney.getNewTenDate().size()>0){
            for (int i=0;i<toMakeMoney.getNewTenDate().size();i++){
                builder.append(toMakeMoney.getNewTenDate().get(i));
            }
            tv_newten_date.setText(builder.toString());
        }

        tv_customerCount.setText(toMakeMoney.getCustomerCount()+"人");
        if (toMakeMoney.getMyCountMoney()!=null){
            tv_myCountMoney.setText(toMakeMoney.getMyCountMoney()+"元");

        }else {
            tv_myCountMoney.setText("0元");
        }
        toMakeMoneyRankingAdapter.setNewData(toMakeMoney.getTopTenDate());
        tv_money.setText(toMakeMoney.getMaxMoney()+"");
    }
}
