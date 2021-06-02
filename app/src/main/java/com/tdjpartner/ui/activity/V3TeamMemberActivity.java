package com.tdjpartner.ui.activity;

import android.content.Intent;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private int userId = UserUtils.getInstance().getLoginBean().getLoginUserId();
    @Override
    protected void initView() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("grade", UserUtils.getInstance().getLoginBean().getGrade());
        map.put("other", "other");

        Bundle bundle = new Bundle();
        bundle.putSerializable("args", (Serializable) map);

        TeamMemberFragment fragment = new TeamMemberFragment();
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl, fragment)
                .addToBackStack("V3TeamMember" + userId)
                .commit();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.v3_team_member_layout;
    }
}
