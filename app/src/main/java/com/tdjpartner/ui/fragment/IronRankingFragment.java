package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tdjpartner.R;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.HomeTopData;
import com.tdjpartner.utils.cache.UserUtils;

import static android.view.Gravity.CENTER;

/**
 * Created by LFM on 2021/4/22.
 */
public class IronRankingFragment extends NetworkFragment {

    private ArrayAdapter<HomeTopData.RegisterTimesTopListBean> arrayAdapter;
    private int entityId = UserUtils.getInstance().getLoginBean().getEntityId();//用户站点
    private int type = UserUtils.getInstance().getLoginBean().getType();//用户级别
    private int grade = UserUtils.getInstance().getLoginBean().getGrade();//用户级别
    private boolean isEntire, allowSkip;
    private int upNum = 6, offset;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化View
        ((TextView) view.findViewById(R.id.tv_ranking_four)).setText(grade == 3 ? "月总GMV" : "月日活");


        arrayAdapter = new ArrayAdapter<HomeTopData.RegisterTimesTopListBean>(getContext(), R.layout.adapter_ranking) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                System.out.println("position = " + position + ", convertView = " + convertView + ", parent = " + parent);
                convertView = getLayoutInflater().inflate(R.layout.adapter_ranking, parent, false);

                HomeTopData.RegisterTimesTopListBean bean = getItem(position);
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
                } else {

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
        //增加脚部
        TextView textView = new TextView(getContext());
        textView.setGravity(CENTER);
        textView.setPadding(24, 24, 24, 24);
        textView.setText("展开全部");
        textView.setTextSize(16);
        listView.addFooterView(textView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);
                if (id != -1) return;
                ((TextView) view).setText(isEntire ? "展开全部" : "收起全部");
                isEntire = !isEntire;
                getVMWithFragment().loading(HomeTopData.class, getArgs());
                showLoading();
            }
        });

        //加载数据
        getVMWithFragment().loadingWithNewLiveData(HomeTopData.class, getArgs())
                .observe(this, homeTopData -> {
                    dismissLoading();
                    arrayAdapter.clear();
                    if (isEntire) {
                        arrayAdapter.addAll(homeTopData.getRegisterTimesTopList());
                    } else {
                        for (int i = 0; i < 5 && i < homeTopData.getRegisterTimesTopList().size(); i++) {
                            if (homeTopData.getRegisterTimesTopList().size() > 4 && i > 4 && homeTopData.getRegisterTimesTopList().get(i).customerId != entityId)continue;
                            arrayAdapter.add(homeTopData.getRegisterTimesTopList().get(i));
                        }


                        for (int i = 0, j = 0; j < 6 && j < homeTopData.getRegisterTimesTopList().size(); i++) {
                            if (allowSkip && i >= upNum && homeTopData.getRegisterTimesTopList().get(i).customerId != entityId) {
                                offset++;
                                System.out.println("one|i = " + i + ", j = " + j);
                                continue;
                            }
                            System.out.println("two|i = " + i + ", j = " + j);
                            if (homeTopData.getRegisterTimesTopList().get(i).customerId == entityId)
                                allowSkip = false;
                            arrayAdapter.add(homeTopData.getRegisterTimesTopList().get(i));
                            if(++j > 4 && !allowSkip) break;
                        }
                    }
                });

    }
}