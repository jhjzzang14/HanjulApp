package com.keiis.hanjul.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ButterKnife.bind(this);

        titleView.setText("Admin");
    }

    @OnClick(R.id.btn_record_situation)
    void actionRecordSituation(){
        Intent intent = new Intent(AdminActivity.this,RecordSituationBoardActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.btn_participation)
    void actionParticipation(){
        Intent intent = new Intent(AdminActivity.this,ParticipationStatusActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.btn_competition_management)
    void actionCompetitionManagement(){
        Intent intent = new Intent(AdminActivity.this,CompetitionManagementActivity.class);

        startActivity(intent);
    }
}
