package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.SelectPersonAdapter;
import com.tdjpartner.adapter.ToMakeMoneyRankingAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.ToMakeMoneyyRanking;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;
import com.tdjpartner.widget.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ToMakeMoneyActivity extends BaseActivity {
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
    private ToMakeMoneyRankingAdapter toMakeMoneyRankingAdapter;
    private List<ToMakeMoneyyRanking> toMakeMoneyyRankingList =new ArrayList<>();
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
    protected IPresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        mScrollShop.smoothScrollTo(0, 0);//置顶
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
        toMakeMoneyyRankingList.add(new ToMakeMoneyyRanking());
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
}
