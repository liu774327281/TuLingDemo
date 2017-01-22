package com.example.loe.qianfengdemo.dao;

import android.util.Log;

import com.example.loe.qianfengdemo.app.AppConfig;
import com.example.loe.qianfengdemo.uitls.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by loe on 13/6/16.
 */
public class ChatMsgDao {
    public void getChatMsg(String msg,ApiStringCallback callback){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key",AppConfig.TULING_KEY);
            jsonObject.put("info",msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("TAG_LI",msg+"----");
        HttpUtils.postString(AppConfig.TULING,jsonObject.toString(),null,null,callback);
    }

}
