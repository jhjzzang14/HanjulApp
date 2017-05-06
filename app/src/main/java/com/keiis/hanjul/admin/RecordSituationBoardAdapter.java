package com.keiis.hanjul.admin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hojun on 2017-05-03.
 */

public class RecordSituationBoardAdapter extends FragmentStatePagerAdapter {
    public RecordSituationBoardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new RecordObjectFragment();
        }else if(position == 1){
            return new RecordRankFragment();
        }else if(position == 2){
            return new RecordPersonalFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
