package com.kingsoft.utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by caohao1 on 16/6/8.
 */
public class DataResponse {

    public static final Map response(int status, Object msg) {

        Map<String, Object> map = new TreeMap<>();
        map.put("status", status);
        map.put("message", msg);

        return map;
    }

    public static final Map error (int state,String errorMsg){
        Map<String,Object> map = new TreeMap<>();
        map.put("state",state);
        map.put("message",errorMsg);
        return map;
    }

    public static final Map data (Object data){
        Map<String,Object> map = new TreeMap<>();
        map.put("state",1);
        map.put("message",null);
        map.put("data",data);
        return map;
    }
}
