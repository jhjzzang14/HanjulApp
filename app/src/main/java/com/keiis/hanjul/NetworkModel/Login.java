package com.keiis.hanjul.NetworkModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hojun on 2017-03-19.
 */

public class Login {
    @SerializedName("code_valu")
    private String code_value;
    private String code_name;

    public String getCode_value() {
        return code_value;
    }

    public void setCode_value(String code_value) {
        this.code_value = code_value;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }
}
