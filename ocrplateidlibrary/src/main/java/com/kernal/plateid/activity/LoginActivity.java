package com.kernal.plateid.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.kernal.plateid.R;
import com.kernal.plateid.R2;
import com.kernal.plateid.application.CardScanApplication;
import com.kernal.plateid.model.bean.Employee;
import com.kernal.plateid.model.bean.Result;
import com.kernal.plateid.model.net.IModelUser;
import com.kernal.plateid.model.net.ModelUser;
import com.kernal.plateid.model.net.OnCompleteListener;
import com.kernal.plateid.utills.CommonUtils;
import com.kernal.plateid.utills.DataStorageUtil;
import com.kernal.plateid.utills.I;
import com.kernal.plateid.utills.MFGT;
import com.kernal.plateid.utills.ResultUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R2.id.username)
    EditText mUsername;
    @BindView(R2.id.password)
    EditText mPassword;

    IModelUser model;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

//    private void checkLoginStatus() {
//        Employee employee = CardScanApplication.getUser();
//        if (employee != null) {
//            MFGT.startActivity(LoginActivity.this, MainActivity.class);
//        }
//
//    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LoginAct", "进入onResume()");
//        checkLoginStatus();
    }

    @OnClick({R2.id.backClickArea, R2.id.btn_login})
    public void onClick(View view) {
        Log.e("LoginAct", "进入按钮检测方法");
        switch (view.getId()) {
            case R2.id.backClickArea:
                MFGT.finish(this);
                break;
            case R2.id.btn_login:
                Log.e("LoginAct", "检测到单击事件");
                checkInput();
                break;
        }
    }

    private void checkInput() {
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            mUsername.setError(getString(R.string.user_name_connot_be_empty));
            mUsername.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            mPassword.setError(getString(R.string.password_connot_be_empty));
            mPassword.requestFocus();
        }else{
            login(username,password);
        }
    }

    private void login(String username, String password) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.logining));
        model = new ModelUser();
        model.login(this, username, password, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                if (result!=null){
                    Result resultFromJson = ResultUtils.getResultFromJson(result, Employee.class);
                    if (resultFromJson != null) {
                        Log.e("LoginActivity",result);
                        if (resultFromJson.isRetMsg()){
                            Log.e("LoginActivity",resultFromJson.toString());
                            Employee employee = (Employee) resultFromJson.getRetData();
                            Log.e("LoginActivity",employee.toString());
                            CardScanApplication.setUser(employee);
                            CardScanApplication.getInstance().getDataInstance()
                                    .addData(I.DATA_NAME_KEY, employee.getName())
                                    .addData(I.DATA_CARD_KEY,String.valueOf(employee.getCard_num()))
                                    .addData(I.DATA_DEPARTMENT_KEY,employee.getDepartment())
                                    .addData(I.DATA_GENTDER_KEY,employee.getGender())
                                    .dataCommit();

                            MFGT.startActivity(LoginActivity.this,MainActivity.class);

                        }else{
                            if (resultFromJson.getRetCode() == I.MSG_LOGIN_UNKNOW_USER){
                                CommonUtils.showLongToast(getString(R.string.login_fail_unknow_user));
                            }
                            if (resultFromJson.getRetCode() == I.MSG_LOGIN_ERROR_PASSWORD){
                                CommonUtils.showLongToast(getString(R.string.login_fail_error_password));
                            }
                        }
                    }else{
                        CommonUtils.showLongToast(getString(R.string.login_fail));
                    }
                }else{
                    CommonUtils.showLongToast(getString(R.string.login_fail));
                }
                dialog.dismiss();

            }

            @Override
            public void onError(String error) {
                dialog.dismiss();
                CommonUtils.showLongToast(error);

            }
        });
    }
}
