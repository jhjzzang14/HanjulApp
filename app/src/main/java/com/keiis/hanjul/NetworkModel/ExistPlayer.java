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
public class ExistPlayer {
    private String player_name;
    private String orgn_cd;
    private String uniform_num;
    private String game_degree;
    private String part_cd;
    private String game_part_cd;
    private String player_idx;
    private String game_cd;
    private String appl_yn;
    private String player_seq;
    private String contest_yy;
    private String contest_seq;
    private String part_rope_cnt;
}
