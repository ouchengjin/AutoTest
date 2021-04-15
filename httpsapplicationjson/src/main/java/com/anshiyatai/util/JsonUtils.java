package com.anshiyatai.util;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class JsonUtils{


    public static  Map getJsonList(String jsonStr) {
        Map<String, String> hashMap = new HashMap<>();
        JSONObject outJson = JSONObject.parseObject(jsonStr);
        //因为外部的JSON的key为三位数字的编号，我们需要得到编号，才能得到它对应的内部json
        Set<String> jsonSet = outJson.keySet();
        Iterator<String> iterator = jsonSet.iterator();
        while (iterator.hasNext()) {
            //通过迭代器可以取到外部json的key
            String json = iterator.next();
            if (json.equals("result")) {
                //取得内部json字符串
                String string = outJson.getString(json);
                //将内部json字符串解析为object对象
                JSONObject inJson = JSONObject.parseObject(string);
                //userId，得到value值
                String userId = inJson.getString("userId");
                System.out.println("userId:"+userId);
                hashMap.put("userId:",userId);
                String token = inJson.getString("token");
                System.out.println("token:"+token);
                hashMap.put("token:",token);
            }else if (json.equals("msg")){
                String msg = outJson.getString(json);
                hashMap.put("msg",msg);
            }


        }
        return hashMap;
    }
}