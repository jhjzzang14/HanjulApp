package com.keiis.hanjul.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 장호준 on 2017-05-03.
 */

public class ManagerActivity extends AppCompatActivity {

    @OnClick(R.id.btn_manager_sign)
    void actionSign(){
        //이벤트 발생
        Intent intent = new Intent(ManagerActivity.this,ManagementActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.btn_user_password_submit)
    void actionPasswordSubmit(){
        //회원 패스워드 전송
    }

    @OnClick(R.id.btn_order_management)
    void actionOrder(){
        //차수관리
    }

    @OnClick(R.id.btn_process_management)
    void actionProcessManagement(){
        Intent intent = new Intent(ManagerActivity.this,SituationBoardActivity.class);

        startActivity(intent);
    }


    @BindView(R.id.tv_title)
    TextView titleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager_layout);

        ButterKnife.bind(this);

        titleView.setText("Admin");

    }
}
