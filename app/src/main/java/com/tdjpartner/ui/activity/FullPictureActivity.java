package com.tdjpartner.ui.activity;

import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.glide.ImageLoad;

import butterknife.BindView;

public class FullPictureActivity extends NetworkActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void initView() {
        imageView.setTransitionName("share");
    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra("url");
        if(TextUtils.isEmpty(url))return;
        ImageLoad.loadImageViewLoding(url, imageView);
    }

    @Override
    protected int getLayoutId() {
        long duration = 500l;
        Window window = getWindow();
        window.setSharedElementEnterTransition(new ChangeBounds().setDuration(duration));
        window.setTransitionBackgroundFadeDuration(duration);
        return R.layout.full_picture_activity;
    }
}
