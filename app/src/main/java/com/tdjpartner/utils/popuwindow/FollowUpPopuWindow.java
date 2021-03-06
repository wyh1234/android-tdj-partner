package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.tdjpartner.R;

import razerdp.basepopup.BasePopupWindow;

public class FollowUpPopuWindow extends BasePopupWindow {
    private View popupView;
    private TextView tv_content;
    private Button btn_qx,btn_next;

    private FollowUpListener listener;

    public void setFollowUpListener(FollowUpListener listener) {
        this.listener = listener;
    }
    public interface FollowUpListener {
        void onCancel();
        void onOk();
    }
    public FollowUpPopuWindow(Context context,String str) {
        super(context);
        tv_content=popupView.findViewById(R.id.tv_content);
        btn_qx=popupView.findViewById(R.id.btn_qx);
        btn_next=popupView.findViewById(R.id.btn_next);
          if ("BAIFANG".contains(str)){
            tv_content.setText("恭喜您拜访客户成功");
            btn_qx.setText("知道了");
            btn_next.setText("返回上一页");
        }else if ("ADDBAIFANG".contains(str)){
            tv_content.setText("新增拜访客户成功！");
            btn_qx.setText("知道了");
            btn_next.setText("返回");
        }else {
            tv_content.setText("您确定要跟进客户“"+str+"”吗？");
        }
        btn_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCancel();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
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
        popupView = createPopupById(R.layout.genjin_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
         return findViewById(R.id.popup_anima);
    }
}
