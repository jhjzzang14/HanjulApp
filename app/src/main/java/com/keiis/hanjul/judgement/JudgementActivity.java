package com.keiis.hanjul.judgement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.keiis.hanjul.NetworkModel.ContestRetrieve;
import com.keiis.hanjul.NetworkModel.DataContestResult;
import com.keiis.hanjul.NetworkModel.MessageResult;
import com.keiis.hanjul.NetworkModel.result;
import com.keiis.hanjul.NetworkService.DefaultRestClient;
import com.keiis.hanjul.NetworkService.JudgementService;
import com.keiis.hanjul.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JudgementActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.cb_select)
    Spinner eventVIew;

    @BindView(R.id.judgement_name)
    EditText judgementNameEditText;

    @BindView(R.id.judgement_number)
    EditText judgementNumberEditText;

    @BindView(R.id.btn_record)
    Button recordButton;

    private DefaultRestClient<JudgementService> defaultRestClient;

    private JudgementService judgementService;

    private String userNo;

    private Bundle bundle;


    //기록 등록 버튼 클릭시 이벤트
    @OnClick(R.id.btn_record)
    void actionRecord(){
        String judgementNumber = judgementNumberEditText.getText().toString();

        String subject = (String)eventVIew.getSelectedItem();

        String judgementName = judgementNameEditText.getText().toString();

        //심판 번호 저장
        bundle.putString("judgement_num",judgementNumber);

        //심판 이름 저장
        bundle.putString("judgement_nm",judgementName);

        if(subject.equals("")){
            Toast.makeText(JudgementActivity.this,"대회를 선택해주세요",Toast.LENGTH_SHORT).show();
        }else if(judgementNumber.equals("")){
            Toast.makeText(JudgementActivity.this,"심판번호를 입력하세요",Toast.LENGTH_SHORT).show();
        }else if(judgementName.equals("")){
            Toast.makeText(JudgementActivity.this,"심판명을 입력하세요",Toast.LENGTH_SHORT).show();
        }else{
            //json 객체 생성
            JSONObject jsonObject = new JSONObject();

            try {
                jsonObject.put("user_no", userNo);

                jsonObject.put("contest_yy",bundle.getString("contest_yy"));

                jsonObject.put("contest_seq",bundle.getString("contest_seq"));

                jsonObject.put("judgement_num",judgementNumber);

                jsonObject.put("judgement_nm",judgementName);
            }catch (Exception e){
                e.getStackTrace();
            }

            //기록등록 화면으로 이동
            Call<MessageResult> commit = judgementService.setContestJudgementSave("ContestJudgementSave",jsonObject);

            commit.enqueue(new Callback<MessageResult>() {
                @Override
                public void onResponse(Call<MessageResult> call, Response<MessageResult> response) {
                    if(response.isSuccessful()){
                        Log.d("result",response.body().toString());
                        if(response.body().getResult().getResultCd().equals("OK")){
                            Intent intent = new Intent(JudgementActivity.this,RecordActivity.class);

                            intent.putExtra("contest",bundle);

                            startActivity(intent);
                        }
                    }else{
                        try{
                            Log.d("result",response.errorBody().string());
                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageResult> call, Throwable t) {
                    Log.d("result",t.getMessage());
                }
            });
        }
    }

    @OnClick(R.id.back)
    void onActionBack(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugdement);

        ButterKnife.bind(this);

        bundle = new Bundle();

        defaultRestClient = new DefaultRestClient<>();

        judgementService = defaultRestClient.getClient(JudgementService.class);

        titleView.setText("심판");

        userNo = getSharedPreferences("user",MODE_PRIVATE).getString("user_no","13");

        judgementNameEditText.addTextChangedListener(textWatcher);

        judgementNumberEditText.addTextChangedListener(textWatcher);

        //json 객체 생성
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("user_no", userNo);

            jsonObject.put("before_day","-30");
        }catch (Exception e){
            e.getStackTrace();
        }

        Call<DataContestResult<ContestRetrieve>> commit = judgementService.setContestRetrieve("ContestRetrieve",jsonObject);

        commit.enqueue(new Callback<DataContestResult<ContestRetrieve>>() {
            @Override
            public void onResponse(Call<DataContestResult<ContestRetrieve>> call, final Response<DataContestResult<ContestRetrieve>> response) {
                if(response.isSuccessful()){

                    final List<ContestRetrieve> contests = response.body().getDataArray();

                    List<String> contestNames = new ArrayList<String>();
                    //대회 목록 저장
                    for(ContestRetrieve contestName : contests){
                        contestNames.add(contestName.getSmart_contest_name());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(JudgementActivity.this,android.R.layout.simple_spinner_item, contestNames);

                    eventVIew.setAdapter(adapter);

                    eventVIew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            //아이템 선택시.
                            bundle.putString("contest_name",contests.get(position).getSmart_contest_name());

                            bundle.putString("contest_yy",contests.get(position).getContest_yy());

                            bundle.putString("contest_seq",contests.get(position).getContest_seq());
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

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if((!judgementNameEditText.getText().toString().equals("")) && (!judgementNumberEditText.getText().toString().equals(""))){
                recordButton.setBackgroundColor(Color.rgb(254,94,0));
                recordButton.setEnabled(true);
            }else{
                recordButton.setBackgroundColor(Color.rgb(239,239,244));
                recordButton.setEnabled(false);
            }
        }
    };
}
