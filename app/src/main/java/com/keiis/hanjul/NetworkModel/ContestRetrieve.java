package com.keiis.hanjul.NetworkModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by hojun on 2017-05-13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestRetrieve {
    String contest_end_yn;
    String image_cnt;
    String smart_contest_name;
    String group_sel_yn;
    String contest_yy;
    String contest_date;
    String contest_seq;
    String agent_cd;
}
