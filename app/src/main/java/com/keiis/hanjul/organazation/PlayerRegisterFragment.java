package com.keiis.hanjul.organazation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.keiis.hanjul.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hojun on 2017-04-30.
 */

public class PlayerRegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_register,container,false);

        ButterKnife.bind(this,view);

        return view;
    }

    //이름 검색
    @OnClick(R.id.btn_search)
    void actionSearch(){
        DialogPlus dialogPlus = DialogPlus.newDialog(getActivity())
                .setAdapter(new SearchAdapter())
                .setHeader(R.layout.dialog_user_search)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {

                    }
                })
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();

        dialogPlus.show();
    }

    class SearchAdapter extends BaseAdapter{

        private LayoutInflater inflater;

        public SearchAdapter() {
            inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = inflater.inflate(R.layout.adapter_search_list_item,null);

            return view;
        }
    }
}
