package com.tdjpartner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tdjpartner.base.BaseFrgment;

import java.util.ArrayList;
import java.util.List;


public class MainTabAdapter extends FragmentStatePagerAdapter {

    private List<BaseFrgment> mFragments = new ArrayList<BaseFrgment>();

    public MainTabAdapter(List<BaseFrgment> fragmentList, FragmentManager fm) {
        super(fm);
        if (fragmentList != null){
            mFragments = fragmentList;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
