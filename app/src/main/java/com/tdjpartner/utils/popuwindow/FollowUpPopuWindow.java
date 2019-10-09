package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class FollowUpPopuWindow extends BasePopupWindow {
    private View popupView;
    private TextView tv_content;
    public FollowUpPopuWindow(Context context,String str) {
        super(context);
        tv_content=popupView.findViewById(R.id.tv_content);
        if (str.equals("SELECTPERSON")){
            tv_content.setText("您确定将客户"+'"'+123+'"'+"分配给456吗?");
        }
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
        popupView = createPopupById(R.layout.genjin_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
         return findViewById(R.id.popup_anima);
    }
}
