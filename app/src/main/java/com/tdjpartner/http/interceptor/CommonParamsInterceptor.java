package com.tdjpartner.http.interceptor;

/**
 * Created by Administrator on 2018/3/24.
 */

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.utils.GeneralUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
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
        Request.Builder heard = request.newBuilder();
        Request newRequest = null;
        if (request.method().equals("GET")) {

            HttpUrl.Builder builder = request.url().newBuilder();
                for (String s : getSpParams().keySet()) {
                    builder.setEncodedQueryParameter(s, getSpParams().get(s));
                }
            newRequest = heard.method(request.method(), request.body()).url(builder.build()).build();
//            newRequest = request.newBuilder().url(builder.build()).build();
            return chain.proceed(newRequest);
        } else if (request.method().equals("POST")&& request.body() instanceof FormBody) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) request.body();
                for (int i = 0; i < formBody.size(); i++) {
                 bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
                for (String s : getSpParams().keySet()) {
                bodyBuilder.addEncoded(s, getSpParams().get(s));
              }
            newRequest = heard.method(request.method(), bodyBuilder.build()).build();
//            newRequest = request.newBuilder().post(bodyBuilder.build()).build();
            return chain.proceed(newRequest);
        }
        return chain.proceed(request);

    }

    public Map<String, String> getSpParams(){
        Map<String, String> params=new HashMap<>();
        params.put("versionType","Android");
        params.put("versionCode", String.valueOf(GeneralUtils.getVersionCode()));
        params.put("versionName", GeneralUtils.getVersionName());
        params.put("uniqueId",GeneralUtils.getAndroidId());
        return params;

    }
}
