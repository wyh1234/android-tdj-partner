package com.tdjpartner.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.PartnerCheckDetailsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.PartnerCheckDetails;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.PartnerCheckDetailsPopu;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;
import com.tdjpartner.widget.MyRecyclerView;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PartnerCheckDetailsActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.recyclerview)
    MyRecyclerView recyclerview;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn)
    Button btn;
    private PartnerCheckDetailsPopu partnerCheckDetailsPopu;
    @OnClick({R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                if (partnerCheckDetailsPopu!=null){
                    if (partnerCheckDetailsPopu.isShowing()){
                        return;
                    }
                    partnerCheckDetailsPopu.showPopupWindow();
                }else {

                    partnerCheckDetailsPopu = new PartnerCheckDetailsPopu(this);
                    partnerCheckDetailsPopu.setDismissWhenTouchOutside(false);
                    partnerCheckDetailsPopu.setInterceptTouchEvent(false);
                    partnerCheckDetailsPopu.setPopupWindowFullScreen(true);//铺满
                    partnerCheckDetailsPopu.showPopupWindow();
                }
                break;
        }
    }
    private PartnerCheckDetailsAdapter partnerCheckAdapter;
    private List<PartnerCheckDetails> data=new ArrayList<>();
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
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        data.add(new PartnerCheckDetails());
        data.add(new PartnerCheckDetails());
        data.add(new PartnerCheckDetails());
        CustomLinearLayout layout = new CustomLinearLayout(getContext(),
                CustomLinearLayout.VERTICAL, false);
        layout.setScrollEnabled(false);
        recyclerview.setLayoutManager(layout);
        partnerCheckAdapter=new PartnerCheckDetailsAdapter(R.layout.partner_check_details_item,data);
        recyclerview.setAdapter(partnerCheckAdapter);
        partnerCheckAdapter.setOnItemChildClickListener(this);
        tv_title.setText("审核处理");
        toolbar.setBackgroundResource(R.mipmap.home_bg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.partner_ckeck_details_layout;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (view.getId()==R.id.rl_one){
            data.get(i).setF(true);

        }else if (view.getId()==R.id.rl_two){
            data.get(i).setF(false);
        }
        partnerCheckAdapter.notifyDataSetChanged();

    }
}
