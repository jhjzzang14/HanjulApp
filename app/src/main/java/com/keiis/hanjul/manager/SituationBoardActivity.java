package com.keiis.hanjul.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 장호준 on 2017-05-03.
 */

public class SituationBoardActivity extends AppCompatActivity{

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.tab_management)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_board);

        ButterKnife.bind(this);

        titleView.setText("대회상황판");

        tabLayout.addTab(tabLayout.newTab().setText("차수"));
        tabLayout.addTab(tabLayout.newTab().setText("등급"));
        tabLayout.addTab(tabLayout.newTab().setText("상장"));
        tabLayout.addTab(tabLayout.newTab().setText("진행"));

        viewPager.setAdapter(new ManagementAdapter(getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    class ManagementAdapter extends FragmentStatePagerAdapter{

        public ManagementAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                return new OrderFragment();
            }else if(position == 1){
                return  new RatingFragment();
            }else if(position == 2){
                return new PrizeFragment();
            }else if(position ==3){
                return new ProgressFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
