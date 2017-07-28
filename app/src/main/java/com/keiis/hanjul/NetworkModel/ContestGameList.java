package com.keiis.hanjul.NetworkModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hojun on 2017-05-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestGameList {
    private String game_cd;
    private String game_name;
    private String people_cnt;
}
