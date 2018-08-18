package com.kernal.plateid.model.net;

import android.content.Context;

import com.kernal.plateid.model.bean.CollectBean;
import com.kernal.plateid.model.bean.MessageBean;
import com.kernal.plateid.model.bean.Truck;

import java.io.File;



/**
 * Created by clawpo on 2017/1/11.
 */

public interface IModelUser {
    void login(Context context, String username, String password, OnCompleteListener<String> listener);
    void register(Context context, String username, String usernick, String password, OnCompleteListener<String> listener);
    void updatNick(Context context, String username, String usernick, OnCompleteListener<String> listener);
    void uploadAvatar(Context context, String username, File file, OnCompleteListener<String> listener);
    void collecCount(Context context, String username, OnCompleteListener<MessageBean> listener);
    void getCollects(Context context, String username, int pageId, int pageSize, OnCompleteListener<CollectBean[]> listener);
    void getUnTaggedTruck(Context context, int pageId, int pageSize, OnCompleteListener<Truck[]> listener);
    void insertIntoRemote(Context context, String status, String truckNum, String employeeId, OnCompleteListener<String> listener);
//    void getCart(Context context, String username, OnCompleteListener<CartBean[]> listener);
//    void addCart(Context context,String username,int goodsId,int count,OnCompleteListener<MessageBean> listener);
//    void delCart(Context context,int cartId,OnCompleteListener<MessageBean> listener);
//    void updateCart(Context context,int cartId,int count,OnCompleteListener<MessageBean> listener);
//    void updateCart(Context context, int action, String username, int goodsId, int count, int cartId, OnCompleteListener<MessageBean> listener);
}
