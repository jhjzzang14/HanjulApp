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
public class ResultList {
    private String player_name;
    private String part_name;
    private String uniform_num;
    private String rank;
    private String placing;
    private String grade_name;
    private String grade;
    private String game_short_name;
    private String part_rope_cnt;
}
