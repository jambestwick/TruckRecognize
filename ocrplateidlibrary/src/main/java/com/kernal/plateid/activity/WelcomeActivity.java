package com.kernal.plateid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.kernal.plateid.R;
import com.kernal.plateid.application.CardScanApplication;
import com.kernal.plateid.utills.DataStorageUtil;
import com.kernal.plateid.utills.I;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        verifyStatus();

    }

    private void verifyStatus() {
        DataStorageUtil dataInstance = CardScanApplication.getInstance().getDataInstance();
        String userInfo = dataInstance.getData(I.DATA_NAME_KEY);
        Log.e(TAG, "verifyStatus: userInfo="+userInfo);
        if (!TextUtils.isEmpty(userInfo)) {
            jumpToActivity(new Intent(this,MainActivity.class));
        } else {
            jumpToActivity(new Intent(this,LoginActivity.class));
        }

    }

    private void jumpToActivity(final Intent intent) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
