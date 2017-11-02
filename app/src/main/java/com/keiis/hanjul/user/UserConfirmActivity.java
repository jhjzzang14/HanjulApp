package com.keiis.hanjul.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.keiis.hanjul.DoubleTabController;
import com.keiis.hanjul.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserConfirmActivity extends AppCompatActivity {

    private MainFragmentAdapter mainFragmentAdapter;

    @BindView(R.id.userToolbar)
    Toolbar userToolbar;

    @BindView(R.id.userTab)
    TabLayout userTab;

    @BindView(R.id.userViewpager)
    ViewPager userViewPager;

    //클래스 네임 정보 얻기
    @OnClick(R.id.userToolbar)
    void onClassName(){
        DoubleTabController.onTouchPressed(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_confirm);

        ButterKnife.bind(this);

        setToolbar();
        setUp();
        userTab.setupWithViewPager(userViewPager);
    }
    private void setToolbar(){
        setSupportActionBar(userToolbar); //AooCompatActivity actionbar를 설정
        getSupportActionBar().setTitle("ID/PW 찾기"); //타이틀 설정
    }

    public void setUp(){
        mainFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager());
        mainFragmentAdapter.addFragment(new IndividualFragment(),"본인폰");
        mainFragmentAdapter.addFragment(new UnindividualFragment(),"다른폰");
        userViewPager.setAdapter(mainFragmentAdapter);
    }

    class MainFragmentAdapter extends FragmentPagerAdapter{

        private List<String> mFragmentTitles = new ArrayList<>();
        private List<Fragment> mFragments = new ArrayList<>();

        public MainFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            mFragmentTitles.add(title);
            mFragments.add(fragment);
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
