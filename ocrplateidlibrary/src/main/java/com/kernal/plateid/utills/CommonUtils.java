package com.kernal.plateid.utills;

import android.widget.Toast;

import static com.kernal.plateid.application.FuLiCenterApplication.getInstance;


public class CommonUtils {
    public static void showLongToast(String msg){
        Toast.makeText(getInstance(),msg,Toast.LENGTH_LONG).show();
    }
    public static void showShortToast(String msg){
        Toast.makeText(getInstance(),msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(int rId){
        showLongToast(getInstance().getString(rId));
    }
    public static void showShortToast(int rId){
        showShortToast(getInstance().getString(rId));
    }
}
