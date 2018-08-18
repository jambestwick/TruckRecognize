package com.kernal.plateid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kernal.plateid.R;
import com.kernal.plateid.application.FuLiCenterApplication;
import com.kernal.plateid.model.bean.Employee;


public class EmployeeMsgFragment extends Fragment {

    private TextView tvCardNum;
    private TextView tvName;
    private TextView tvDpartment;
    private TextView tvGender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_msg, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        Employee employee = FuLiCenterApplication.getInstance().getUser();
        Log.e("EmployeeMsgFragment", employee.toString());
        tvCardNum.setText(employee.getCard_num()+"");
        tvDpartment.setText(employee.getDepartment());
        tvGender.setText(employee.getGender());
        tvName.setText(employee.getName());

    }

    private void initView(View view) {

        tvCardNum = (TextView) view.findViewById(R.id.empCard);
        tvDpartment = (TextView) view.findViewById(R.id.empDepartment);
        tvName = (TextView) view.findViewById(R.id.empName);
        tvGender = (TextView) view.findViewById(R.id.empGender);
    }

}
