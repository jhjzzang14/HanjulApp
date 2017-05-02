package com.keiis.hanjul.NetworkModel;

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
public class LoginOpen {
        private String user_name;

        private String orgn_name;

        private String user_gubn;

        private String user_type;

        private String user_no;
}
