package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.tdjpartner.R;
import com.tdjpartner.model.AfterSalesType;
import com.tdjpartner.widget.view.ScrollPickerAdapter;
import com.tdjpartner.widget.view.ScrollPickerView;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class DayPopuWindow  extends BasePopupWindow {
    View popupView;
    ScrollPickerView dayPicker;
    TextView bt_cancel,bt_ok;
    String text;

    private DayPopuWindowListener listener;

    public void setDayPopuWindowListener(DayPopuWindowListener listener) {
        this.listener = listener;
    }
    public interface DayPopuWindowListener {
        void onOk(int s);
    }
    public DayPopuWindow(Context context) {
        super(context);
        dayPicker=popupView.findViewById(R.id.day);
        bt_cancel=popupView.findViewById(R.id.bt_cancel);
        bt_ok=popupView.findViewById(R.id.bt_ok);
        List<String> stringList=new ArrayList<>();
        for (int i=0;i<7;i++){
            stringList.add((i+7)+"");
        }
        dayPicker.setLayoutManager(new LinearLayoutManager(getContext()));
        text=stringList.get(1);
        ScrollPickerAdapter.ScrollPickerAdapterBuilder<String> builder =
                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<String>(getContext())
                        .setDataList(stringList)
                        .selectedItemOffset(1)
                        .visibleItemNumber(3)
                        .setDivideLineColor("#FFFFFF")
                        .setItemViewProvider(null)
                        .setOnClickListener(new ScrollPickerAdapter.OnClickListener() {
                            @Override
                            public void onSelectedItemClicked(View v) {
                                 text = (String) v.getTag();
                                if (text != null) {
                                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
        ScrollPickerAdapter mScrollPickerAdapter = builder.build();
        dayPicker.setAdapter(mScrollPickerAdapter);
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
                if (listener!=null){
                    listener.onOk(Integer.parseInt(text));

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
        popupView = createPopupById(R.layout.day_popu_layout);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}
