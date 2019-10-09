package com.tdjpartner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdjpartner.ui.fragment.ClientListFragment;
import com.tdjpartner.ui.fragment.GoodsAndStoreFragment;

import java.util.List;

public class FragmentGoodsAndStoreAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitles;

    public FragmentGoodsAndStoreAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return GoodsAndStoreFragment.newInstance(position);
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
