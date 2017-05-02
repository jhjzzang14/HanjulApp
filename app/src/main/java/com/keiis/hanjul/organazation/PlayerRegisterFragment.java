package com.keiis.hanjul.organazation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keiis.hanjul.R;

import butterknife.ButterKnife;

/**
 * Created by hojun on 2017-04-30.
 */

public class PlayerRegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_register,container,false);

        ButterKnife.bind(this,view);

        return view;
    }
}
