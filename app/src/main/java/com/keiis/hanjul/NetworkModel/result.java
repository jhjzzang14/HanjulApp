package com.keiis.hanjul.NetworkModel;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hojun on 2017-05-14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class result {
    private String resultMsg;
    private String resultCd;
    private String saveFlag;
}
