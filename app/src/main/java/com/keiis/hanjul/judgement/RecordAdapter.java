package com.keiis.hanjul.judgement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.keiis.hanjul.R;

/**
 * Created by hojun on 2017-04-30.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {

    private LayoutInflater inflater;

    public RecordAdapter(Context context){
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.adapter_list_item,parent,false);

        RecordViewHolder viewHolder = new RecordViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecordViewHolder extends RecyclerView.ViewHolder{

        private TextView userNameView;
        private TextView userRecordView;

        public RecordViewHolder(View itemView) {
            super(itemView);
            userNameView = (TextView)itemView.findViewById(R.id.user_name);
            userRecordView = (TextView)itemView.findViewById(R.id.user_record);
        }

    }
}
