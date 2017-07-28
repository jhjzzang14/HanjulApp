package com.keiis.hanjul.judgement;

import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.keiis.hanjul.NetworkModel.BodyExistPlayer;
import com.keiis.hanjul.NetworkModel.ContestGameList;
import com.keiis.hanjul.NetworkModel.ContestRetrieve;
import com.keiis.hanjul.NetworkModel.DataResult;
import com.keiis.hanjul.NetworkModel.ExistPlayer;
import com.keiis.hanjul.NetworkModel.ExistPlayerSave;
import com.keiis.hanjul.NetworkModel.GameDegree;
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

public class RecordActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.subject_list)
    Spinner subjectSpinnerVIew;

    @BindView(R.id.et_record)
    EditText recordEditText;

    private Bundle bundle;

    private String userNo;

    private String contestYY;

    private String contestSeq;

    private String gameCd;

    private String judgementNumber;

        private int peopleCnt;

    private JSONObject jsonObject1 = new JSONObject();

    @BindView(R.id.tv_record)
    TextView recordView;

    //배번 관리
    @BindView(R.id.et_player_1)
    EditText player1EditText;
    @BindView(R.id.et_player_2)
    EditText player2EditText;
    @BindView(R.id.et_player_3)
    EditText player3EditText;
    @BindView(R.id.et_player_4)
    EditText player4EditText;

    @BindView(R.id.tv_player_1_name)
    TextView player1NameTextView;
    @BindView(R.id.tv_player_2_name)
    TextView player2NameTextView;
    @BindView(R.id.tv_player_3_name)
    TextView player3NameTextView;
    @BindView(R.id.tv_player_4_name)
    TextView player4NameTextView;

    @BindView(R.id.btn_record_save)
    Button recordSaveButton;

    @BindView(R.id.btn_identity)
    Button playerConfirmButton;

    @BindView(R.id.tv_final_degree)
    TextView finalDegreeTextView;

    private String lastDegree;

    private DefaultRestClient<JudgementService> defaultRestClient;

    private JudgementService judgementService;

    @OnClick(R.id.back)
    void actionBack(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);

        defaultRestClient = new DefaultRestClient<>();

        // 심판화면에서 선택한 대회에 대한 정보
        bundle = getIntent().getBundleExtra("contest");

        // 심판화면에서 가지고 온 정보중 대회명 타이틀에 표시
        titleView.setText(bundle.getString("contest_name"));

        contestYY = bundle.getString("contest_yy");

        contestSeq = bundle.getString("contest_seq");

        judgementNumber = bundle.getString("judgement_num");

        judgementService = defaultRestClient.getClient(JudgementService.class);

        userNo = getSharedPreferences("user",MODE_PRIVATE).getString("user_no","13");

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("user_no", userNo);
            jsonObject.put("contest_yy",contestYY);
            jsonObject.put("contest_seq",contestSeq);
            jsonObject.put("people_cnt","4");
        }catch (Exception e){
            e.getStackTrace();
        }

        Call<DataResult<ContestGameList>> commit = judgementService.setContestGameList("ContestGameList",jsonObject);

        commit.enqueue(new Callback<DataResult<ContestGameList>>() {
            @Override
            public void onResponse(Call<DataResult<ContestGameList>> call, final Response<DataResult<ContestGameList>> response) {
                if(response.isSuccessful()){
                    final List<ContestGameList> contestGameLists = response.body().getDataArray();

                    List<String> gameNames = new ArrayList<String>();
                    //대회 목록 저장
                    for(ContestGameList contestGameList : contestGameLists){
                        gameNames.add(contestGameList.getGame_name());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(RecordActivity.this,android.R.layout.simple_spinner_item, gameNames);

                    subjectSpinnerVIew.setAdapter(adapter);

                    subjectSpinnerVIew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            gameCd = contestGameLists.get(position).getGame_cd();
                            //종목별 선수 인원수 확인
                            peopleCnt = Integer.parseInt(contestGameLists.get(position).getPeople_cnt());
                            setPeopleSetting(peopleCnt);

                            actionAddDegree();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DataResult<ContestGameList>> call, Throwable t) {

            }
        });
    }

    //선수 확인 버튼 클릭시
    @OnClick(R.id.btn_identity)
    void actionIdentity(){
        if(peopleCnt==1){
            if(player1EditText.getText().toString().equals("")){
                Toast.makeText(RecordActivity.this,"선수 1명이 필요합니다",Toast.LENGTH_SHORT).show();
            }else{
                setPeopleConfirm();
            }
        }else if(peopleCnt==2){
            if(player1EditText.getText().toString().equals("") || player2EditText.getText().toString().equals("")){
                Toast.makeText(RecordActivity.this,"선수 2명이 필요합니다",Toast.LENGTH_SHORT).show();
            }else{
                setPeopleConfirm();
            }
        }else if(peopleCnt==3){
            if(player1EditText.getText().toString().equals("") || player2EditText.getText().toString().equals("") || player3EditText.getText().toString().equals("")){
                Toast.makeText(RecordActivity.this,"선수 3명이 필요합니다",Toast.LENGTH_SHORT).show();
            }else{
                setPeopleConfirm();
            }
        }else if(peopleCnt==4){
            if(player1EditText.getText().toString().equals("") || player2EditText.getText().toString().equals("") || player3EditText.getText().toString().equals("") || player4EditText.getText().toString().equals("")){
                Toast.makeText(RecordActivity.this,"선수 4명이 필요합니다",Toast.LENGTH_SHORT).show();
            }else{
                setPeopleConfirm();
            }
        }
    }

    //선수 확인 시 서버 전송
    void setPeopleConfirm(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("user_no",userNo);
            jsonObject.put("contest_yy",contestYY);
            jsonObject.put("contest_seq",contestSeq);
            jsonObject.put("game_cd",gameCd);
            jsonObject.put("uniform_num1",player1EditText.getText().toString());
            jsonObject.put("uniform_num2",player2EditText.getText().toString());
            jsonObject.put("uniform_num3",player3EditText.getText().toString());
            jsonObject.put("uniform_num4",player4EditText.getText().toString());

        }catch (Exception e){
            e.getStackTrace();
        }

        //선수 확인
        Call<DataResult<ExistPlayer>> commit = judgementService.setExistPlayer("ExistPlayer",jsonObject);

        commit.enqueue(new Callback<DataResult<ExistPlayer>>() {
            @Override
            public void onResponse(Call<DataResult<ExistPlayer>> call, Response<DataResult<ExistPlayer>> response) {
                if(response.isSuccessful()){

                    Log.d("결과",response.body().getDataArray().toString());
                    setInit();

                    for(int i = 0 ; i < response.body().getDataArray().size();i++){
                        switch (i){
                            case 0:
                                if(!response.body().getDataArray().get(i).getPart_rope_cnt().equals("0")){
                                    player1EditText.setBackgroundColor(Color.RED);
                                }else if(response.body().getDataArray().get(i).getAppl_yn().equals("N")) {
                                    player1EditText.setBackgroundColor(Color.YELLOW);
                                    player1NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                }else{
                                    //player1EditText.setEnabled(false);
                                    player1EditText.setBackgroundColor(Color.rgb(239,239,244));
                                    player1NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                    try {
                                        jsonObject1.put("player_seq1", response.body().getDataArray().get(i).getPlayer_seq() );
                                        jsonObject1.put("uniform_num1",response.body().getDataArray().get(i).getUniform_num());
                                        jsonObject1.put("game_part_cd1", response.body().getDataArray().get(i).getGame_part_cd());
                                        jsonObject1.put("appl_yn1", response.body().getDataArray().get(i).getAppl_yn());
                                    }catch (Exception e){
                                        e.getStackTrace();
                                    }
                                }
                                break;
                            case 1:
                                if(!response.body().getDataArray().get(i).getPart_rope_cnt().equals("0")){
                                    player2EditText.setBackgroundColor(Color.RED);
                                }else if(response.body().getDataArray().get(i).getAppl_yn().equals("N")) {
                                    player2EditText.setBackgroundColor(Color.YELLOW);
                                    player2NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                }else{
                                    //player2EditText.setEnabled(false);
                                    player2EditText.setBackgroundColor(Color.rgb(239,239,244));
                                    player2NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                    try {
                                        jsonObject1.put("player_seq2", response.body().getDataArray().get(i).getPlayer_seq() );
                                        jsonObject1.put("uniform_num2",response.body().getDataArray().get(i).getUniform_num());
                                        jsonObject1.put("game_part_cd2", response.body().getDataArray().get(i).getGame_part_cd());
                                        jsonObject1.put("appl_yn2", response.body().getDataArray().get(i).getAppl_yn());
                                    }catch (Exception e){
                                        e.getStackTrace();
                                    }
                                }
                                break;
                            case 2:
                                if(!response.body().getDataArray().get(i).getPart_rope_cnt().equals("0")){
                                    player3EditText.setBackgroundColor(Color.RED);
                                }else if(response.body().getDataArray().get(i).getAppl_yn().equals("N")) {
                                    player3EditText.setBackgroundColor(Color.YELLOW);
                                    player3NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                }else{
                                    //player3EditText.setEnabled(false);
                                    player3EditText.setBackgroundColor(Color.rgb(239,239,244));
                                    player3NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                    try {
                                        jsonObject1.put("player_seq3", response.body().getDataArray().get(i).getPlayer_seq() );
                                        jsonObject1.put("uniform_num3",response.body().getDataArray().get(i).getUniform_num());
                                        jsonObject1.put("game_part_cd3", response.body().getDataArray().get(i).getGame_part_cd());
                                        jsonObject1.put("appl_yn3", response.body().getDataArray().get(i).getAppl_yn());
                                    }catch (Exception e){
                                        e.getStackTrace();
                                    }
                                }
                                break;
                            case 3:
                                if(!response.body().getDataArray().get(i).getPart_rope_cnt().equals("0")){
                                    player4EditText.setBackgroundColor(Color.RED);
                                }else if(response.body().getDataArray().get(i).getAppl_yn().equals("N")) {
                                    player4EditText.setBackgroundColor(Color.YELLOW);
                                    player4NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                }else{
                                    //player4EditText.setEnabled(false);
                                    player4EditText.setBackgroundColor(Color.rgb(239,239,244));

                                    player4NameTextView.setText(response.body().getDataArray().get(i).getPlayer_name());
                                    try {
                                        jsonObject1.put("player_seq4", response.body().getDataArray().get(i).getPlayer_seq() );
                                        jsonObject1.put("uniform_num4",response.body().getDataArray().get(i).getUniform_num());
                                        jsonObject1.put("game_part_cd4", response.body().getDataArray().get(i).getGame_part_cd());
                                        jsonObject1.put("appl_yn4", response.body().getDataArray().get(i).getAppl_yn());
                                    }catch (Exception e){
                                        e.getStackTrace();
                                    }
                                }
                                break;
                        }
                    }

                    for(ExistPlayer existPlayer : response.body().getDataArray()){
                        if(existPlayer.getPart_rope_cnt().equals("0") && existPlayer.getAppl_yn().equals("Y")){
                            recordSaveButton.setEnabled(true);
                            recordSaveButton.setBackgroundColor(Color.rgb(254,94,0));

                            playerConfirmButton.setEnabled(false);
                            playerConfirmButton.setBackgroundColor(Color.rgb(239,239,244));
                        }else {
                            recordSaveButton.setEnabled(false);
                            recordSaveButton.setBackgroundColor(Color.rgb(239,239,244));

                            playerConfirmButton.setEnabled(true);
                            playerConfirmButton.setBackgroundColor(Color.rgb(254,94,0));
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataResult<ExistPlayer>> call, Throwable t) {
                Log.d("크기", t.getMessage());
            }
        });
    }

    void setInit(){
        try {
            jsonObject1.put("player_seq1", "");
            jsonObject1.put("player_seq2", "");
            jsonObject1.put("player_seq3", "");
            jsonObject1.put("player_seq4", "");

            jsonObject1.put("uniform_num1", "");
            jsonObject1.put("uniform_num2", "");
            jsonObject1.put("uniform_num3", "");
            jsonObject1.put("uniform_num4", "");

            jsonObject1.put("game_part_cd1", "");
            jsonObject1.put("game_part_cd2", "");
            jsonObject1.put("game_part_cd3", "");
            jsonObject1.put("game_part_cd4", "");

            jsonObject1.put("appl_yn1", "");
            jsonObject1.put("appl_yn2", "");
            jsonObject1.put("appl_yn3", "");
            jsonObject1.put("appl_yn4", "");
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    //기록저장 버튼 클릭시
    @OnClick(R.id.btn_record_save)
    void actionSave(){

        if(recordEditText.getText().toString().equals("")){
            Toast.makeText(RecordActivity.this,"기록을 입력하십시오",Toast.LENGTH_SHORT).show();
        }else{
            try{
                jsonObject1.put("user_no",userNo);
                jsonObject1.put("contest_yy",contestYY);
                jsonObject1.put("contest_seq",contestSeq);
                jsonObject1.put("game_cd",gameCd);
                jsonObject1.put("people_cnt",String.valueOf(peopleCnt));
                jsonObject1.put("judgement_num",judgementNumber);
                jsonObject1.put("part_rope_cnt",recordEditText.getText().toString());

            }catch (Exception e){
                e.getStackTrace();
            }
            Log.d("result",jsonObject1.toString());
            Call<DataResult<ExistPlayerSave>> commit = judgementService.setExistPlayerSave("RecordRegisterSave",jsonObject1);

            commit.enqueue(new Callback<DataResult<ExistPlayerSave>>() {
                @Override
                public void onResponse(Call<DataResult<ExistPlayerSave>> call, Response<DataResult<ExistPlayerSave>> response) {
                    if(response.isSuccessful()){

                        Log.d("result",response.body().toString());

                        //초기화
                        clear();

                        for(ExistPlayerSave existPlayerSave : response.body().getDataArray()){
                            String degree = existPlayerSave.getLast_game_degree();

                            String partRopeCnt = existPlayerSave.getPart_rope_cnt();

                            recordView.setText("(이전기록:"+partRopeCnt+")(차수:"+degree+")");
                        }

                        setPeopleSetting(peopleCnt);
                    }
                }

                @Override
                public void onFailure(Call<DataResult<ExistPlayerSave>> call, Throwable t) {
                    Log.d("result",t.getMessage());
                }
            });
        }
    }

    //차수 증가 버튼 클릭시
    @OnClick(R.id.btn_degree)
    void actionAddDegree(){
        //심반 번호가 1번인 경우만 조회
        //if(bundle.getString("judgement_num").equals("1")){
            String subject = (String)subjectSpinnerVIew.getSelectedItem();

            if(subject.equals("")){
                Toast.makeText(RecordActivity.this,"종목을 선택 하십시오",Toast.LENGTH_SHORT).show();
            }else{
                JSONObject jsonObject = new JSONObject();

                try{
                    jsonObject.put("user_no",userNo);

                    jsonObject.put("contest_yy",contestYY);

                    jsonObject.put("contest_seq",contestSeq);

                    jsonObject.put("game_cd",gameCd);

                }catch (Exception e){
                    e.getStackTrace();
                }

                Call<DataResult<GameDegree>> commit = judgementService.setGameDegree("GetLastGameDegree",jsonObject);

                commit.enqueue(new Callback<DataResult<GameDegree>>() {
                    @Override
                    public void onResponse(Call<DataResult<GameDegree>> call, Response<DataResult<GameDegree>> response) {
                        if(response.isSuccessful()){
                            //최종 차수
                            lastDegree = response.body().getDataArray().get(0).getLast_game_degree();
                            //최종 차수 Lable 저장
                            finalDegreeTextView.setText("최종="+lastDegree);
                        }
                    }

                    @Override
                    public void onFailure(Call<DataResult<GameDegree>> call, Throwable t) {

                    }
                });
            }
       // }
    }

    void clear(){
        player1EditText.setText("");
        player2EditText.setText("");
        player3EditText.setText("");
        player4EditText.setText("");

        player1NameTextView.setText("");
        player2NameTextView.setText("");
        player3NameTextView.setText("");
        player4NameTextView.setText("");

        playerConfirmButton.setEnabled(true);
        playerConfirmButton.setBackgroundColor(Color.rgb(254,94,0));

        recordSaveButton.setEnabled(false);
        recordSaveButton.setBackgroundColor(Color.rgb(239,239,244));

        recordEditText.setText("");
    }

    void setPeopleSetting(int peopleCnt){

        clear();

        switch (peopleCnt){
            case 1:
                player1EditText.setEnabled(true);
                player1EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player2EditText.setEnabled(false);
                player2EditText.setBackgroundColor(Color.rgb(239,239,244));
                player3EditText.setEnabled(false);
                player3EditText.setBackgroundColor(Color.rgb(239,239,244));
                player4EditText.setEnabled(false);
                player4EditText.setBackgroundColor(Color.rgb(239,239,244));
                break;
            case 2:
                player1EditText.setEnabled(true);
                player1EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player2EditText.setEnabled(true);
                player2EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player3EditText.setEnabled(false);
                player3EditText.setBackgroundColor(Color.rgb(239,239,244));
                player4EditText.setEnabled(false);
                player4EditText.setBackgroundColor(Color.rgb(239,239,244));
                break;
            case 3:
                player1EditText.setEnabled(true);
                player1EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player2EditText.setEnabled(true);
                player2EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player3EditText.setEnabled(true);
                player3EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player4EditText.setEnabled(false);
                player4EditText.setBackgroundColor(Color.rgb(239,239,244));
                break;
            case 4:
                player1EditText.setEnabled(true);
                player1EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player2EditText.setEnabled(true);
                player2EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player3EditText.setEnabled(true);
                player3EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                player4EditText.setEnabled(true);
                player4EditText.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.et_border,null));
                break;

        }
    }
}
