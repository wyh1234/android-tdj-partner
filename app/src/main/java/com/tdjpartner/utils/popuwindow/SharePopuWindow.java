package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class SharePopuWindow extends BasePopupWindow {
    private View popupView;
    public SharePopuWindow(Context context) {
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
        popupView = createPopupById(R.layout.share_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
         return findViewById(R.id.popup_anima);
    }
}
