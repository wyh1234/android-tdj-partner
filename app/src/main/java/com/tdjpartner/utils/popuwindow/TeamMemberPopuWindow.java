package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class TeamMemberPopuWindow extends BasePopupWindow {
    private View popupView;
    private TeamMemberPopuWindowListener listener;
    private TextView tv_one,tv_two,tv_three;

    public void setTeamMemberPopuWindowListener(TeamMemberPopuWindowListener listener) {
        this.listener = listener;
    }
    public interface TeamMemberPopuWindowListener {
        void onOk(int type);
    }
    public TeamMemberPopuWindow(Context context) {
        super(context);
        tv_one=popupView.findViewById(R.id.tv_one);
        tv_two=popupView.findViewById(R.id.tv_two);
        tv_three=popupView.findViewById(R.id.tv_three);
        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOk(3);
            }
        });
        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOk(2);
            }
        });
        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOk(2);
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
        popupView = createPopupById(R.layout.teammenber_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}