package com.zzy.quick.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by zhou on 2018/5/3.
 * @function 定义解析Json 需要的接口
 */

public interface JsonUtils {

    /**
     * 将String字符串转换成一个JsonObject
     * */
     JSONObject parseObject(String jsonStr);

     /**
      * 将String字符串解析为指定的JavaBean
      * */
     <T> T parseObject(String jsonStr,Class<T> clazz);

     /**
      * 将String字符串转换为JSONArray
      * */
     JSONArray parseArray(String jsonStr);

     /**
      * 将String字符串转换为包含自定类的List集合
      * */
     <T> List<T> parseArray(String jsonStr, Class<T> clazz);

     /**
      * 将一个JavaBean对象转换为JSON格式的String
      * */
    String toJson(Object obj);
}
