package com.keiis.hanjul.organazation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrganizationActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @OnClick(R.id.btn_register)
    void actionRegister(){
        Intent intent = new Intent(OrganizationActivity.this,PlayerManagerActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.btn_dividend)
    void actionDividend(){
        Intent intent = new Intent(OrganizationActivity.this,DividendActiviy.class);

        startActivity(intent);
    }

    @OnClick(R.id.btn_record)
    void actionRecord(){
        Intent intent = new Intent(OrganizationActivity.this,RecordActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organazation);
        ButterKnife.bind(OrganizationActivity.this);

        titleView.setText("선수관리");
    }
}
