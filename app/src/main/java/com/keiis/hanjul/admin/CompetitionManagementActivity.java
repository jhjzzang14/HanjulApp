package com.keiis.hanjul.admin;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetitionManagementActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tab_competition)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_management);

        ButterKnife.bind(this);

        titleView.setText("대회관리");

        tabLayout.addTab(tabLayout.newTab().setText("배번관리"));
        tabLayout.addTab(tabLayout.newTab().setText("마감일시"));
        tabLayout.addTab(tabLayout.newTab().setText("심판승인"));

        viewPager.setAdapter(new CompetitionManagementAdapter(getSupportFragmentManager()));

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
}
