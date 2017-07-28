package com.keiis.hanjul.organazation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.keiis.hanjul.NetworkModel.ContestOrgnRetrieve;
import com.keiis.hanjul.NetworkModel.ContestRetrieve;
import com.keiis.hanjul.NetworkModel.DataContestResult;
import com.keiis.hanjul.NetworkModel.DataResult;
import com.keiis.hanjul.NetworkService.DefaultRestClient;
import com.keiis.hanjul.NetworkService.JudgementService;
import com.keiis.hanjul.NetworkService.OrganizationService;
import com.keiis.hanjul.R;
import com.keiis.hanjul.judgement.JudgementActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrganizationActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.subjectSpinner)
    Spinner subjectSpinner;

    @BindView(R.id.groupSpinner)
    Spinner groupSpinner;

    private DefaultRestClient<OrganizationService> defaultRestClient;

    private OrganizationService organizationService;

    private String userNo;

    private Bundle bundle;

    private String organCd;

    private String imageCnt;

    @OnClick(R.id.back)
    void actionBack(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organazation);
        ButterKnife.bind(OrganizationActivity.this);

        bundle = new Bundle();

        titleView.setText("선수관리");

        userNo = getSharedPreferences("user",MODE_PRIVATE).getString("user_no","13");

        defaultRestClient = new DefaultRestClient<>();

        organizationService = defaultRestClient.getClient(OrganizationService.class);

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("user_no",userNo);

            jsonObject.put("before_day","-30");
        }catch (Exception e){
            e.getStackTrace();
        }

        //대회 목록 조회
        Call<DataContestResult<ContestRetrieve>> commit = organizationService.setContestRetrieve("ContestRetrieve",jsonObject);

        commit.enqueue(new Callback<DataContestResult<ContestRetrieve>>() {
            @Override
            public void onResponse(Call<DataContestResult<ContestRetrieve>> call, Response<DataContestResult<ContestRetrieve>> response) {
                if(response.isSuccessful()){
                    final List<ContestRetrieve> contests = response.body().getDataArray();

                    List<String> contestNames = new ArrayList<String>();
                    //대회 목록 저장
                    for(ContestRetrieve contestName : contests){
                        contestNames.add(contestName.getSmart_contest_name());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(OrganizationActivity.this,android.R.layout.simple_spinner_item, contestNames);

                    subjectSpinner.setAdapter(adapter);

                    subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                            JSONObject jsonContest = new JSONObject();
                            try{
                                jsonContest.put("user_no",userNo);
                                jsonContest.put("contest_yy",contests.get(position).getContest_yy());
                                jsonContest.put("contest_seq",contests.get(position).getContest_seq());

                                imageCnt = contests.get(position).getImage_cnt();

                                bundle.putString("contest_yy",contests.get(position).getContest_yy());

                                bundle.putString("contest_seq",contests.get(position).getContest_seq());
                            }catch (Exception e){
                                e.getStackTrace();
                            }
                            //단체 목록 조회
                            Call<DataResult<ContestOrgnRetrieve>> organizationCommit = organizationService.setContestOrgnRetrieve("ContestOrgnRetrieve",jsonContest);

                            organizationCommit.enqueue(new Callback<DataResult<ContestOrgnRetrieve>>() {
                                @Override
                                public void onResponse(Call<DataResult<ContestOrgnRetrieve>> call, Response<DataResult<ContestOrgnRetrieve>> response) {
                                    if(response.isSuccessful()){
                                        final List<ContestOrgnRetrieve> organContests = response.body().getDataArray();

                                        List<String> contestNames = new ArrayList<String>();
                                        //대회 목록 저장
                                        for(ContestOrgnRetrieve contestName : organContests){
                                            contestNames.add(contestName.getOrgn_name());
                                        }
                                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(OrganizationActivity.this,android.R.layout.simple_spinner_item, contestNames);

                                        groupSpinner.setAdapter(adapter);

                                        groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                organCd = organContests.get(position).getOrgn_cd();

                                                bundle.putString("organ_cd",organCd);
                                            }

                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {

                                            }
                                        });

                                    }
                                }

                                @Override
                                public void onFailure(Call<DataResult<ContestOrgnRetrieve>> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DataContestResult<ContestRetrieve>> call, Throwable t) {

            }
        });
    }

    //선수등록 및 종목 신청
    @OnClick(R.id.btn_register)
    void actionRegister(){
        Intent intent = new Intent(OrganizationActivity.this,PlayerManagerActivity.class);

        startActivity(intent);
    }

    //배번관리
    @OnClick(R.id.btn_dividend)
    void actionDividend(){
        Intent intent = new Intent(OrganizationActivity.this,DividendActiviy.class);

        startActivity(intent);
    }

    //기록조회 버튼 클릭시
    @OnClick(R.id.btn_record)
    void actionRecord(){
        Intent intent = new Intent(OrganizationActivity.this,RecordActivity.class);

        intent.putExtra("contest",bundle);

        startActivity(intent);
    }

    //대회 요강
    @OnClick(R.id.btn_subject)
    void actionSubject(){
        Intent intent = new Intent(OrganizationActivity.this,CompetitionSummaryActivity.class);

        intent.putExtra("image_cnt",imageCnt);

        startActivity(intent);
    }
}
