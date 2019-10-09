package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class PartnerCheckDetailsPopu extends BasePopupWindow {
    private View popupView;
    public PartnerCheckDetailsPopu(Context context) {
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
        popupView = createPopupById(R.layout.partner_check_details_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}