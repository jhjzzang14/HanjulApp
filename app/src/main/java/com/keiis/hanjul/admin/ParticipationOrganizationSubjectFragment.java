package com.keiis.hanjul.admin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hojun on 2017-05-03.
 */

public class ParticipationOrganizationSubjectFragment extends Fragment{

    @BindView(R.id.subject_1)
    TextView subject1View;
    @BindView(R.id.subject_2)
    TextView subject2View;
    @BindView(R.id.list_view)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_participation,container,false);

        ButterKnife.bind(this,view);

        subject1View.setText("단체명");

        subject2View.setText("참가인원");

        listView.setAdapter(new FragmentAdapter());

        return view;
    }

    class FragmentAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        public FragmentAdapter(){
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //테이블 목록
            View view = inflater.inflate(R.layout.adapter_table_list_row2,parent,false);

            return view;
        }
    }
}
