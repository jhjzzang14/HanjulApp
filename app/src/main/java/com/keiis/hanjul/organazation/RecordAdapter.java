package com.keiis.hanjul.organazation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hojun on 2017-05-01.
 */

public class RecordAdapter extends FragmentStatePagerAdapter {

    private Bundle bundle;

    public RecordAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){
            RecordSubjectFragment subjectFragment = new RecordSubjectFragment();
            subjectFragment.setArguments(bundle);

            return subjectFragment;
        }else if(position == 1){
            return new RecordPersonalFragment();
        }else if(position == 2){
            return new RecordBestPlayerFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
