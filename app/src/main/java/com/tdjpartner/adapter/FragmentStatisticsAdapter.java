package com.tdjpartner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdjpartner.ui.fragment.ClientListFragment;
import com.tdjpartner.ui.fragment.StatisticsFragment;

import java.util.List;

public class FragmentStatisticsAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;
    private String title;

    public FragmentStatisticsAdapter(FragmentManager fm, List<String> titles,String title) {
        super(fm);
        mTitles = titles;
        this.title=title;
    }

    @Override
    public Fragment getItem(int position) {
        return StatisticsFragment.newInstance(position,title);
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
