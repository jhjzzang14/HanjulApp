package com.keiis.hanjul.organazation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hojun on 2017-04-30.
 */

public class TabAdapter extends FragmentStatePagerAdapter {
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return new PlayerListFragment();
        }else if(position == 1){
            return new PlayerRegisterFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
