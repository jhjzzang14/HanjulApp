package com.keiis.hanjul.admin;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hojun on 2017-05-03.
 */

public class DividendFragment extends Fragment{

    @BindView(R.id.list_view)
    ListView listView;

    //버튼 강제 클릭시
    @OnClick(R.id.btn_dividend)
    void actionDividend(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dividend_management,container,false);

        ButterKnife.bind(this,view);

        listView.setAdapter(new DividendAdapter(getActivity()));

        return view;
    }

    class DividendAdapter extends BaseAdapter{

        private LayoutInflater inflater;

        public DividendAdapter(Context context){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

            View view = inflater.inflate(R.layout.adapter_dividend_list_item,parent,false);

            TextView organizationView = (TextView)view.findViewById(R.id.tv_organization);

            TextView leaderView = (TextView)view.findViewById(R.id.tv_name);

            TextView personalCountView = (TextView)view.findViewById(R.id.tv_personal_count);

            TextView generatedView = (TextView)view.findViewById(R.id.tv_generated);

            Button messageVIew = (Button) view.findViewById(R.id.tv_message);

            return view;
        }
    }
}
