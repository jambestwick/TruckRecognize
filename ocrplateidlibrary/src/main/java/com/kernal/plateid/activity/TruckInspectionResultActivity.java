package com.kernal.plateid.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kernal.plateid.R;
import com.kernal.plateid.model.bean.Result;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class TruckInspectionResultActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_alert,tv_time;
    private Button btn_back;
    private Result result;
    private Handler handler;
    private Timer timer;
    private int time_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_inspection_result);
        result= (Result) getIntent().getSerializableExtra("result_data");
        initView();
        setOnClickListenter();
        initData();
        initHandler();

    }

    private void initHandler() {
        handler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    tv_time.setText(msg.arg1+"s");
                    if (msg.arg1 == 0) {
                        TruckInspectionResultActivity.this.finish();
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        countDownStart();
    }

    private void countDownStart() {
        timer=new Timer();
        time_count=5;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (time_count >=0) {
                    Message msg = Message.obtain();
                    msg.what = 1;
                    msg.arg1 = time_count;
                    handler.sendMessage(msg);
                    time_count--;
                } else {
                    timer.cancel();
                }
            }
        },0,1*1000);

    }

    private void initData() {
        if (result != null && result.getRetCode() == 200) {


        } else {
            tv_alert.setText("验收失败，请单击返回按钮重新提交验收");
            btn_back.setVisibility(View.VISIBLE);
        }

    }

    private void setOnClickListenter() {
        btn_back.setOnClickListener(this);

    }

    private void initView() {
        tv_alert = (TextView) findViewById(R.id.alert_content);
        tv_time = (TextView) findViewById(R.id.time_content);
        btn_back = (Button) findViewById(R.id.btn_back);

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_back)
            TruckInspectionResultActivity.this.finish();

    }
}
