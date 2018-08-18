package com.kernal.plateid.utills;

import android.util.Log;

import com.google.gson.Gson;
import com.kernal.plateid.model.bean.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;



public class ResultUtils {
    public static <T> Result getResultFromJson(String jsonStr, Class<T> clazz){
        Result result = new Result();
        try {
            if(jsonStr==null || jsonStr.isEmpty() || jsonStr.length()<3)return null;
            JSONObject jsonObject = new JSONObject(jsonStr);
            if(!jsonObject.isNull("retCode")) {
                result.setRetCode(jsonObject.getInt("retCode"));
            }else if(!jsonObject.isNull("msg")){
                result.setRetCode(jsonObject.getInt("msg"));
            }
            if(!jsonObject.isNull("retMsg")) {
                result.setRetMsg(jsonObject.getBoolean("retMsg"));
            }else if(!jsonObject.isNull("result")){
                result.setRetMsg(jsonObject.getBoolean("result"));
            }
            if(!jsonObject.isNull("retData")) {
                JSONObject jsonRetData = jsonObject.getJSONObject("retData");
                if (jsonRetData != null) {
                    Log.e("Utils", "jsonRetData=" + jsonRetData);
                    String date;
                    try {
                        date = URLDecoder.decode(jsonRetData.toString(), I.UTF_8);
                        Log.e("Utils", "jsonRetData=" + date);
                        T t = new Gson().fromJson(date, clazz);
                        result.setRetData(t);
                        return result;

                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                        T t = new Gson().fromJson(jsonRetData.toString(), clazz);
                        result.setRetData(t);
                        return result;
                    }
                }
            }else{
                if (jsonObject != null) {
                    Log.e("Utils", "jsonRetData=" + jsonObject);
                    String date;
                    try {
                        date = URLDecoder.decode(jsonObject.toString(), I.UTF_8);
                        Log.e("Utils", "jsonRetData=" + date);
                        T t = new Gson().fromJson(date, clazz);
                        result.setRetData(t);
                        return result;

                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                        T t = new Gson().fromJson(jsonObject.toString(), clazz);
                        result.setRetData(t);
                        return result;
                    }
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

   /* public static ArrayList<CartBean> getCartFromJson(String jsonStr){
        ArrayList<CartBean> list = null;
        try {
            if (jsonStr == null || jsonStr.isEmpty() || jsonStr.length() < 3) return null;
            JSONArray array = new JSONArray(jsonStr);
            if (array != null) {
                list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    CartBean cart = new CartBean();
                    if(!jsonObject.isNull("id")) {
                        cart.setId(jsonObject.getInt("id"));
                    }
                    if(!jsonObject.isNull("userName")) {
                        cart.setUserName(jsonObject.getString("userName"));
                    }
                    if(!jsonObject.isNull("goodsId")) {
                        cart.setGoodsId(jsonObject.getInt("goodsId"));
                    }
                    if(!jsonObject.isNull("count")) {
                        cart.setCount(jsonObject.getInt("count"));
                    }
                    if(!jsonObject.isNull("isChecked")) {
                        cart.setChecked(false);
                    }
                    if(!jsonObject.isNull("goods")) {
                        try {
                            JSONObject jsonRetData = jsonObject.getJSONObject("goods");
                            if (jsonRetData != null) {
                                Log.e("Utils", "jsonRetData=" + jsonRetData);
                                String date;
                                try {
                                    date = URLDecoder.decode(jsonRetData.toString(), I.UTF_8);
                                    Log.e("Utils", "jsonRetData=" + date);
                                    GoodsDetailsBean g = new Gson().fromJson(date, GoodsDetailsBean.class);
                                    cart.setGoods(g);

                                } catch (UnsupportedEncodingException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    list.add(cart);
                }
                return list;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }*/

    public static <T> Result getListResultFromJson(String jsonStr, Class<T> clazz){
        Result result = new Result();
        Log.e("Utils","jsonStr="+jsonStr);
        try {
            if(jsonStr==null || jsonStr.isEmpty() || jsonStr.length()<3)return null;
            JSONObject jsonObject = new JSONObject(jsonStr);
            if(!jsonObject.isNull("retCode")) {
                result.setRetCode(jsonObject.getInt("retCode"));
            }else if(!jsonObject.isNull("msg")){
                result.setRetCode(jsonObject.getInt("msg"));
            }
            if(!jsonObject.isNull("retMsg")) {
                result.setRetMsg(jsonObject.getBoolean("retMsg"));
            }else if(!jsonObject.isNull("result")){
                result.setRetMsg(jsonObject.getBoolean("result"));
            }
            if(!jsonObject.isNull("retData")) {
                JSONArray array = jsonObject.getJSONArray("retData");
                if (array != null) {
                    List<T> list = new ArrayList<T>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonGroupAvatar = array.getJSONObject(i);
                        T ga = new Gson().fromJson(jsonGroupAvatar.toString(), clazz);
                        list.add(ga);
                    }
                    result.setRetData(list);
                    return result;
                }
            }else{
                JSONArray array = new JSONArray(jsonStr);
                if (array != null) {
                    List<T> list = new ArrayList<T>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonGroupAvatar = array.getJSONObject(i);
                        T ga = new Gson().fromJson(jsonGroupAvatar.toString(), clazz);
                        list.add(ga);
                    }
                    result.setRetData(list);
                    return result;
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

}
