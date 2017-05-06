package com.keiis.hanjul.admin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.keiis.hanjul.user.OrganizationFragment;

/**
 * Created by hojun on 2017-05-03.
 */

public class ParticipationStatusAdapter extends FragmentStatePagerAdapter {
    public ParticipationStatusAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new OrganizationFragment();
        }else if(position == 1){
            return new ParticipationSubjectFragment();
        }else if(position == 2){
            return new ParticipationFragment();
        }else if(position == 3){
            return new ParticipationOrganizationFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
