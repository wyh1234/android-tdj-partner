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
import com.tdjpartner.base.BaseFrgment;
import com.tdjpartner.model.IronHomeTopData;
import com.tdjpartner.mvp.presenter.RankingFragmentPresenter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingFragment extends BaseFrgment<RankingFragmentPresenter> {

    private int type;
    private int userType;
    private int websiteId;
    private boolean isDay;
    private ArrayAdapter<List<String>> arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
            userType = getArguments().getInt("userType");
            websiteId = getArguments().getInt("websiteId");
            isDay = getArguments().getBoolean("isDay");
        }
    }

    @Override
    protected void initView(View view) {

        arrayAdapter = new ArrayAdapter<List<String>>(getContext(), R.layout.adapter_ranking) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                System.out.println("~~" + getClass().getSimpleName() + ".getView~~");
                System.out.println("position = " + position + ", convertView = " + convertView + ", parent = " + parent);

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


    }

    @Override
    protected void loadData() {

        Map<String, Object> map = new HashMap<>();
        map.put("userType", userType);
        map.put("timeType", isDay ? "day" : "month");
        map.put("websiteId", 3);
        map.put("type", type);


        mPresenter.homeTopData(map);
    }

    @Override
    protected RankingFragmentPresenter loadPresenter() {
        return new RankingFragmentPresenter();
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_ranking;
    }

    public void homeTopData_Success(IronHomeTopData homeTopData) {
        System.out.println("homeTopData = " + homeTopData.getRegisterTimesTopList());

        for (int i = 0; i < homeTopData.getRegisterTimesTopList().size(); i++) {
            arrayAdapter.add(Arrays.asList("" + (i + 1),
                    homeTopData.getRegisterTimesTopList().get(i).partnerName,
                    homeTopData.getRegisterTimesTopList().get(i).name,
                    "" + homeTopData.getRegisterTimesTopList().get(i).monthActiveNum));
        }

    }

    public void homeTopData_failed() {
//        stop();
    }

}