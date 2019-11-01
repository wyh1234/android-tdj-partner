package com.tdjpartner.common;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicCache {

    //域名
//    public static final String http_type = "real";
    public static final String http_type = "test";
//    public static final String http_type = "model";

    private static Map<String, List<String>> ROOT_HTTP_URL;

    public static List<String> getROOT_URL() {
        if (ROOT_HTTP_URL == null) {
            ROOT_HTTP_URL = new HashMap<>();
            //真实环境
            if (ROOT_HTTP_URL.get("real") == null) ROOT_HTTP_URL.put("real", new ArrayList<String>());
            ROOT_HTTP_URL.get("real").add("http://www.51taodj.com:3001/");//http://node2.51taodj.com:8001/
            ROOT_HTTP_URL.get("real").add("http://finance.51taodj.com/");
            ROOT_HTTP_URL.get("real").add("http://gateway.51taodj.com:9000/");
            ROOT_HTTP_URL.get("real").add("http://47.111.22.162:9000/");
//            ROOT_HTTP_URL.get("real").add("http://192.168.10.239:8898/");//徐明52

            //模拟真实环境
            if (ROOT_HTTP_URL.get("model") == null) ROOT_HTTP_URL.put("model", new ArrayList<String>());
            ROOT_HTTP_URL.get("model").add("http://47.97.250.138:8001/");//http://node2.51taodj.com:8001/       http://121.196.199.8:8001/
            ROOT_HTTP_URL.get("model").add("http://finance.51taodj.com/");

            //测试
            if (ROOT_HTTP_URL.get("test") == null) ROOT_HTTP_URL.put("test", new ArrayList<String>());
            ROOT_HTTP_URL.get("test").add("http://114.55.253.161:8001/");
            ROOT_HTTP_URL.get("test").add("http://test-finance2.51taodj.com:8080/");
            ROOT_HTTP_URL.get("test").add("http://47.111.22.162:9000/");
            ROOT_HTTP_URL.get("test").add("http://47.111.22.162:9000/");
//            ROOT_HTTP_URL.get("test").add("http://192.168.10.239:8898/");//徐明52



        }

        return ROOT_HTTP_URL.get(http_type);
    }


}
