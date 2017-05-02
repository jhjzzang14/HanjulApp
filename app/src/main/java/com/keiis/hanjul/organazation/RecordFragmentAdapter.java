package com.keiis.hanjul.organazation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.keiis.hanjul.R;

/**
 * Created by hojun on 2017-05-01.
 */

public class RecordFragmentAdapter extends BaseAdapter{

    static int RECORD_SUBJECT = 1;
    static int RECORD_PERSONAL = 2;
    static int RECORD_BEST = 3;
    private int selectNumber;
    private LayoutInflater inflater;

    public RecordFragmentAdapter(Context context,int selectNumber) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.selectNumber = selectNumber;
    }

    @Override
    public int getCount() {
        return 3;
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
        View view = null;
        if(selectNumber == RECORD_SUBJECT){
            view = inflater.inflate(R.layout.record_subject_list_table_item,parent,false);
        }else if(selectNumber == RECORD_PERSONAL){
            view = inflater.inflate(R.layout.record_personal_list_table_item,parent,false);
        }else if(selectNumber == RECORD_BEST){
            view = inflater.inflate(R.layout.record_best_list_table_item,parent,false);
        }

        return view;
    }
}
