package com.shu.shust2.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Leo on 2017/9/5.
 */

public class JsonUtil {

    public static <T> T parseJson(String jsonData, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, type);
    }

    public static <T> List<T> parseJsonArray(String jsonData,Class<T> type){
        Gson gson=new Gson();
        return gson.fromJson(jsonData,new TypeToken<List<T>>(){}.getType());
    }
}
