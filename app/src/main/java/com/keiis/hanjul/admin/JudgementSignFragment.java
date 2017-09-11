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
import android.widget.Spinner;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hojun on 2017-05-03.
 */

public class JudgementSignFragment extends Fragment {

    @BindView(R.id.list_view)
    ListView listView;

    @BindView(R.id.sp_subject)
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jugdement_sign,container,false);

        ButterKnife.bind(this,view);

        listView.setAdapter(new JudgementSignAdapter());

        return view;
    }

    class JudgementSignAdapter extends BaseAdapter{

        private LayoutInflater inflater;

        public JudgementSignAdapter(){
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 1;
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
            View view = inflater.inflate(R.layout.adapter_jugdement_sign_list,null);

            return view;
        }
    }
}
