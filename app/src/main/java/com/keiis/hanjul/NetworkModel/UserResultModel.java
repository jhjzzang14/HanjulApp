package com.keiis.hanjul.NetworkModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hojun on 2017-02-19.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResultModel<T> {

    private Result result;

    private List<T> dataArray;

    class Result{
        private String resultMsg;

        private String resultCd;
    }


}
