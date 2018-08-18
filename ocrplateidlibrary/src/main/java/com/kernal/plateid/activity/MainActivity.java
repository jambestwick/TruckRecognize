package com.kernal.plateid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import com.kernal.plateid.R;
import com.kernal.plateid.R2;
import com.kernal.plateid.application.FuLiCenterApplication;
import com.kernal.plateid.fragment.AllTruckFragment;
import com.kernal.plateid.fragment.EmployeeMsgFragment;
import com.kernal.plateid.fragment.OperateFragment;
import com.kernal.plateid.fragment.TaggedFragment;
import com.kernal.plateid.utills.I;
import com.kernal.plateid.utills.L;
import com.kernal.plateid.utills.MFGT;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    RadioButton[] rbs = new RadioButton[4];
//    @BindView(R2.id.layout_new_good)
//    RadioButton mLayoutNewGood;
//    @BindView(R2.id.layout_boutique)
//    RadioButton mLayoutBoutique;
//    @BindView(R2.id.layout_category)
//    RadioButton mLayoutCategory;
//    @BindView(R2.id.layout_personal_center)

    private static final String TAG = MainActivity.class.getSimpleName();
    int index, currentIndex;

    //    RadioButton mLayoutPersonalCenter;
    Fragment[] mFragments = new Fragment[4];
    AllTruckFragment mAllTruckFragment;
    EmployeeMsgFragment mEmployeeMsgFragment;
    OperateFragment mOperateFragment;
    TaggedFragment mTaggedFragment;
    @BindView(R2.id.select_button)
    RadioButton layoutNewGood;
    @BindView(R2.id.layout_boutique)
    RadioButton layoutBoutique;
    @BindView(R2.id.layout_category)
    RadioButton layoutCategory;
    @BindView(R2.id.layout_personal_center)
    RadioButton layoutPersonalCenter;
//    @BindView(R2.id.layout_new_good)
//    RadioButton mLayoutNewGood;
//    @BindView(R2.id.layout_boutique)
//    RadioButton mLayoutBoutique;
//    @BindView(R2.id.layout_category)
//    RadioButton mLayoutCategory;
//    @BindView(R2.id.layout_personal_center)
//    RadioButton mLayoutPersonalCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);
        ButterKnife.bind(this);
//        ButterKnife.bind(this);

        rbs[0] = layoutNewGood;
        rbs[1] = layoutBoutique;
        rbs[2] = layoutCategory;
        rbs[3] = layoutPersonalCenter;
        mAllTruckFragment = new AllTruckFragment();
        mEmployeeMsgFragment = new EmployeeMsgFragment();
        mOperateFragment = new OperateFragment();
        mTaggedFragment = new TaggedFragment();
        mFragments[0] = mAllTruckFragment;
        mFragments[1] = mTaggedFragment;
        mFragments[2] = mOperateFragment;
        mFragments[3] = mEmployeeMsgFragment;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mAllTruckFragment)
                .add(R.id.fragment_container, mEmployeeMsgFragment)
                .add(R.id.fragment_container, mOperateFragment)
                .show(mAllTruckFragment)
                .hide(mEmployeeMsgFragment)
                .hide(mOperateFragment)
                .commit();
    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R2.id.select_button:
                index = 0;
                break;
            case R2.id.layout_boutique:
                index = 1;
                break;
            case R2.id.layout_category:
                index = 2;
                break;
            case R2.id.layout_personal_center:
                if (FuLiCenterApplication.getUser() == null) {
                    MFGT.startActivity(MainActivity.this, LoginActivity.class);
                } else {
                    index = 3;
                }
                break;
        }
//        currentIndex=index;
        setFragment();
        if (index != currentIndex) {
            setRadioStatus();
        }

    }

    private void setFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mFragments[currentIndex]);
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fragment_container, mFragments[index]);
        }
        ft.show(mFragments[index]).commit();
    }

    private void setRadioStatus() {
        for (int i = 0; i < rbs.length; i++) {
            if (index != i) {
                rbs[i].setChecked(false);
            } else {
                rbs[i].setChecked(true);
            }
        }
        currentIndex = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
        L.e(TAG, "onResume,currentIndex=" + currentIndex + ",index=" + index
                + ",user=" + FuLiCenterApplication.getUser());
        if (index == 4 && FuLiCenterApplication.getUser() == null) {
            index = 0;
        }
        setFragment();
        setRadioStatus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        L.e(TAG, "onActivityResult,resultCode=" + resultCode + ",requestCode=" + requestCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == I.REQUEST_CODE_LOGIN) {
                index = 4;
            }
            if (requestCode == I.REQUEST_CODE_LOGIN_FROM_CART) {
                index = 3;
            }
            setFragment();
            setRadioStatus();
        }
    }
}

