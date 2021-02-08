package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.tdjpartner.R;
import com.tdjpartner.utils.glide.ImageLoad;

import razerdp.basepopup.BasePopupWindow;

public class CheckHeadImagePopuWindow extends BasePopupWindow {
    View popupView;
    ImageView iv_head;

    public CheckHeadImagePopuWindow(Context context,String url) {
        super(context);
        iv_head=popupView.findViewById(R.id.iv_head);
        ImageLoad.loadImageViewLoding(url,iv_head,R.mipmap.xxz);
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
        popupView = createPopupById(R.layout.check_head_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}
