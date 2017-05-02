package com.keiis.hanjul.organazation;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keiis.hanjul.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerManagerActivity extends AppCompatActivity {

    @BindView(R.id.tab_player_register)
    TabLayout tabLayout;

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.viewpager)
    ViewPager viewPagerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_register);
        ButterKnife.bind(this);

        titleView.setText("선수 관리");
        viewPagerView.setAdapter(new TabAdapter(getSupportFragmentManager()));

        tabLayout.addTab(tabLayout.newTab().setText("선수목록"));
        tabLayout.addTab(tabLayout.newTab().setText("선수등록"));

        viewPagerView.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerView.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private ArrayList<Fragment> getFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new PlayerListFragment());
        fragments.add(new PlayerRegisterFragment());

        return fragments;
    }
}
