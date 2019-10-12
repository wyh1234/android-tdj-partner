package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class SetHeadImagePopu extends BasePopupWindow {
    private View popupView;
    public SetHeadImagePopu(Context context) {
        super(context);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        popupView = createPopupById(R.layout.set_head_image_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}