package com.kernal.plateid.utills;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

/**
 * Created by blackfox on 2018/9/6 0006.
 */

public class DataStorageUtil {

    private static final String TAG = "DataStorageUtil";



    private static DataStorageUtil instance;
    private Context mContext;
    private SharedPreferences dataInstance;
    private SharedPreferences.Editor mEditor;

    private DataStorageUtil(){

    }

    /**
     *
     * @desc:双重校验单例模式
     * 
     * @return：返回该对象的一个实例
     */
    public static DataStorageUtil getInstance(){

        if (instance == null) {
            synchronized (DataStorageUtil.class) {
                if (instance==null)
                    Log.e(TAG, "getInstance: instance.create()" );
                instance=new DataStorageUtil();
            }
        }
        Log.e(TAG, "getInstance: instance="+instance.toString() );
        return instance;
    }

    public void initData(Context context){
        mContext=context;
        if (dataInstance==null){
            Log.e(TAG, "initData: "+"dataInstance==null");
            dataInstance = PreferenceManager.getDefaultSharedPreferences(context);
            Log.e(TAG, "initData: dataInstance="+dataInstance.toString() );

        }

//        Log.e(TAG, "initData: mEditor="+mEditor.toString());
        mEditor=dataInstance.edit();

    }

    /**
     * @desc
     * @param key
     * @param value
     * @return
     *
     */
    public DataStorageUtil addData(String key, String value){

        mEditor.putString(key, value);
        return this;
    }


    public void dataCommit(){
        if (mEditor!=null)
            mEditor.commit();
    }

    public boolean deleteData(String key) {
        mEditor.remove(key).commit();
        if (dataInstance.contains(key)) {
            return false;
        }
        return true;
    }

    public String getData(String key){
        Log.e(TAG, "getData: key="+key );
       String result=dataInstance.getString(key, null);
        if (TextUtils.isEmpty(result)) {
            return null;
        } else {
            return result;
        }

    }


}
