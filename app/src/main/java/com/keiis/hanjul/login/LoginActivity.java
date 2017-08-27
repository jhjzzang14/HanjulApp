package com.keiis.hanjul.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.keiis.hanjul.NetworkService.DefaultRestClient;
import com.keiis.hanjul.NetworkService.UserService;
import com.keiis.hanjul.R;
import com.keiis.hanjul.admin.AdminActivity;
import com.keiis.hanjul.judgement.JudgementActivity;
import com.keiis.hanjul.manager.ManagerActivity;
import com.keiis.hanjul.organazation.OrganizationActivity;
import com.keiis.hanjul.user.UserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private DefaultRestClient<UserService> defaultRestClient;

    private UserService userService;

    @BindView(R.id.et_loginId)
    EditText loginId;

/*
    @BindView(R.id.et_loginPw)
    EditText loginPw;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.sp_userGubun)
    Spinner sp_userGubun;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v02);

        ButterKnife.bind(this);

        defaultRestClient = new DefaultRestClient<>();

        userService = defaultRestClient.getClient(UserService.class);

     }

    //기록조회 버튼 클릭시
    @OnClick(R.id.btn_record_search) void registerConfirm(){
        //선수관리 화면으로 이동
        Intent intent = new Intent(LoginActivity.this, OrganizationActivity.class);

        startActivity(intent);
    }

    //기록등록 버튼 클릭시
    @OnClick(R.id.btn_record_register) void findUserConfirm(){
        //심판 기록등록으로 이동
        //로그인 아이디, 사용자 번호 디폴트
        String id = loginId.getText().toString();
        if(id.equals("hanjul01")){
            getSharedPreferences("user",MODE_PRIVATE).edit().putString("login_id","hanjul01").commit();
            getSharedPreferences("user",MODE_PRIVATE).edit().putString("user_no","13").commit();
            Intent intent = new Intent(LoginActivity.this, JudgementActivity.class);

            startActivity(intent);
        }else if(id.equals("manager")){

            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);

            startActivity(intent);
        }else if(id.equals("admin")){
            Intent intent = new Intent(LoginActivity.this, ManagerActivity.class);

            startActivity(intent);
        }else if(id.equals("user")){
            Intent intent = new Intent(LoginActivity.this, UserActivity.class);

            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "아이디가 일치 하지 않습니다", Toast.LENGTH_SHORT).show();
        }
    }

/*    @OnClick(R.id.btn_login) void loginConfirm(){
        String type = (String)sp_userGubun.getSelectedItem();
        userService.postLogin("Login",loginId.getText().toString(),loginPw.getText().toString(),type)
                .enqueue(new Callback<DataResult<Login>>() {
                    @Override
                    public void onResponse(Call<DataResult<Login>> call, Response<DataResult<Login>> response) {
                        if(response.isSuccessful()){
                            String code_valu = response.body().getDataArray().get(0).getCode_value();
                            if(code_valu.equals("O")){
                                //Organization
                                Log.d("result",response.body().getDataArray().get(0).getCode_name());
                            }else if(code_valu.equals("P")){
                                //Player

                                Log.d("result",response.body().getDataArray().get(1).getCode_name());
                            }if(code_valu.equals("J")){
                                //Judgement

                                Log.d("result",response.body().getDataArray().get(2).getCode_name());
                            }if(code_valu.equals("M")){
                                //Manager

                                Log.d("result",response.body().getDataArray().get(3).getCode_name());
                            }if(code_valu.equals("A")){
                                //Admin

                                Log.d("result",response.body().getDataArray().get(4).getCode_name());
                            }
                        }else{
                            try{
                                Log.d("result",response.errorBody().string());
                            }catch (Exception e){

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DataResult<Login>> call, Throwable t) {
                        Log.d("result",t.getMessage());
                    }
                });
    }*/
}
