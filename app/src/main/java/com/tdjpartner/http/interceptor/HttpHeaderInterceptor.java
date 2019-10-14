package com.tdjpartner.http.interceptor;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.tdjpartner.AppAplication;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.http.cookie.CookieManger;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Android对Retrofit的BaseUrl的适配
 * 使用Headers请求的特性，里面的键值可以为我们区别请求的url
 * @Headers({"url_type:weather"})  //url_type用来区别调用接口的标志（）
 * Created by Administrator on 2018/3/24.
 */

public class HttpHeaderInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    public HttpHeaderInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d("HttpCommonInterceptor", "add common params");
        Request oldRequest = chain.request();
        HttpUrl oldHttpUrl = oldRequest.url();
        mHeaderParamsMap.put("user-agent", "Android");
        mHeaderParamsMap.put("Content-Type", "application/json");
//        mHeaderParamsMap.put("sign", "dJi89de3cfd3249c8ef");
        //添加请求头
        List<Cookie> cookieList =new CookieManger(AppAplication.getAppContext()).loadForRequest(HttpUrl.parse(PublicCache.getROOT_URL().get(0)));
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(),
                oldRequest.body());

        StringBuilder stringBuilder=new StringBuilder();
        if (cookieList.size() > 0) {
            for (Cookie params : cookieList) {
                if ("_n_i".equals(params.name())){
                    stringBuilder.append(params.name()+"="+params.value()+";");
                }
                if ("_oi_".equals(params.name())){
                    stringBuilder.append(params.name()+"="+params.value()+";");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            requestBuilder.header("cookie", stringBuilder.toString());
        }
        //添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        List<String> headerValues = oldRequest.headers("url_type");
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            requestBuilder.removeHeader("url_type");
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            if ("launcher".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(PublicCache.getROOT_URL().get(1));
            }else if ("weather".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(PublicCache.getROOT_URL().get(0));
            }else if ("xuming".equals(headerValue)){
                newBaseUrl = HttpUrl.parse(PublicCache.getROOT_URL().get(3));
            }else {
                newBaseUrl = oldHttpUrl;
            }
            //重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(newBaseUrl.scheme())//更换网络协议
                    .host(newBaseUrl.host())//更换主机名
                    .port(newBaseUrl.port())//更换端口
//                            .removePathSegment(0)//移除第一个参数
                    .build();
            //重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            return chain.proceed(requestBuilder.url(newFullUrl).build());
        }
        return chain.proceed(requestBuilder.build());
    }

    public static class Builder {
        HttpHeaderInterceptor mHttpHeaderInterceptor;

        public Builder() {
            mHttpHeaderInterceptor = new HttpHeaderInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpHeaderInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HttpHeaderInterceptor build() {
            return mHttpHeaderInterceptor;
        }
    }


}
