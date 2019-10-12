package com.tdjpartner.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentAdapter;
import com.tdjpartner.adapter.PartnerCheckFragmentAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PartnerCheckActivity extends BaseActivity {
    @BindView(R.id.wtab)
    WTabLayout wtab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
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

        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待审核");
        titles.add("审核通过");
        titles.add("审核驳回");
        PartnerCheckFragmentAdapter adatper = new PartnerCheckFragmentAdapter(getSupportFragmentManager(), titles);
        viewPager.setAdapter(adatper);
//        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        wtab.setupWithViewPager(viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.partner_ckeck_layout;
    }
}
