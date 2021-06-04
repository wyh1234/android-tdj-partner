package com.tdjpartner.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tdjpartner.R;
import com.tdjpartner.adapter.ListViewAdapter;
import com.tdjpartner.base.NetworkFragment;
import com.tdjpartner.model.TeamMemberData;
import com.tdjpartner.ui.activity.MemberStatisticsActivity;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
    private RxPermissions rxPermissions;

    String siteName = UserUtils.getInstance().getLoginBean().getSiteName(),
            realname = UserUtils.getInstance().getLoginBean().getRealname();//用户级别

    @Override
    protected int getLayoutId() {
        return R.layout.team_member_list_fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout.setColorSchemeResources(R.color.bbl_ff0000);
        swipeRefreshLayout.setOnRefreshListener(this::onRefresh);


        //初始列表
        adapter = new ListViewAdapter.Builder<TeamMemberData.Data>()
                .setResource(R.layout.team_member_list_item)
                .addChildId(R.id.tv_user, R.id.tv_phone)
                .setOnClickListener(this::onClick)
                .setInitView((data, convertView) -> {
                    System.out.println("view = " + view + ", savedInstanceState = " + savedInstanceState);
                    TextView textView;
                    textView = convertView.findViewById(R.id.tv_user);
                    String html = data.nickName + (data.size <= 0 ? "" : "<small>（" + data.size + "）</small>") + "<br/><font color='#dddddd'>" + data.abbreviation + "</font>";
                    textView.setText(Html.fromHtml(html, FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
                    textView.setTag(data.userId + "|" + data.nickName);

                    if (TextUtils.isEmpty(data.phone)) {
                        convertView.findViewById(R.id.tv_phone).setVisibility(View.GONE);
                    } else {
                        ((TextView) convertView.findViewById(R.id.tv_phone)).setText(data.phone);
                    }

                    if (data.size == 0 || getArgs().containsKey("nickName"))
                        convertView.findViewById(R.id.tv_sink).setVisibility(View.GONE);

                    convertView.setTag(data.size);
                })
                .build(getContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent = " + parent + ", view = " + view + ", position = " + position + ", id = " + id);
                TeamMemberData.Data data = (TeamMemberData.Data) parent.getAdapter().getItem(position);
                if (data.size != 0 && !getArgs().containsKey("nickName"))
                    leak(data.userId, data.other, data.grade, data.nickName);
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


        //初始化标题
        if (getArgs().containsKey("nickName")) {
            menu.setVisibility(View.GONE);
            getView().findViewById(R.id.divider).setVisibility(View.GONE);
            return;
        }
        menuAdapter = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, String data) {
                TextView textView = (TextView) baseViewHolder.itemView;
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setPadding(0, 8, 8, 0);
                textView.setText(Html.fromHtml(" <font color='#dddddd'>></font> " + data.substring(data.indexOf("|") + 1), FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);
            }
        };
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                System.out.println("baseQuickAdapter = " + baseQuickAdapter + ", view = " + view + ", i = " + i);
                System.out.println(baseQuickAdapter.getItem(i));
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
    }

    public void onRefresh() {
        getVMWithFragment().loading(TeamMemberData.class, getArgs());
    }

    private void leak(int userId, boolean other, int grade, String nickName) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("grade", grade);
        map.put("other", other);

        Bundle bundle = new Bundle();
        bundle.putSerializable("args", (Serializable) map);

        TeamMemberFragment fragment = new TeamMemberFragment();
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction()
                .replace(R.id.fl, fragment)
                .addToBackStack(System.currentTimeMillis() + "|" + nickName)
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
        textView.setPadding(16, 16, 8, 16);
        textView.setText(Html.fromHtml("<b>" + siteName.substring(0, siteName.length() - 1) + "战区</b><small>（" + realname + "</samll>）", FROM_HTML_MODE_LEGACY), TextView.BufferType.SPANNABLE);

        Drawable drawable = getResources().getDrawable(R.mipmap.city_one, null);
        drawable.setBounds(0, 0, 40, 40);
        textView.setCompoundDrawables(drawable, null, null, null);
        textView.setCompoundDrawablePadding(GeneralUtils.dipToPx(getContext(), 4));
        textView.setOnClickListener(v -> {
            getFragmentManager().popBackStack(null, POP_BACK_STACK_INCLUSIVE);
        });
        return textView;
    }

    public RxPermissions getRxPermissions() {
        if (rxPermissions == null) rxPermissions = new RxPermissions(getActivity());
        return rxPermissions;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_user:
                Intent intent = new Intent(getContext(), MemberStatisticsActivity.class);
                String data = (String) v.getTag();
                intent.putExtra("userId", Integer.parseInt(data.substring(0, data.indexOf("|"))));
                intent.putExtra("nickName", data.substring(data.indexOf("|") + 1));
                startActivity(intent);
                break;
            case R.id.tv_phone:
                GeneralUtils.action_call(getRxPermissions(), ((TextView) v).getText().toString(), getContext());
                break;
        }

    }
}