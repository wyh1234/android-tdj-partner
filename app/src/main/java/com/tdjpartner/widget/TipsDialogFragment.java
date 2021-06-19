package com.tdjpartner.widget;

import android.os.Bundle;
import android.widget.TextView;

import com.tdjpartner.R;

import butterknife.BindView;

public class TipsDialogFragment extends BaseDialogFragment {

    @BindView(R.id.textView4)
    TextView tvClose;
    @BindView(R.id.textView3)
    TextView mTextView;


    public static TipsDialogFragment newInstance(int grade) {
        TipsDialogFragment fragment = new TipsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("grade", grade);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.tips_dialog;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        int grade = arguments.getInt("grade");
        tvClose.setOnClickListener(v->
            finishSelf()
        );
        String info = "";
        if (grade == 3) {
            info = "绿色代表我的客户；\n" +
                    "\n" +
                    "红色代表公海客户；\n" +
                    "\n" +
                    "蓝色代表他人客户；\n" +
                    "\n" +
                    "黑色代表注册从未下单的客户。";
        }else {
            info = "黄色代表有专员的客户；\n" +
                    "\n" +
                    "红色代表公海客户\n" +
                    "\n" +
                    "黑色代表注册从未下单的客户(公海中)";
        }
        mTextView.setText(info);
    }
}
