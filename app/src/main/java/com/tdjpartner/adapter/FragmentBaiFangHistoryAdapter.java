package com.tdjpartner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdjpartner.ui.fragment.BaiFangHistoryFragment;

import java.util.List;

public class FragmentBaiFangHistoryAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    public FragmentBaiFangHistoryAdapter(FragmentManager fm,List<String> Titles) {
        super(fm);
        mTitles = Titles;
    }

    @Override
    public Fragment getItem(int position) {
        return BaiFangHistoryFragment.newInstance(position);
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
