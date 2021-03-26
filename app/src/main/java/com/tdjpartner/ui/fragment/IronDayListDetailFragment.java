package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.adapter.IronAdapter;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by LFM on 2021/3/15.
 */
public class IronDayListDetailFragment extends Fragment implements View.OnClickListener {

    private int id;
    private IronAdapter ironAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListView listView = new ListView(getContext());
        listView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


        ironAdapter = new IronAdapter.Builder()
                .setResource(R.layout.iron_day_list_datail_item)
                .setInitView((data, convertView) -> {
                    System.out.println("data = " + data + ", convertView = " + convertView);

                    ((TextView) convertView.findViewById(R.id.priceNum)).setText(data.get(0));
                    ((TextView) convertView.findViewById(R.id.vegetablesNum)).setText(data.get(1));
                    ((TextView) convertView.findViewById(R.id.orderNum)).setText(data.get(2));

                    ((TextView) convertView.findViewById(R.id.tv_name)).setText(data.get(3));
                    ((TextView) convertView.findViewById(R.id.tv_boss)).setText(data.get(4));
                    ((TextView) convertView.findViewById(R.id.tv_regionCollNo)).setText(data.get(5));
                    ((TextView) convertView.findViewById(R.id.tv_username)).setText(data.get(6));
                    ((TextView) convertView.findViewById(R.id.tv_address)).setText(data.get(7));


                })
                .setOnClickListener(this)
                .build(getContext());
        listView.setAdapter(ironAdapter);
        listView.setDivider(null);
        return listView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        System.out.println("~~" + getClass().getSimpleName() + ".onViewCreated~~");
        System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);

        Random random = new Random();
        ironAdapter.clear();
        for (int i = 0; i < 5; i++) {
            ironAdapter.add(Arrays.asList("" + random.nextInt(1000),
                    "" + random.nextInt(1000),
                    "" + random.nextInt(1000),
                    "华天大酒店" + random.nextInt(1000),
                    "负责专员:李四" + random.nextInt(1000),
                    "D92区72-58" + random.nextInt(1000),
                    "李四" + random.nextInt(1000),
                    "武昌徐东大街100号" + random.nextInt(1000)));
        }
    }

    @Override
    public void onClick(View v) {

    }
}