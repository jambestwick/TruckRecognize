package com.kernal.plateid.application;

import android.app.Application;
import android.util.Log;

import com.kernal.plateid.model.bean.Employee;
import com.kernal.plateid.utills.DataStorageUtil;


/**
 * Created by clawpo on 2017/1/10.
 */

public class CardScanApplication extends Application {
    public CardScanApplication() {
    }
    private static CardScanApplication instance;

    private static final String TAG = "CardScanApplication";

    private  DataStorageUtil dataInstance;

    public DataStorageUtil getDataInstance() {
        return dataInstance;
    }

    public static CardScanApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: "+"进入application的onCreate()方法");
        instance = this;
        dataInstance=DataStorageUtil.getInstance();
        dataInstance.initData(getApplicationContext());
    }

    private static Employee user;

    public static Employee getUser() {
        return user;
    }

    public static void setUser(Employee user) {
        CardScanApplication.user = user;
    }
}
