package com.keiis.hanjul.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.keiis.hanjul.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyh on 2017-01-29.
 */

public class UserActivity extends AppCompatActivity {
    Toolbar userToolbar;
    TabLayout userTab;
    ViewPager userViewpager;
    MainFragmentAdapter mainFragmentAdapter;  //Fragment adapter선언

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_v01);

        ButterKnife.bind(this);
        //TabLayout userTab = (TabLayout)findViewById(R.id.userTab);
        //userTab.setTabMode(TabLayout.MODE_FIXED);

        userToolbar = (Toolbar)findViewById(R.id.userToolbar);
        userViewpager = (ViewPager)findViewById(R.id.userViewpager);
        userTab = (TabLayout)findViewById(R.id.userTab) ;

       setToolbar();

        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        setUpViewPager(userViewpager, mainFragmentAdapter); //viewPager에  adapter를 달아준다.
        userTab.setupWithViewPager(userViewpager);
    }

/*
    //ID/PWD 찾기
    @OnClick(R.id.btn_search)
    void actionSearch(){
        Intent intent = new Intent(UserActivity.this, UserConfirmActivity.class);

        startActivity(intent);
    }
*/

    private void setToolbar(){
        setSupportActionBar(userToolbar); //AooCompatActivity actionbar를 설정
        getSupportActionBar().setTitle("회원등록"); //타이틀 설정
    }

    /**
     * viewPager에 adapter를 설정해준다.
     */
    public void setUpViewPager(ViewPager viewPager, MainFragmentAdapter mainFragmentAdapter) {
        mainFragmentAdapter.addFragment(new OrganizationFragment(), "단체");
        mainFragmentAdapter.addFragment(new JudgementFragment(), "심판");
        mainFragmentAdapter.addFragment(new ManagerFragment(), "관리자");
        viewPager.setAdapter(mainFragmentAdapter);
    }

    //http://blog.daum.net/mailss/19 FragmentPagerAdapter 설명
    class MainFragmentAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment); //받은 프레그먼트를 리스트에 더해준다.
            mFragmentTitles.add(title);//받은 String을 리스트에 더해준다.
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
