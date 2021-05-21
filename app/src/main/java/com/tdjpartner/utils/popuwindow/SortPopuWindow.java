package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.widget.view.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import razerdp.basepopup.BasePopupWindow;

public class SortPopuWindow extends BasePopupWindow {
    View popupView;
    WheelView dayPicker;
    TextView bt_cancel, bt_ok, tv_type;
    String text;
    int index;

    private DayPopuWindowListener listener;

    public void setDayPopuWindowListener(DayPopuWindowListener listener) {
        this.listener = listener;
    }

    public interface DayPopuWindowListener {
        void onOk(int s);
    }

    public SortPopuWindow(Context context, Map<String, String> arrayMap) {
        super(context);
        dayPicker = popupView.findViewById(R.id.day);
        tv_type = popupView.findViewById(R.id.tv_type);
        bt_cancel = popupView.findViewById(R.id.bt_cancel);
        bt_ok = popupView.findViewById(R.id.bt_ok);

        text = (String) arrayMap.values().toArray()[0];
        dayPicker.setItems(new ArrayList(arrayMap.values()), 0);
        dayPicker.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                LogUtils.e(item);
                text = item;
                index = selectedIndex;
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onOk(index);
                }
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
        popupView = createPopupById(R.layout.sort_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}
