package com.keiis.hanjul.login;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.keiis.hanjul.NetworkModel.Login;
import com.keiis.hanjul.NetworkModel.LoginOpen;
import com.keiis.hanjul.NetworkModel.UserResultModel;
import com.keiis.hanjul.NetworkService.DefaultRestClient;
import com.keiis.hanjul.NetworkService.UserService;
import com.keiis.hanjul.R;
import com.keiis.hanjul.user.UserActivity;
import com.keiis.hanjul.user.UserConfirmActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private DefaultRestClient<UserService> defaultRestClient;

    private UserService userService;

    @BindView(R.id.et_loginId)
    EditText loginId;

    @BindView(R.id.et_loginPw)
    EditText loginPw;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.sp_userGubun)
    Spinner sp_userGubun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_v02);

        ButterKnife.bind(this);

        defaultRestClient = new DefaultRestClient<>();

        userService = defaultRestClient.getClient(UserService.class);

        loginId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int inputSize = loginId.getText().length();

                if(inputSize==0){
                    btn_login.setEnabled(false);
                    btn_login.setBackgroundColor(Color.rgb(153,153,153));
                }else{
                    btn_login.setEnabled(true);
                    btn_login.setBackgroundColor(Color.rgb(254,94,0));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

     }

    @OnClick(R.id.btn_login) void loginConfirm(){
        String type = (String)sp_userGubun.getSelectedItem();
        userService.postLogin("Login",loginId.getText().toString(),loginPw.getText().toString(),type)
                .enqueue(new Callback<UserResultModel<Login>>() {
                    @Override
                    public void onResponse(Call<UserResultModel<Login>> call, Response<UserResultModel<Login>> response) {
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
                    public void onFailure(Call<UserResultModel<Login>> call, Throwable t) {
                        Log.d("result",t.getMessage());
                    }
                });
    }
    @OnClick(R.id.btn_register) void registerConfirm(){
        Intent intent = new Intent(LoginActivity.this, UserActivity.class);

        startActivity(intent);
    }

    @OnClick(R.id.btn_search) void findUserConfirm(){
        Intent intent = new Intent(LoginActivity.this, UserConfirmActivity.class);

        startActivity(intent);
    }
}
