package com.keiis.hanjul.organazation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keiis.hanjul.DoubleTabController;
import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DividendActiviy extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @OnClick(R.id.btn_dividend)
    void actionDividend(){
        //배번생성 클릭시 이벤트 처리
    }

    //클래스 네임 정보 얻기
    @OnClick(R.id.toolbar)
    void onClassName(){
        DoubleTabController.onTouchPressed(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dividend_activiy);

        ButterKnife.bind(this);

        titleView.setText("배번관리");
    }
}
