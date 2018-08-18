package com.kernal.plateid.utills;

import android.app.Activity;
import android.content.Intent;

import com.kernal.plateid.R;
import com.kernal.plateid.activity.LoginActivity;


/**
 * Created by clawpo on 2017/1/10.
 */

public class MFGT {
    public static void finish(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void startActivity(Activity context, Class<?> clz){
        context.startActivity(new Intent(context, clz));
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        context.finish();

    }

    public static void startActivity(Activity context,Intent intent){
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        context.finish();
    }


    public static void gotoLogin(Activity context) {
        context.startActivityForResult(new Intent(context,LoginActivity.class), I.REQUEST_CODE_LOGIN);
    }

    public static void gotoLogin(Activity context,int code) {
        context.startActivityForResult(new Intent(context,LoginActivity.class),code);
    }

}
