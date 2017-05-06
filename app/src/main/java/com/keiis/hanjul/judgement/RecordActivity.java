package com.keiis.hanjul.judgement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.rc_list)
    RecyclerView recyclerView;

    @BindView(R.id.subject_list)
    Spinner subjectSpinnerVIew;

    @BindView(R.id.et_record)
    EditText recordEditText;

    private RecordAdapter recordAdapter;

    @OnClick(R.id.btn_identity)
    void actionIdentity(){
        //선수 확인 버튼 클릭시
    }

    @OnClick(R.id.btn_record_save)
    void actionSave(){
        //기록저장 버튼 클릭시
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        ButterKnife.bind(this);

        titleView.setText("대회명");

        subjectSpinnerVIew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //종목 선택시 리스트 뷰 활성화
            }
        });

        recordAdapter = new RecordAdapter(RecordActivity.this);

        recyclerView.setLayoutManager(new GridLayoutManager(RecordActivity.this,2));

        recyclerView.setAdapter(recordAdapter);
    }
}
