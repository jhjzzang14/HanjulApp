package com.keiis.hanjul.admin;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keiis.hanjul.DoubleTabController;
import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParticipationStatusActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.tab_participation)
    TabLayout tabLayout;

    @OnClick(R.id.toolbar)
    void onClassName(){
        DoubleTabController.onTouchPressed(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participation_status);

        ButterKnife.bind(this);

        titleView.setText("참가 현황판");

        tabLayout.addTab(tabLayout.newTab().setText("단체별"));
        tabLayout.addTab(tabLayout.newTab().setText("종목별"));
        tabLayout.addTab(tabLayout.newTab().setText("참가부"));
        tabLayout.addTab(tabLayout.newTab().setText("단체전"));

        viewPager.setAdapter(new ParticipationStatusAdapter(getSupportFragmentManager()));

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
