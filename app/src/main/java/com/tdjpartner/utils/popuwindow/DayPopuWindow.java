package com.tdjpartner.utils.popuwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.R;
import com.tdjpartner.model.AfterSalesType;
import com.tdjpartner.model.DayPopuWindowinfo;
import com.tdjpartner.widget.view.ScrollPickerAdapter;
import com.tdjpartner.widget.view.ScrollPickerView;
import com.tdjpartner.widget.view.WheelView;

import java.util.ArrayList;
import java.util.List;

import razerdp.basepopup.BasePopupWindow;

public class DayPopuWindow  extends BasePopupWindow {
    View popupView;
    WheelView  dayPicker;
    TextView bt_cancel,bt_ok,tv_type;
    String text;

    private DayPopuWindowListener listener;

    public void setDayPopuWindowListener(DayPopuWindowListener listener) {
        this.listener = listener;
    }
    public interface DayPopuWindowListener {
        void onOk(int s);
    }
    public DayPopuWindow(Context context,List<String> stringList,String type) {
        super(context);
        dayPicker=popupView.findViewById(R.id.day);
        tv_type=popupView.findViewById(R.id.tv_type);
        bt_cancel=popupView.findViewById(R.id.bt_cancel);
        bt_ok=popupView.findViewById(R.id.bt_ok);
        if (type.equals("call")){
            tv_type.setText("按拜访后第");
        }else {
            tv_type.setText("按下单后第");
        }
//        dayPicker.setLayoutManager(new LinearLayoutManager(getContext()));
/*        List<DayPopuWindowinfo> newstringList=new ArrayList<>();
        for (int i=0;i<stringList.size();i++){
            DayPopuWindowinfo dayPopuWindowinfo=new DayPopuWindowinfo();
            dayPopuWindowinfo.setDay(stringList.get(i));
            newstringList.add(dayPopuWindowinfo);
        }*/
//        LogUtils.e(newstringList);

        text=stringList.get(1);
        dayPicker.setItems(stringList,1);//init selected position is 1 初始选中位置为1
        dayPicker.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                LogUtils.e(item);
                text=item;
            }
        });
//        text=newstringList.get(1).getDay();
//        ScrollPickerAdapter.ScrollPickerAdapterBuilder<DayPopuWindowinfo> builder =
//                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<DayPopuWindowinfo>(getContext())
//                        .setDataList(newstringList)
//                        .selectedItemOffset(1)
//                        .visibleItemNumber(3)
//                        .setDivideLineColor("#FFFFFF")
//                        .setItemViewProvider(null)
//                        .setOnClickListener(new ScrollPickerAdapter.OnClickListener() {
//                            @Override
//                            public void onSelectedItemClicked(View v) {
//                                 text =((String) v.getTag());
//                                 LogUtils.e(text);
//                                if (text != null) {
//                                    Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//        ScrollPickerAdapter mScrollPickerAdapter = builder.build();
//        dayPicker.setAdapter(mScrollPickerAdapter);
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
