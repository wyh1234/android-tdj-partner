package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.IronHomeTopData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LFM on 2021/4/22.
 */
public class RankingFragment extends NetworkFragment {

    private int type;
    private int userType;
    private int websiteId;
    private boolean isDay;
    private ArrayAdapter<List<String>> arrayAdapter;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            type = getArguments().getInt("type");
//            userType = getArguments().getInt("userType");
//            websiteId = getArguments().getInt("websiteId");
//            isDay = getArguments().getBoolean("isDay");
//        }
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化View
        arrayAdapter = new ArrayAdapter<List<String>>(getContext(), R.layout.adapter_ranking) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.adapter_ranking, parent, false);

                List<String> data = getItem(position);
                System.out.println("data = " + data);
                TextView textView = convertView.findViewById(R.id.tv_ranking);
                textView.setText(data.get(0));
                textView = convertView.findViewById(R.id.tv_db);
                textView.setText(data.get(1));
                textView = convertView.findViewById(R.id.tv_higher);
                textView.setText(data.get(2));
                textView = convertView.findViewById(R.id.tv_action);
                textView.setText(data.get(3));

                return convertView;
            }
        };
        ListView listView = view.findViewById(R.id.lv);
        listView.setAdapter(arrayAdapter);
        listView.setNestedScrollingEnabled(true);


        //加载数据
        getVMWithFragment().loadingWithNewLiveData(IronHomeTopData.class, getArgs())
                .observe(this, ironHomeTopData -> {
                    for (int i = 0; i < ironHomeTopData.getRegisterTimesTopList().size(); i++) {
                        arrayAdapter.add(Arrays.asList("" + (i + 1),
                                ironHomeTopData.getRegisterTimesTopList().get(i).partnerName,
                                ironHomeTopData.getRegisterTimesTopList().get(i).name,
                                "" + ironHomeTopData.getRegisterTimesTopList().get(i).monthActiveNum));
                    }
                });

    }
}