package com.shu.shust2.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shu.shust2.fragment.ActivityFragment;
import com.shu.shust2.fragment.ClubFragment;
import com.shu.shust2.fragment.WelcomeFragment;

/**
 * Created by Leo on 2017/9/3.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private String mTitles[];

    public MainFragmentPagerAdapter(FragmentManager fm, String[] mTitles) {
        super(fm);
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new WelcomeFragment();
        else if (position == 1)
            return new ClubFragment();
        else
            return new ActivityFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
