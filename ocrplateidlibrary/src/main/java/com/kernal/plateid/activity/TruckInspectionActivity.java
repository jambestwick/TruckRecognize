package com.kernal.plateid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kernal.plateid.R;
import com.kernal.plateid.model.bean.Result;
import com.kernal.plateid.model.bean.Truck;
import com.kernal.plateid.utills.I;
import com.kernal.plateid.utills.MFGT;
import com.kernal.plateid.utills.OkHttpUtils;

import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;

public class TruckInspectionActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView truck_name,truck_card,truck_weight,truck_time,truck_status,truck_operator;
    private Button btn_confirm,btn_cancel;
    private Truck truck_data;
    private static final String TAG = "TruckInspectionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_inspection);
        Result result = (Result) getIntent().getSerializableExtra("result_data");
        Log.e(TAG, "result=" + result.toString());
        Gson gson=new Gson();
        String json=result.getRetData().toString();
        truck_data=gson.fromJson(json, Truck.class);
        Log.e(TAG, "truck_data=" + truck_data.toString());
        initView();
        initData();
        setListenter();

    }

    private void initData() {
        truck_time.setText(truck_data.getDate());
        truck_weight.setText("50吨");
        truck_status.setText(truck_data.getStatus()+"");
        truck_card.setText(truck_data.getTruk_card());
        truck_operator.setText(truck_data.getId()+"");
        truck_name.setText("解放牌汽车");

    }

    private void setListenter() {
        btn_cancel.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    private void initView() {
        truck_card = (TextView) findViewById(R.id.truck_card);
        truck_name = (TextView) findViewById(R.id.truck_name);
        truck_operator = (TextView) findViewById(R.id.truck_operatorid);
        truck_status = (TextView) findViewById(R.id.truck_status);
        truck_weight = (TextView) findViewById(R.id.truck_weight);
        truck_time = (TextView) findViewById(R.id.truck_time);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_confirm) {
            executeSetRequest(truck_data.getTruk_card());


        } else if (i == R.id.btn_cancel) {
            TruckInspectionActivity.this.finish();

        }


    }

    private void executeSetRequest(String truck_card) {
        OkHttpUtils<Result> utils = new OkHttpUtils<>(TruckInspectionActivity.this);
        utils.setRequestUrl(I.TRUCKSTATUS_UPDATE)
                .addParam("truck_card",truck_card)
                .targetClass(Result.class)
                .execute(new OkHttpUtils.OnCompleteListener<Result>() {
                    @Override
                    public void onSuccess(Result result) {
                        Intent intent = new Intent(TruckInspectionActivity.this, TruckInspectionResultActivity.class);
                        if (result != null) {
                            Log.e(TAG, "result=" + result.toString());
                            intent.putExtra("result_data", result);
                        }
                        MFGT.startActivity(TruckInspectionActivity.this,intent);
                    }

                    @Override
                    public void onError(String error) {
                        Log.e(TAG, "result=" + error.toString());
                        MFGT.startActivity(TruckInspectionActivity.this, new Intent(TruckInspectionActivity.this, TruckInspectionResultActivity.class));

                    }
                });


    }
}
