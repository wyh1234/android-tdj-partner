package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class SetHeadImagePopu extends BasePopupWindow {
    private View popupView;
    private TextView tv_qx,tv_ok;
    private SetHeadImageListener listener;

    public void setSetHeadImageListener(SetHeadImageListener listener) {
        this.listener = listener;
    }
    public interface SetHeadImageListener {
         void onCancel();
        void onUpload();
    }
    public SetHeadImagePopu(Context context) {
        super(context);
        tv_qx=popupView.findViewById(R.id.tv_qx);
        tv_ok=popupView.findViewById(R.id.tv_ok);
        tv_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUpload();

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
        popupView = createPopupById(R.layout.set_head_image_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}