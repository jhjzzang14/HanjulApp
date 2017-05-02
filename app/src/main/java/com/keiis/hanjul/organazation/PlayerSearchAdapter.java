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

public class PlayerSearchAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    public PlayerSearchAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        View view = inflater.inflate(R.layout.player_list_table_item,parent,false);

        return view;
    }
}
