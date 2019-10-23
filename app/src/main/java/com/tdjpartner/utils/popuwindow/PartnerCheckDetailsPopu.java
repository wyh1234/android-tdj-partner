package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class PartnerCheckDetailsPopu extends BasePopupWindow {
    private View popupView;
    private Button btn;
    private PartnerCheckDetailsListener listener;

    public void setPartnerCheckDetailsListener(PartnerCheckDetailsListener listener) {
        this.listener = listener;
    }
    public interface PartnerCheckDetailsListener {
        void onOk();
    }
    public PartnerCheckDetailsPopu(Context context) {
        super(context);
        btn=popupView.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOk();
            }
        });
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