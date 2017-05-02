package com.keiis.hanjul.organazation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hojun on 2017-05-01.
 */

public class PlayerNameListFragment extends Fragment {
    @BindView(R.id.list_view)
    ListView listView;

    @BindView(R.id.et_name)
    EditText nameEditTextView;

    @OnClick(R.id.search)
    void actionSearch(){
        nameEditTextView.getText().toString();

        listView.setAdapter(new PlayerSearchAdapter(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_player_list_item,container,false);

        ButterKnife.bind(this,view);

        return view;
    }
}
