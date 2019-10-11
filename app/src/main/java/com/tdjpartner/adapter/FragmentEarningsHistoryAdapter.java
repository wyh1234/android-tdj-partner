package com.tdjpartner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdjpartner.ui.fragment.EarningsHistoryFragment;
import com.tdjpartner.ui.fragment.StatisticsFragment;

import java.util.List;

public class FragmentEarningsHistoryAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;


    public FragmentEarningsHistoryAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;

    }

    @Override
    public Fragment getItem(int position) {
        return EarningsHistoryFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
