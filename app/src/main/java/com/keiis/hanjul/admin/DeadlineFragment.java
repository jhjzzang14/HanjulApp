package com.keiis.hanjul.admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
}
