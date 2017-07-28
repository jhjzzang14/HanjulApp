package com.keiis.hanjul.organazation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.in.iua.thesimplestpageindicator.TheSimplestPageIndicatorView;

/**
 * Created by hojun on 2017-05-21.
 */

public class CompetitionSummaryActivity extends AppCompatActivity {
    @BindView(R.id.piPageIndicator)
    TheSimplestPageIndicatorView simplestPageIndicatorView;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tv_title)
    TextView titleView;

    //대회요강 이미지 갯수는 image_cnt
    private int imageCnt;

    @OnClick(R.id.back)
    void actionBack(){
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_summary);

        ButterKnife.bind(this);

        //타이틀
        titleView.setText("모집요강");

        //단체화면에서 대회요강 버튼 클릭시 image_cnt값을 전달 받음
        imageCnt = Integer.parseInt(getIntent().getStringExtra("image_cnt"));

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //현재 페이지 표시
                simplestPageIndicatorView.setCurrentSelectedItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            CompetitionSummaryFragment competitionSummaryFragment = new CompetitionSummaryFragment();

            Bundle bundle = new Bundle();

            bundle.putInt("position",position);

            competitionSummaryFragment.setArguments(bundle);

            return competitionSummaryFragment;
        }

        @Override
        public int getCount() {
            return imageCnt;
        }
    }
}
