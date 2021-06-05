package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tdjpartner.R;
import com.tdjpartner.adapter.TeamMemberAdapter;
import com.tdjpartner.adapter.TeamMemberHorizontalAdapter;
import com.tdjpartner.base.BaseActivity;
import com.tdjpartner.base.NetworkActivity;
import com.tdjpartner.model.NewMyTeam;
import com.tdjpartner.model.TeamMemberData;
import com.tdjpartner.mvp.presenter.TeamMemberPresenter;
import com.tdjpartner.ui.fragment.NetListFragment;
import com.tdjpartner.ui.fragment.TeamMemberFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.ListUtils;
import com.tdjpartner.utils.cache.UserUtils;
import com.tdjpartner.utils.statusbar.Eyes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class V3TeamMemberActivity extends NetworkActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.search_text)
    EditText search_text;
    int searchNum = 0;

    private int userId = UserUtils.getInstance().getLoginBean().getLoginUserId();
    private int grade = UserUtils.getInstance().getLoginBean().getGrade();

    @OnClick({R.id.btn_back, R.id.tv_list_type})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                if (searchNum-- > 0) {
                    getSupportFragmentManager().popBackStack();
                    if (searchNum == 0) tv_title.setText("我的团队");
                } else {
                    finish();
                }
                break;
            case R.id.tv_list_type:
                if (TextUtils.isEmpty(search_text.getText())) return;
                searchNum++;
                tv_title.setText("搜索我的团队");
                Map<String, Object> map = new HashMap<>();
                map.put("userId", userId);
                map.put("grade", grade);
                map.put("other", false);
                System.out.println("search_text.getText() = " + search_text.getText());
                map.put("nickName", search_text.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putSerializable("args", (Serializable) map);

                TeamMemberFragment fragment = new TeamMemberFragment();
                fragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    @Override
    protected void initView() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("grade", UserUtils.getInstance().getLoginBean().getGrade());
        map.put("other", false);
        System.out.println("map = " + map);

        Bundle bundle = new Bundle();
        bundle.putSerializable("args", (Serializable) map);

        TeamMemberFragment fragment = new TeamMemberFragment();
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl, fragment)
                .commit();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.v3_team_member_layout;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO bug
//        if(--searchNum == 0) tv_title.setText("我的团队");
    }
}
