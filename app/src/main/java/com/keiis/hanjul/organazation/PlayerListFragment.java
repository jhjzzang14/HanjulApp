package com.keiis.hanjul.organazation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hojun on 2017-04-30.
 */

public class PlayerListFragment extends Fragment{

    @BindView(R.id.tab_player_list)
    TabLayout tabLayout;

    @BindView(R.id.player_search)
    ViewPager viewPagerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_player_management,container,false);

        ButterKnife.bind(this,view);

        tabLayout.addTab(tabLayout.newTab().setText("선수"));
        tabLayout.addTab(tabLayout.newTab().setText("참가부"));
        tabLayout.addTab(tabLayout.newTab().setText("종목"));

        viewPagerView.setAdapter(new PlayerListAdapter(getChildFragmentManager()));

        viewPagerView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerView.setCurrentItem(tab.getPosition());;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }
}
