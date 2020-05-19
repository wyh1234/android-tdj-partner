package com.tdjpartner.http.interceptor;

/**
 * Created by Administrator on 2018/3/24.
 */

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tdjpartner.utils.GeneralUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求公共参数插入器
 * <p>
 *
 * @author Administrator
 */
public class CommonParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = null;
        if (request.method().equals("GET")) {
            HttpUrl.Builder builder = request.url().newBuilder();
                for (String s : getSpParams().keySet()) {
                    builder.setEncodedQueryParameter(s, getSpParams().get(s));
                }
            newRequest = request.newBuilder().url(builder.build()).build();
            return chain.proceed(newRequest);
        } else if (request.method().equals("POST")&& request.body() instanceof FormBody) {//表单请求
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();
                for (int i = 0; i < formBody.size(); i++) {
                 bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                for (String s : getSpParams().keySet()) {
                bodyBuilder.addEncoded(s, getSpParams().get(s));
              }
            newRequest = request.newBuilder().post(bodyBuilder.build()).build();
            return chain.proceed(newRequest);
        }else if (request.method().equals("POST")&& request.body() instanceof PostJsonBody){//json请求
            Gson gson=   new Gson();
            String content = ((PostJsonBody) request.body()).getContent();
            HashMap<String, Object> hashMap = JsonHelper.parseJSONString(content);
            for (String s : getSpParams().keySet()) {
                hashMap.put(s, getSpParams().get(s));
            }
            newRequest= request.newBuilder().post( RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(hashMap))).build();
            return chain.proceed(newRequest);
        }
        return chain.proceed(request);

    }

    public Map<String, String> getSpParams(){//公共请求参数
        Map<String, String> params=new HashMap<>();
        params.put("versionType","Android");
        params.put("versionCode", String.valueOf(GeneralUtils.getVersionCode()));
        params.put("versionName", GeneralUtils.getVersionName());
        params.put("uniqueId",GeneralUtils.getAndroidId());
        return params;

    }
}
