package com.tdjpartner.http.interceptor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonHelper{

    public static HashMap<String, Object> parseJSONObject(JSONObject jsonobj){
        JSONArray a_name = jsonobj.names();
        HashMap<String, Object> map =new HashMap<String, Object>();
        if(a_name !=null) {
            int i =0;
            while(i < a_name.length()) {
                String key;
                try{
                    key = a_name.getString(i);
                    Object obj = jsonobj.get(key);
                    map.put(key,parseUnknowObjectToJson(obj));
                }catch(JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
        return map;
    }
    public static HashMap<String, Object> parseJSONString(String json) {
        JSONObject obj;
        try{
            obj =new JSONObject(json);
            return parseJSONObject(obj);
        }catch(JSONException e) {
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }
    public static ArrayList<Object> parseJSONArray(JSONArray jsonarr) {
        ArrayList<Object> list =new ArrayList<Object>();
        int len = jsonarr.length();
        for(int i =0; i < len; i++) {
            Object o;
            try{
                o = jsonarr.get(i);
                list.add(parseUnknowObjectToJson(o));
            }catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    private static Object parseUnknowObjectToJson(Object o) {
        if(o instanceof JSONObject) {
            return parseJSONObject((JSONObject)o);
        }
        else if (o instanceof JSONArray) {
            return parseJSONArray((JSONArray)o);
        }
        return o;
    }
}
