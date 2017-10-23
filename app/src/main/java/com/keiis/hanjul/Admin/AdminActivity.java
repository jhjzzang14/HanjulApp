package com.keiis.hanjul.admin;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.keiis.hanjul.R;
import com.keiis.hanjul.user.UserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.activity_admin)
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ButterKnife.bind(this);

        titleView.setText("관리자");
    }

    @OnClick(R.id.my_info)
    void actionMyInfo(){
        Intent intent = new Intent(AdminActivity.this, UserActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.menu)
    void actionMenu(){
        drawerLayout.openDrawer(Gravity.START);
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

    //단체별 배번관리
    @OnClick(R.id.btn_dividend)
    void actionDividend(){
        Intent intent = new Intent(AdminActivity.this,CompetitionManagementActivity.class);

        intent.putExtra("contents_name","dividend");

        startActivity(intent);
    }

    //마감일시 관리
    @OnClick(R.id.btn_deadline)
    void actionDeadline(){
        Intent intent = new Intent(AdminActivity.this,CompetitionManagementActivity.class);

        intent.putExtra("contents_name","deadline");

        startActivity(intent);
    }

    //심판승인
    @OnClick(R.id.btn_judgement_sign)
    void actionJudgementSign(){
        Intent intent = new Intent(AdminActivity.this,CompetitionManagementActivity.class);

        intent.putExtra("contents_name","judgement_sign");

        startActivity(intent);
    }
}
