package com.keiis.hanjul.manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 장호준 on 2017-05-03.
 */

public class ManagementActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.tab_management)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private String contents_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        ButterKnife.bind(this);

        contents_name = getIntent().getStringExtra("contents_name");

        titleView.setText("Admin");

        tabLayout.addTab(tabLayout.newTab().setText("관리자 승인"));
        tabLayout.addTab(tabLayout.newTab().setText("회원패스워드"));

        viewPager.setAdapter(new ManagementAdapter(getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        if(contents_name.equals("signment")){
            viewPager.setCurrentItem(0);
        }else if(contents_name.equals("password")){
            viewPager.setCurrentItem(1);
        }


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
                return new ManagerSignFragment();
            }else if(position == 1){
                return new UserPasswordFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
