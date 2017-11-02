package com.keiis.hanjul;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by NINESTAIRS on 2017. 11. 2..
 */

public class DoubleTabController {
    private static long touchPressedTime = 0;
    private static long resetTime = 2000; // 리셋 타임 설정 - 2초

    public static void onTouchPressed(Activity activity) {
        if (System.currentTimeMillis() > touchPressedTime + resetTime ) {
            // 첫번째 터치
            touchPressedTime = System.currentTimeMillis();
            return;
        }
        // 첫번째 터치후 두번째 터치를 resetTime에 설정된 2초안에 하지 않을시 아래 두번째 터치부분은 실행되지 않음.
        if (System.currentTimeMillis() <= touchPressedTime + resetTime ) {
            // 두번째 터치
            // 동작 수행.
            Toast.makeText(activity,activity.getLocalClassName(),Toast.LENGTH_SHORT).show();
        }
    }
}
