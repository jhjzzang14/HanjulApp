package com.keiis.hanjul.organazation;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.tab_record)
    TabLayout tabLayout;

    @OnClick(R.id.back)
    void actionBack(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_acitivty);

        ButterKnife.bind(this);

        titleView.setText("기록 조회");

        tabLayout.addTab(tabLayout.newTab().setText("종목"));
        tabLayout.addTab(tabLayout.newTab().setText("개인"));
        tabLayout.addTab(tabLayout.newTab().setText("대회최고"));

        viewPager.setAdapter(new RecordAdapter(getSupportFragmentManager(),getIntent().getBundleExtra("contest")));

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
