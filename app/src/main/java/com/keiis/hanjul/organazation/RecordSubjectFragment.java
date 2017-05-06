package com.keiis.hanjul.organazation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hojun on 2017-05-01.
 */

public class RecordSubjectFragment extends Fragment {

    @BindView(R.id.list_view)
    ListView listView;

    @BindView(R.id.sp_subject)
    Spinner spinnerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject_record,container,false);

        ButterKnife.bind(this,view);

        listView.setAdapter(new RecordFragmentAdapter(getActivity(),1));

        return view;
    }
}
