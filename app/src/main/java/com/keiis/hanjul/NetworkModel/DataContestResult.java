package com.keiis.hanjul.NetworkModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hojun on 2017-05-21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataContestResult<T> {
    private com.keiis.hanjul.NetworkModel.result result;

    private List<T> dataArray;

    private String groupArray;
}
