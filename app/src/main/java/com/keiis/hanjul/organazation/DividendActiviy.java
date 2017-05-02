package com.keiis.hanjul.organazation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DividendActiviy extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dividend_activiy);

        ButterKnife.bind(this);

        titleView.setText("배번관리");
    }
}
