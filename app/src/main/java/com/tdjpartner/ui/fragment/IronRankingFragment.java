package com.tdjpartner.ui.fragment;

import android.arch.lifecycle.MediatorLiveData;
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
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import butterknife.BindView;

import static android.view.Gravity.CENTER;

/**
 * Created by LFM on 2021/4/22.
 */
public class IronRankingFragment extends NetworkFragment {
    @BindView(R.id.listView)
    ListView listView;

    private ArrayAdapter<HomeTopData.RegisterTimesTopListBean> arrayAdapter;
    private int entityId = UserUtils.getInstance().getLoginBean().getEntityId();//用户站点
    private boolean isEntire, allowSkip;
    private int upNum, offset;
    private TextView footerView;

    MediatorLiveData<Integer> liveData;

    public void setLiveData(MediatorLiveData<Integer> liveData) {
        this.liveData = liveData;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ranking_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化View
        if (getArgs().get("timeType").equals("day")) {

            switch ((int) getArgs().get("type")) {
                case 1:
                    ((TextView) view.findViewById(R.id.tv_ranking_four)).setText("GMV");
                    break;
                case 2:
                    ((TextView) view.findViewById(R.id.tv_ranking_four)).setText("注册数");
                    break;
                case 3:
                    ((TextView) view.findViewById(R.id.tv_ranking_four)).setText("新开数");
                    break;
            }
        } else {
            switch ((int) getArgs().get("type")) {
                case 1:
                    ((TextView) view.findViewById(R.id.tv_ranking_four)).setText("月总GMV");
                    break;
                case 2:
                    ((TextView) view.findViewById(R.id.tv_ranking_four)).setText("注册总数");
                    break;
                case 3:
                    ((TextView) view.findViewById(R.id.tv_ranking_four)).setText("新开总数");
                    break;
            }
        }


        arrayAdapter = new ArrayAdapter<HomeTopData.RegisterTimesTopListBean>(getContext(), R.layout.adapter_ranking) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                System.out.println("position = " + position + ", convertView = " + convertView + ", parent = " + parent);
                convertView = getLayoutInflater().inflate(R.layout.adapter_ranking, parent, false);

                HomeTopData.RegisterTimesTopListBean bean = getItem(position);
                System.out.println("bean = " + bean);

                TextView textView;
                if (bean.customerId == entityId) {
                    textView = convertView.findViewById(R.id.tv_ranking);
                    textView.setText("" + (position >= upNum ? position + 1 + offset : position + 1));
                    textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                    textView = convertView.findViewById(R.id.tv_db);
                    textView.setText(bean.partnerName);
                    textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                    textView = convertView.findViewById(R.id.tv_higher);
                    textView.setText(bean.name);
                    textView.setTextColor(getResources().getColor(R.color.orange_red, null));

                    textView = convertView.findViewById(R.id.tv_action);
                    textView.setTextColor(getResources().getColor(R.color.orange_red, null));
                } else {
                    textView = convertView.findViewById(R.id.tv_ranking);
                    textView.setText("" + (position >= upNum ? position + 1 + offset : position + 1));

                    textView = convertView.findViewById(R.id.tv_db);
                    textView.setText(bean.partnerName);

                    textView = convertView.findViewById(R.id.tv_higher);
                    textView.setText(bean.name);

                    textView = convertView.findViewById(R.id.tv_action);
                }

                switch ((int) getArgs().get("type")) {
                    case 1:
                        textView.setText("" + bean.monthAmount);
                        break;
                    case 2:
                        textView.setText("" + bean.dayRegisterTimes);
                        break;
                    case 3:
                        textView.setText("" + bean.firstOrderNum);
                        break;
                }
                return convertView;
            }
        };

        listView.setAdapter(arrayAdapter);
        listView.setNestedScrollingEnabled(true);

        //增加脚部
        footerView = new TextView(getContext());
        footerView.setGravity(CENTER);
        footerView.setPadding(24, 24, 24, 24);
        footerView.setText("展开全部");
        footerView.setTextSize(16);
        listView.addFooterView(footerView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);
                if (!view.equals(footerView)) return;
                showLoading();
                ((TextView) view).setText(isEntire ? "展开全部" : "收起全部");
                isEntire = !isEntire;
                getVMWithFragment().loading(HomeTopData.class, getArgs());
            }
        });

        //加载数据
        getVMWithFragment().loadingWithNewLiveData(HomeTopData.class, getArgs())
                .observe(this, homeTopData -> {
                    dismissLoading();
                    offset = 0;
                    allowSkip = true;
                    arrayAdapter.clear();

                    if (homeTopData.getRegisterTimesTopList().isEmpty()) {
                        footerView.setText("暂无数据");
                        updateListViewLayout();
                        return;
                    }

                    if (homeTopData.getRegisterTimesTopList().size() <= 5)
                        listView.removeFooterView(footerView);
                    if (isEntire) {
                        arrayAdapter.addAll(homeTopData.getRegisterTimesTopList());
                    } else {
                        for (int i = 0; i < homeTopData.getRegisterTimesTopList().size(); i++) {

                            if (homeTopData.getRegisterTimesTopList().get(i).customerId == entityId) {
                                allowSkip = false;
                            } else {
                                if (i >= 5) {
                                    if (++offset == 1) upNum = i;
                                    continue;
                                }
                            }
                            arrayAdapter.add(homeTopData.getRegisterTimesTopList().get(i));
                            if (arrayAdapter.getCount() == (i < 5 && !allowSkip ? 5 : 6)) break;
                        }
                    }
                    updateListViewLayout();
                });

    }

    private void updateListViewLayout() {
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = Math.min(GeneralUtils.dipToPx(getContext(), 300), 160 * (arrayAdapter.getCount() + 1));
        listView.setLayoutParams(layoutParams);
        liveData.postValue(layoutParams.height);
    }
}