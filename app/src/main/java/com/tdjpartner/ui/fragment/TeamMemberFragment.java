package com.tdjpartner.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.TeamMemberData;
import com.tdjpartner.model.V3HomeData;
import com.tdjpartner.utils.glide.ImageLoad;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;
import static android.text.Html.FROM_HTML_MODE_LEGACY;
import static android.view.Gravity.CENTER;

/**
 * Created by LFM on 2021/3/15.
 */
public class TeamMemberFragment extends NetworkFragment {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.menu)
    RecyclerView menu;
    @BindView(R.id.listView)
    ListView listView;
    String title;

    private ListViewAdapter<TeamMemberData.Data> adapter;
    private BaseQuickAdapter<String, BaseViewHolder> menuAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.team_member_list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);

        //标题
        int n = getFragmentManager().getBackStackEntryCount();
        System.out.println("n = " + n);
        for (int i = 0; i < n; i++) {
            FragmentManager.BackStackEntry entry = getFragmentManager().getBackStackEntryAt(i);
            System.out.println("getBreadCrumbShortTitle is " + entry.getBreadCrumbTitle());
        }

        menuAdapter = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1) {

            @Override
            protected void convert(BaseViewHolder baseViewHolder, String data) {
                TextView textView = (TextView) baseViewHolder.itemView;
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setPadding(0, 8, 8, 0);
//                textView.setBackgroundColor(getResources().getColor(R.color.blue, null));
                textView.setText(Html.fromHtml(" <font color='#dddddd'>></font> " + data.substring(0, data.indexOf('|')), FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
            }
        };
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                System.out.println("baseQuickAdapter = " + baseQuickAdapter + ", view = " + view + ", i = " + i);
                getFragmentManager().popBackStack(baseQuickAdapter.getItem(i).toString(), 0);
            }
        });
        menuAdapter.addHeaderView(getTextView(), 0, LinearLayoutManager.HORIZONTAL);

        menu.setAdapter(menuAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        menu.setLayoutManager(linearLayoutManager);
        for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
            System.out.println("name = " + getFragmentManager().getBackStackEntryAt(i).getName());
            menuAdapter.addData(getFragmentManager().getBackStackEntryAt(i).getName());
        }


        //列表
        adapter = new ListViewAdapter.Builder<TeamMemberData.Data>()
                .setResource(R.layout.team_member_list_item)
                .setInitView((data, convertView) -> {
                    System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);
                    String html = data.roleName + "<small>（" + data.size + "）</small><br/><font color='#dddddd'>" + data.abbreviation + "</font>";
                    ((TextView) convertView.findViewById(R.id.user)).setText(Html.fromHtml(html, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                    if (TextUtils.isEmpty(data.phone))
                        convertView.findViewById(R.id.phone).setVisibility(View.GONE);
                    convertView.setTag(data.size);

                })
                .build(getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);
                TeamMemberData.Data data = (TeamMemberData.Data) parent.getAdapter().getItem(position);
                if ((int) view.getTag() > 0) leak(data.userId, data.other, data.grade, null);
            }
        });
        listView.setAdapter(adapter);


        showLoading();
        getVMWithFragment().loadingWithNewLiveData(TeamMemberData.class, getArgs())
                .observe(this, teamMemberData -> {
                    System.out.println("teamMemberData = " + teamMemberData);
                    adapter.clear();
                    adapter.addAll(teamMemberData.teamMembers);
                    title = teamMemberData.title;
                    dismissLoading();
                    stop();
                });

    }

    public void onRefresh() {
        getVMWithFragment().loading(TeamMemberData.class, getArgs());
    }

    private void leak(int userId, boolean other, int grade, String nickName) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("grade", grade);
        map.put("other", other);
        if (!TextUtils.isEmpty(nickName)) map.put("nickName", nickName);

        Bundle bundle = new Bundle();
        bundle.putSerializable("args", (Serializable) map);

        TeamMemberFragment fragment = new TeamMemberFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.fl, fragment)
//                .setBreadCrumbTitle(title)
                .addToBackStack(title + "|" + userId)
                .commit();
    }

    public void stop() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private TextView getTextView() {
        TextView textView = new TextView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = CENTER;
        textView.setLayoutParams(layoutParams);
        textView.setPadding(40, 40, 8, 40);
        textView.setText(Html.fromHtml("<b>战区</b>", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
//        textView.setBackgroundColor(getResources().getColor(R.color.orange_red, null));
        textView.setOnClickListener(v -> {
            for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
                getFragmentManager().popBackStack(i, POP_BACK_STACK_INCLUSIVE);
            }
        });
        return textView;
    }
}