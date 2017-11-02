package com.keiis.hanjul.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.keiis.hanjul.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hojun on 2017-02-18.
 */

public class IndividualFragment extends Fragment{

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.btn_search)
    Button btnSearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_confirm_individual,null);
        ButterKnife.bind(this,view);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int count = etName.getText().length();

                if(count==0){
                    btnSearch.setEnabled(false);
                    btnSearch.setBackgroundColor(Color.rgb(153,153,153));
                }else{
                    btnSearch.setEnabled(true);
                    btnSearch.setBackgroundColor(Color.rgb(254,94,0));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }
}
