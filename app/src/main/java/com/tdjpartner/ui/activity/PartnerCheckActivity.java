package com.tdjpartner.ui.activity;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.FragmentAdapter;
import com.tdjpartner.adapter.PartnerCheckFragmentAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.tablayout.WTabLayout;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.tv_list_type)
    TextView tv_list_type;
    @BindView(R.id.search_text)
    EditText search_text;
    @OnClick({R.id.btn_back,R.id.tv_list_type})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_list_type:
                if (GeneralUtils.isNullOrZeroLenght(search_text.getText().toString())){
                    GeneralUtils.showToastshort("请输入门店名称或者手机号");

                }else {
                    EventBus.getDefault().post(new SeachTag(search_text.getText().toString()));
                }
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
