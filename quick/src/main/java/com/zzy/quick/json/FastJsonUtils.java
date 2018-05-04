package com.zzy.quick.json;

import com.alibaba.fastjson.JSON;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @function 使用FastJson实现
 * Created by zhou on 2018/5/4.
 */

public class FastJsonUtils implements JsonUtils {


    @Override
    public JSONObject parseObject(String jsonStr) {
       JSONObject obj=null;
        try {
            obj= new org.json.JSONObject(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public <T> T parseObject(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr,clazz);
    }

    @Override
    public JSONArray parseArray(String jsonStr) {
        JSONArray jsonArray=null;
        try {
            jsonArray=new JSONArray(jsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @Override
    public <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        return JSON.parseArray(jsonStr,clazz);
    }

    @Override
    public String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
}
