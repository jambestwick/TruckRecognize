package com.kernal.plateid.application;

import android.app.Application;

import com.kernal.plateid.model.bean.Employee;


/**
 * Created by clawpo on 2017/1/10.
 */

public class FuLiCenterApplication extends Application {
    private static FuLiCenterApplication instance;
    public static FuLiCenterApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    private static Employee user;

    public static Employee getUser() {
        return user;
    }

    public static void setUser(Employee user) {
        FuLiCenterApplication.user = user;
    }
}
