package com.keiis.hanjul.judgement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JudgementActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.cb_select)
    Spinner eventVIew;

    @BindView(R.id.judgement_number)
    EditText judgementNumberEditText;


    //기록 등록 버튼 클릭시 이벤트
    @OnClick(R.id.btn_record)
    void actionRecord(){
        int judgementNumber = Integer.parseInt(judgementNumberEditText.getText().toString());

        String object = (String)eventVIew.getSelectedItem();

        if(!object.equals("") && judgementNumber != -1){
            //기록 화면으로 이동
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugdement);

        ButterKnife.bind(this);

        titleView.setText("심판");

        //Spinner 선택시
        eventVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getSelectedItem();

            }
        });
    }
}
