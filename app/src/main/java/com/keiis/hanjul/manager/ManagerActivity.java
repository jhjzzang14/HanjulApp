package com.keiis.hanjul.manager;

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
    }

    @BindView(R.id.btn_manager_sign)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manager_layout);

        ButterKnife.bind(this);

        textView.getText().toString();


    }
}
