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
import com.tdjpartner.utils.cache.UserUtils;

/**
 * Created by LFM on 2021/4/22.
 */
public class IronRankingFragment extends NetworkFragment {

    private ArrayAdapter<IronHomeTopData.RegisterTimesTopListBean> arrayAdapter;
    private int entityId = UserUtils.getInstance().getLoginBean().getEntityId();//用户站点
    private int type = UserUtils.getInstance().getLoginBean().getType();//用户级别
    private int grade = UserUtils.getInstance().getLoginBean().getGrade();//用户级别

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化View
        ((TextView) view.findViewById(R.id.tv_ranking_four)).setText(grade == 3 ? "月总GMV" : "月日活");




        arrayAdapter = new ArrayAdapter<IronHomeTopData.RegisterTimesTopListBean>(getContext(), R.layout.adapter_ranking) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                System.out.println("position = " + position + ", convertView = " + convertView + ", parent = " + parent);
                convertView = getLayoutInflater().inflate(R.layout.adapter_ranking, parent, false);

                IronHomeTopData.RegisterTimesTopListBean bean = getItem(position);
                System.out.println("bean = " + bean);

                if (type == 1) {
                    //网军
                    if (bean.customerId == entityId) {
                        TextView textView = convertView.findViewById(R.id.tv_ranking);
                        textView.setText("" + (position + 1));
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                        textView = convertView.findViewById(R.id.tv_db);
                        textView.setText(bean.partnerName);
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                        textView = convertView.findViewById(R.id.tv_higher);
                        textView.setText(bean.name);
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                        textView = convertView.findViewById(R.id.tv_action);
                        textView.setText("" + bean.monthAmount);
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));
                    } else {
                        TextView textView = convertView.findViewById(R.id.tv_ranking);
                        textView.setText("" + (position + 1));

                        textView = convertView.findViewById(R.id.tv_db);
                        textView.setText(bean.partnerName);

                        textView = convertView.findViewById(R.id.tv_higher);
                        textView.setText(bean.name);

                        textView = convertView.findViewById(R.id.tv_action);
                        textView.setText("" + bean.monthAmount);
                    }
                }else {

                    //铁军
                    if (bean.customerId == entityId) {
                        TextView textView = convertView.findViewById(R.id.tv_ranking);
                        textView.setText("" + (position + 1));
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                        textView = convertView.findViewById(R.id.tv_db);
                        textView.setText(bean.partnerName);
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                        textView = convertView.findViewById(R.id.tv_higher);
                        textView.setText(bean.name);
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                        textView = convertView.findViewById(R.id.tv_action);
                        textView.setText("" + bean.monthActiveNum);
                        textView.setTextColor(getResources().getColor(R.color.orange_red, null));
                    } else {
                        TextView textView = convertView.findViewById(R.id.tv_ranking);
                        textView.setText("" + (position + 1));

                        textView = convertView.findViewById(R.id.tv_db);
                        textView.setText(bean.partnerName);

                        textView = convertView.findViewById(R.id.tv_higher);
                        textView.setText(bean.name);

                        textView = convertView.findViewById(R.id.tv_action);
                        textView.setText("" + bean.monthActiveNum);
                    }
                }

                return convertView;
            }
        };
        ListView listView = view.findViewById(R.id.lv);
        listView.setAdapter(arrayAdapter);
        listView.setNestedScrollingEnabled(true);

        //加载数据
        getVMWithFragment().loadingWithNewLiveData(IronHomeTopData.class, getArgs())
                .observe(this, ironHomeTopData -> {
                    arrayAdapter.addAll(ironHomeTopData.getRegisterTimesTopList());
                });

    }
}