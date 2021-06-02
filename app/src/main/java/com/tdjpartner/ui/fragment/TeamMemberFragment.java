package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.TeamMemberData;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import butterknife.BindView;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

/**
 * Created by LFM on 2021/3/15.
 */
public class TeamMemberFragment extends NetworkFragment implements OnRefreshListener {

    @BindView(R.id.menu)
    RecyclerView menu;
    @BindView(R.id.listView)
    ListView listView;

    private ListViewAdapter<TeamMemberData.Data> adapter;

    int userType = UserUtils.getInstance().getLoginBean().getType();//用户类型
    int site = UserUtils.getInstance().getLoginBean().getSite();//用户站点
    int grade = UserUtils.getInstance().getLoginBean().getGrade();//用户级别

    @Override
    protected int getLayoutId() {
        return R.layout.team_member_list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Map<String, Object> map = new ArrayMap<>();
//        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
//        map.put("grade", UserUtils.getInstance().getLoginBean().getGrade());
//        map.put("other", "other");

        adapter = new ListViewAdapter.Builder<TeamMemberData.Data>()
                .setResource(R.layout.team_member_list_item)
                .setInitView((data, convertView) -> {
                    System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);

                    String html = "<b>" + data.roleName + "</b><small>（" + data.size + "）</small><br/><font color='#dddddd'>" + data.abbreviation + "</font>";
                    ((TextView) convertView.findViewById(R.id.user)).setText(Html.fromHtml(html, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                    ((TextView) convertView.findViewById(R.id.phone)).setText(TextUtils.isEmpty(data.phone)? "其他" : data.phone);

                })
                .build(getContext());
//        listView.setOnItemClickListener(this::onItemClick);
        listView.setAdapter(adapter);


        showLoading();
        getVMWithFragment().loadingWithNewLiveData(TeamMemberData.class, getArgs())
                .observe(this, teamMemberData -> {
                    System.out.println("teamMemberData = " + teamMemberData);

                    adapter.clear();
                    adapter.addAll(teamMemberData.teamMembers);

//        Map<String, Object> map = makeArges(userId, grade, new Date(), isDay, isNext);
//        bundle.putSerializable("args", (Serializable) map);
//        bundle.putBoolean("isDay", isDay);
//        NetListFragment fragment = new NetListFragment();
//        fragment.setArguments(bundle);
//
//        getFragmentManager().beginTransaction()
//                .add(R.id.fl, fragment)
//                .addToBackStack(tag += userId + "")
//                .commit();
                    dismissLoading();
                });

    }

    public void onRefresh(RefreshLayout refreshlayout) {
//        getVMWithFragment().loading(StatisticsDetails.class, getArgs());
    }


//    private Map<String, Object> makeArges(int userId, int grade, Date date, boolean isDay, boolean isNext) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("loginId", UserUtils.getInstance().getLoginBean().getLoginUserId());
//        map.put("userId", userId);
//        map.put("grade", grade);
//        map.put("type", isNext ? "next" : "up");
//        if (isDay) {
//            map.put("startTime", GeneralUtils.getTimeFilter(date));
//            map.put("timeType", "day");
//        } else {
//            map.put("startTime", GeneralUtils.getMonthFilter(date));
//            map.put("timeType", "month");
//        }
//        System.out.println("map = " + map);
//        return map;
//    }

//    private void leak(int userId, int grade, boolean isNext) {
//        Bundle bundle = new Bundle();
//        Map<String, Object> map = makeArges(userId, grade, new Date(), isDay, isNext);
//        bundle.putSerializable("args", (Serializable) map);
//        bundle.putBoolean("isDay", isDay);
//        NetListFragment fragment = new NetListFragment();
//        fragment.setArguments(bundle);
//
//        getFragmentManager().beginTransaction()
//                .add(R.id.fl, fragment)
//                .addToBackStack(tag += userId + "")
//                .commit();
//    }
}