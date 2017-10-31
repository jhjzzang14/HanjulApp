package com.keiis.hanjul.admin;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hojun on 2017-05-03.
 */

public class DeadlineFragment extends Fragment {

    @BindView(R.id.tv_deadline)
    TextView deadlineView;

    @BindView(R.id.et_modify_date)
    EditText modifyEditText;

    @BindView(R.id.cb_select)
    CheckBox checkBoxView;

    @OnClick(R.id.btn_send_message)
    void sendMessage(){
        //마감 일시 문자 발송

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("01051502481",null,"대회명 : 충현고등학교 줄넘기 대회\n" +
                "대회일 : 2017년 12월 16일\n" +
                "등록마감일 : 2017년 11월 15일\n" +
                "빠른시간안에 대회등록 부탁 합니다.\n" +
                "대회 요강은 홈페이지 및 hanjulApp을 통해\n" +
                "조회 가능 합니다.\n",null,null);

    }

    @OnClick(R.id.btn_modify)
    void actionModify(){
        //마감 일시 변경
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deadline,container,false);

        ButterKnife.bind(this,view);

        return view;
    }

    private void sendSMS(String phoneNumber, String message){
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(),0,new Intent(SENT),0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(getActivity(),0,new Intent(DELIVERED),0);

        getActivity().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(getActivity(),"알림 문자 메시지가 전송되었습니.",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        },new IntentFilter(SENT));


    }
}
