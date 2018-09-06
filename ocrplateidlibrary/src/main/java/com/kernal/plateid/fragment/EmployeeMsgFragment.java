package com.kernal.plateid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kernal.plateid.R;
import com.kernal.plateid.application.CardScanApplication;
import com.kernal.plateid.model.bean.Employee;
import com.kernal.plateid.utills.DataStorageUtil;
import com.kernal.plateid.utills.I;


public class EmployeeMsgFragment extends Fragment {

    private static final String TAG = "EmployeeMsgFragment";

    private TextView tvCardNum;
    private TextView tvName;
    private TextView tvDpartment;
    private TextView tvGender;
    private DataStorageUtil dataInstance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_msg, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        dataInstance=CardScanApplication.getInstance().getDataInstance();
        String card_num = dataInstance
                .getData(I.DATA_CARD_KEY);

        String employ_name = dataInstance.getData(I.DATA_NAME_KEY);
        Log.e(TAG, "initData: employ_name="+employ_name );
        String employ_department = dataInstance.getData(I.DATA_DEPARTMENT_KEY);

        String employ_gender = dataInstance.getData(I.DATA_GENTDER_KEY);



        tvCardNum.setText(card_num);
        tvDpartment.setText(employ_department);
        tvGender.setText(employ_gender);
        tvName.setText(employ_name);

    }

    private void initView(View view) {

        tvCardNum = (TextView) view.findViewById(R.id.empCard);
        tvDpartment = (TextView) view.findViewById(R.id.empDepartment);
        tvName = (TextView) view.findViewById(R.id.empName);
        tvGender = (TextView) view.findViewById(R.id.empGender);
    }

}
