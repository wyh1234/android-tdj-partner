package com.tdjpartner.ui.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.PartnerCheckDetailsAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.model.PartnerCheckDetails;
import com.tdjpartner.model.SeachTag;
import com.tdjpartner.mvp.presenter.IPresenter;
import com.tdjpartner.mvp.presenter.PartnerCheckDetailsPresenter;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.glide.ImageLoad;
import com.tdjpartner.utils.popuwindow.FollowUpPopuWindow;
import com.tdjpartner.utils.popuwindow.PartnerCheckDetailsPopu;
import com.tdjpartner.utils.statusbar.Eyes;
import com.tdjpartner.widget.CustomLinearLayout;
import com.tdjpartner.widget.MyRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class PartnerCheckDetailsActivity extends BaseActivity<PartnerCheckDetailsPresenter> implements BaseQuickAdapter.OnItemChildClickListener, PartnerCheckDetailsPopu.PartnerCheckDetailsListener {
    @BindView(R.id.recyclerview)
    MyRecyclerView recyclerview;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_verifyStatus)
    TextView tv_verifyStatus;
    @BindView(R.id.tv_leaderName)
    TextView tv_leaderName;
    @BindView(R.id.tv_createdAt)
    TextView tv_createdAt;
    @BindView(R.id.tv_imageUrl)
    ImageView tv_imageUrl;
    @BindView(R.id.tv_bzlicenceUrl)
    ImageView tv_bzlicenceUrl;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.tv_qy)
    TextView tv_qy;
    private PartnerCheckDetailsPopu partnerCheckDetailsPopu;
    @OnClick({R.id.btn,R.id.btn_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                Map<String,Object> map=new HashMap<>();
                map.put("verifyStatus",partnerCheckAdapter.data.get(data.size()-1).isF()?2:3);
//                map.put("id",getIntent().getStringExtra("id"));
                map.put("id",getIntent().getStringExtra("id"));
                map.put("applyId",partnerCheckAdapter.data.get(data.size()-1).getApplyId());
                map.put("nodeNumber",partnerCheckAdapter.data.get(data.size()-1).getNodeNumber());
                map.put("verifyUserId",partnerCheckAdapter.data.get(data.size()-1).getVerifyUserId());

                map.put("verifyRemark",partnerCheckAdapter.data.get(data.size()-1).getVerifyRemark());
//                map.put("userId", UserUtils.getInstance().getLoginBean().getEntityId());
                map.put("userId", partnerCheckAdapter.data.get(data.size()-1).getUserId());
                if (!partnerCheckAdapter.data.get(data.size()-1).isF()){
                    if (GeneralUtils.isNullOrZeroLenght(partnerCheckAdapter.data.get(data.size()-1).getVerifyRemark().trim())){
                        GeneralUtils.showToastshort("请填写驳回原因");
                        return;
                    }

                }
                mPresenter.clickVerify(map);
                break;
            case R.id.btn_back:

                EventBus.getDefault().post(getIntent().getSerializableExtra("seachTag")==null?new SeachTag(""):getIntent().getSerializableExtra("seachTag"));
                finish();
                break;
        }
    }
    private PartnerCheckDetailsAdapter partnerCheckAdapter;
    private List<PartnerCheckDetails.UserApplyBean> data=new ArrayList<>();
    @Override
    protected PartnerCheckDetailsPresenter loadPresenter() {
        return new PartnerCheckDetailsPresenter();
    }

    @Override
    protected void initData() {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",getIntent().getStringExtra("userId"));
        map.put("loginId",UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("id",getIntent().getStringExtra("id"));
//        map.put("id",21);
//        map.put("verifyStatus","");
        mPresenter.verifyDetail(map);
    }

    @Override
    protected void initView() {
        Eyes.translucentStatusBar(this,true);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
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
            partnerCheckAdapter.data.get(i).setB(true);
            partnerCheckAdapter.data.get(i).setF(false);
        }else if (view.getId()==R.id.rl_two){
            partnerCheckAdapter.data.get(i).setB(false);
            partnerCheckAdapter.data.get(i).setF(true);
        }
        partnerCheckAdapter.notifyDataSetChanged();

    }

    public void verifyDetail_Success(PartnerCheckDetails partnerCheckDetails) {
        if (!ListUtils.isEmpty(data)){
            data.clear();
        }

        if (partnerCheckDetails.getUserApply().get(0).getVerifyStatus()==0){
            tv_verifyStatus.setText("待审核");
            tv_verifyStatus.setBackgroundResource(R.mipmap.check_item_hui_bg);
            tv_verifyStatus.setTextColor( GeneralUtils.getColor(this,R.color.gray_66));

        }else if (partnerCheckDetails.getUserApply().get(0).getVerifyStatus()==3){
            tv_verifyStatus.setText("审核驳回");
            tv_verifyStatus.setBackgroundResource(R.mipmap.shbh);
            tv_verifyStatus.setTextColor( GeneralUtils.getColor(this,R.color.white));
        }else if (partnerCheckDetails.getUserApply().get(0).getVerifyStatus()==2){
            tv_verifyStatus.setText("审核通过");
            tv_verifyStatus.setBackgroundResource(R.mipmap.shtg);
            tv_verifyStatus.setTextColor(GeneralUtils.getColor(this,R.color.white));
        }
        if (partnerCheckDetails.getUserApply().size()>1){
            if (partnerCheckDetails.getUserApply().get(partnerCheckDetails.getUserApply().size()-1).getVerifyStatus()==0){
                if (partnerCheckDetails.getUserApply().get(partnerCheckDetails.getUserApply().size()-1).getIsVerify()==0){
                    ll.setVisibility(View.VISIBLE);
                }else {
                    ll.setVisibility(View.GONE);
                }
            }
        }else {
            ll.setVisibility(View.GONE);
        }



        tv_leaderName.setText(partnerCheckDetails.getUserApply().get(0).getEnterpriseCode()+"\t\t\t\t负责人："+partnerCheckDetails.getUserApply().get(0).getNickName()+"("+
                "主账号"+")");
        tv_createdAt.setText("入驻日期："+partnerCheckDetails.getUserApply().get(0).getCreatedAt());
        ImageLoad.loadImageViewLoding(partnerCheckDetails.getUserApply().get(0).getImageUrl(),tv_imageUrl,R.mipmap.yingyezhao_bg);
        ImageLoad.loadImageViewLoding(partnerCheckDetails.getUserApply().get(0).getBzlicenceUrl(),tv_bzlicenceUrl,R.mipmap.yingyezhao_bg);
        tv_address.setText("地址："+partnerCheckDetails.getUserApply().get(0).getEnterpriseMsg());
        if (!ListUtils.isEmpty(partnerCheckDetails.getUserApply())){
            data.addAll(partnerCheckDetails.getUserApply());
            partnerCheckAdapter.setNewData(data);
        }
        tv_remark.setText(partnerCheckDetails.getUserApply().get(0).getRemark());
        tv_qy.setText("区域："+partnerCheckDetails.getUserApply().get(0).getRegionCollNo());
    }

    public void clickVerify_Success() {
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
                   partnerCheckDetailsPopu.setPartnerCheckDetailsListener(this);
                }
    }

    @Override
    public void onOk() {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",getIntent().getStringExtra("userId"));
        map.put("loginId",UserUtils.getInstance().getLoginBean().getEntityId());
        map.put("id",getIntent().getStringExtra("id"));
//        map.put("id",21);
        map.put("verifyStatus","");
        mPresenter.verifyDetail(map);
        partnerCheckDetailsPopu.dismiss();
    }
}
