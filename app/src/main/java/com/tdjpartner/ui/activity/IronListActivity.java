package com.tdjpartner.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tdjpartner.R;
import com.tdjpartner.ui.fragment.IronListFragment;
import com.tdjpartner.utils.GeneralUtils;
import com.tdjpartner.utils.cache.UserUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LFM on 2021/3/12.
 */
public class IronListActivity extends AppCompatActivity {

    long mPreTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iron_list_activity);
        IronListFragment fragment = new IronListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("args", (Serializable) getArges(new Date(), getIntent().getBooleanExtra("isDay", true), true));
        bundle.putBoolean("isDay", getIntent().getBooleanExtra("isDay", true));
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl, fragment, "one")
                .commit();
    }

    private Map<String, Object> getArges(Date date, boolean isDay, boolean isNext) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", UserUtils.getInstance().getLoginBean().getLoginUserId());
        map.put("type", isNext ? "next" : "up");
        if (isDay) {
            map.put("startTime", GeneralUtils.getTimeFilter(date));
            map.put("timeType", "day");
        } else {
            map.put("startTime", GeneralUtils.getMonthFilter(date));
            map.put("timeType", "month");
        }
        System.out.println("map = " + map);
        return map;
    }
}
