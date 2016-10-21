package com.qg.kinectdoctor.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ZH_L on 2016/10/21.
 */
public class JsonUtil {

    public static String toJson(Object obj){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    public static <T> T toObj(String json, Class<T> clazz){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, clazz);
    }
}
