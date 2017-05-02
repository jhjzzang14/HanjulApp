package com.keiis.hanjul.organazation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hojun on 2017-05-01.
 */

public class PlayerListAdapter extends FragmentStatePagerAdapter {
    public PlayerListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            return new PlayerNameListFragment();
        }else if(position == 1){
            return new PlayerOrganizationListFragment();
        }else if(position == 2){
            return new PlayerSubjectListFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
