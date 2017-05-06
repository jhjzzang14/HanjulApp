package com.keiis.hanjul.admin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hojun on 2017-05-03.
 */

public class CompetitionManagementAdapter extends FragmentStatePagerAdapter {

    public CompetitionManagementAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return new DividendFragment();
        }else if(position == 1){
            return new DeadlineFragment();
        }else if(position == 2){
            return new JudgementSignFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
