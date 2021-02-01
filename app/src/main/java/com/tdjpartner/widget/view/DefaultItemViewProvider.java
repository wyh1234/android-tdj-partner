package com.tdjpartner.widget.view;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.tdjpartner.R;

public class DefaultItemViewProvider implements IViewProvider<String> {
    @Override
    public int resLayout() {
        return R.layout.scroll_picker_default_item_layout;
    }

    @Override
    public void onBindView(@NonNull View view, @Nullable String text) {
        TextView tv = view.findViewById(R.id.tv_content);
        tv.setText(text);
        view.setTag(text);
        tv.setTextSize(12);
    }

    @Override
    public void updateView(@NonNull View itemView, boolean isSelected) {
        TextView tv = itemView.findViewById(R.id.tv_content);
        TextView tv1= itemView.findViewById(R.id.tv);
        TextView tv2 = itemView.findViewById(R.id.tv2);
        tv.setTextSize(isSelected ? 12 : 12);
        if (isSelected){
            tv1.setVisibility(View.VISIBLE);
            tv2.setVisibility(View.VISIBLE);
        }else {
            tv1.setVisibility(View.INVISIBLE);
            tv2.setVisibility(View.INVISIBLE);
        }
        tv.setTextColor(Color.parseColor(isSelected ? "#666666" : "#999999"));
    }
}
