package com.kernal.plateid.fragment;


import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.kernal.plateid.R;
import com.kernal.plateid.R2;
import com.kernal.plateid.activity.MemoryCameraActivity;
import com.kernal.plateid.activity.PermissionActivity;
import com.kernal.plateid.application.CardScanApplication;
import com.kernal.plateid.model.bean.Truck;
import com.kernal.plateid.model.net.IModelUser;
import com.kernal.plateid.model.net.ModelUser;
import com.kernal.plateid.model.net.OnCompleteListener;
import com.kernal.plateid.utills.CheckPermission;


public class OperateFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout rvgGetTaggedTruck,rvStartRecognize,rvAddTruckMsg;


    static final String[] PERMISSION = new String[] {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE, // 读取权限
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.VIBRATE, Manifest.permission.INTERNET
    };

    private IModelUser modelUser;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("LoginInFragmet","OperateFragment");
        setListenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_operate, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setListenter() {
        rvStartRecognize.setOnClickListener(this);

    }

    private void initData() {
    }

    private void addToRemote(Truck truck) {
        IModelUser modelUser=new ModelUser();
        String employeeId= CardScanApplication.getUser().getId()+"";
        modelUser.insertIntoRemote(getActivity(), truck.getStatus()+"", truck.getTruk_card(), employeeId, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("OeprateFrag","result="+result);
            }

            @Override
            public void onError(String error) {

            }
        });


    }

    private void initView(View view) {
        rvAddTruckMsg = (RelativeLayout) view.findViewById(R.id.truck_confirm);
        rvStartRecognize = (RelativeLayout) view.findViewById(R.id.rvGetInRecognize);
        rvgGetTaggedTruck = (RelativeLayout) view.findViewById(R.id.rvTaggedTruck);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R2.id.truck_confirm:

                break;

            case R2.id.rvTaggedTruck:

                break;

            case R2.id.rvGetInRecognize:
                Intent cameraIntent=new Intent(getActivity(), MemoryCameraActivity.class);
//                cameraIntent. putExtra("camera", true);
                if (Build.VERSION.SDK_INT >= 23) {

                    CheckPermission checkPermission = new CheckPermission(getActivity());
                    if (checkPermission.permissionSet(PERMISSION)) {
                        PermissionActivity.startActivityForResult(getActivity(),0,"true",  PERMISSION);
                    } else {
                        cameraIntent.putExtra("camera", false);
//                        cameraIntent.putExtra("camera", true);
                        startActivity(cameraIntent);

                    }
                } else {
//                    cameraIntent.putExtra("camera", true);
                    cameraIntent.putExtra("camera", false);
                    startActivity(cameraIntent);
                }
                break;
        }

    }


}
