package com.tdjpartner.common;


import com.tdjpartner.utils.CustomerData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicCache {
    //域名
    public static final String http_type = "real";
//    public static final String http_type = "test";
//        public static final String http_type = "model";
    public static final String specification_unit_base = "个双卷斤张天小时";//和斤一样处理的单位
    public static int flag;
    private static Map<String, List<String>> ROOT_HTTP_URL;

    public static List<String> getROOT_URL() {
        if (ROOT_HTTP_URL == null) {
            ROOT_HTTP_URL = new HashMap<>();
            //真实环境
            if (ROOT_HTTP_URL.get("real") == null)
                ROOT_HTTP_URL.put("real", new ArrayList<String>());
            ROOT_HTTP_URL.get("real").add("http://www.51taodj.com:3001/");//http://node2.51taodj.com:8001/
            ROOT_HTTP_URL.get("real").add("http://finance.51taodj.com/");
            ROOT_HTTP_URL.get("real").add("http://gateway.51taodj.com:9000/");
//            ROOT_HTTP_URL.get("real").add("http://192.168.10.239:8898/");//徐明52

            //模拟真实环境
            if (ROOT_HTTP_URL.get("model") == null)
                ROOT_HTTP_URL.put("model", new ArrayList<String>());
            ROOT_HTTP_URL.get("model").add("http://47.97.250.138:8001/");//http://node2.51taodj.com:8001/       http://121.196.199.8:8001/
            ROOT_HTTP_URL.get("model").add("http://finance.51taodj.com/");
            ROOT_HTTP_URL.get("model").add("http://121.40.170.150:9000"); //以前废弃的老接口http://gateway.51taodj.com:9000/

            //测试
            if (ROOT_HTTP_URL.get("test") == null)
                ROOT_HTTP_URL.put("test", new ArrayList<String>());
            ROOT_HTTP_URL.get("test").add("http://114.55.253.161:8001/");
            ROOT_HTTP_URL.get("test").add("http://test-finance2.51taodj.com:8080/");
            ROOT_HTTP_URL.get("test").add("http://47.111.22.162:9000/");
//            ROOT_HTTP_URL.get("test").add("http://192.168.10.239:8898/");//徐明52

        }

        return ROOT_HTTP_URL.get(http_type);
    }

    //售后类别
    private static CustomerData<Integer, String, String> afterSaleType;

    public static CustomerData<Integer, String, String> getAfterSaleType() {
        if (afterSaleType == null) {
            afterSaleType = new CustomerData<>();
            afterSaleType.put(0, 1, "退款", "");
            afterSaleType.put(1, 3, "退款退货", "");
            afterSaleType.put(2, 2, "换货", "");
            afterSaleType.put(3, 4, "补货", "");
        }
        return afterSaleType;
    }

    public static CustomerData<Integer, String, String> getAfterSaleProblem() {
        CustomerData<Integer, String, String> afterSaleProblem = new CustomerData<>();
        afterSaleProblem.put(0, 1, "未收到货", "");
        afterSaleProblem.put(1, 2, "差称", "");


        afterSaleProblem.put(2, 9, "有烂坏", "");
        afterSaleProblem.put(3, 10, "有异味", "");
        afterSaleProblem.put(4, 11, "有发霉", "");
        afterSaleProblem.put(5, 12, "有虫眼", "");


        //        afterSaleProblem.put(6, 3, "质量问题", "");
        afterSaleProblem.put(7, 5, "品种不符", "");
        afterSaleProblem.put(8, 6, "发错货", "");
        afterSaleProblem.put(9, 7, "下错单", "");


        // afterSaleProblem.put(10, 8, "取消商品", "");
        afterSaleProblem.put(15, 4, "其它", "");

        afterSaleProblem.put(11, 13, "压坏", "");
        afterSaleProblem.put(12, 14, "捂坏", "");
        afterSaleProblem.put(13, 15, "化冻", "");
        afterSaleProblem.put(14, 16, "闷死", "");
        return afterSaleProblem;
    }

}
