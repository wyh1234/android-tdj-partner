package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class SelTypePopuWindow extends BasePopupWindow {
    private View popupView;
    private TextView tv_content;
    private TextView tv_content_one;


    private SelTypePopuWindowListener listener;

    public void setSelTypePopuWindowListener(SelTypePopuWindowListener listener) {
        this.listener = listener;
    }
    public interface SelTypePopuWindowListener {
        void onSelType(int type);
    }
    public SelTypePopuWindow(Context context) {
        super(context);
        tv_content=popupView.findViewById(R.id.tv_content);
        tv_content_one=popupView.findViewById(R.id.tv_content_one);
        tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelType(2);
            }
        });
        tv_content_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelType(1);
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
        popupView = createPopupById(R.layout.sel_type_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}
