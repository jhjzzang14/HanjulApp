package com.keiis.hanjul.organazation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hojun on 2017-05-01.
 */

public class RecordPersonalFragment extends Fragment {
    @BindView(R.id.list_view)
    ListView listView;

    @OnClick(R.id.tv_search)
    void actionSearch(){
        //이름을 입력하고 검색 버튼을 누르면 이벤트 발생
        String name = nameEditText.getText().toString();

        listView.setAdapter(new RecordFragmentAdapter(getActivity(),2));
    }

    @BindView(R.id.et_name)
    EditText nameEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_record,container,false);

        ButterKnife.bind(this,view);

        return view;
    }
}
