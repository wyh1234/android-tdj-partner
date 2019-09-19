package com.tdjpartner.http.interceptor;

import com.apkfuns.logutils.LogUtils;
import com.tdjpartner.common.ApiService;
import com.tdjpartner.common.RequestPresenter;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;

public class TokenInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // try the request
        Response originalResponse = chain.proceed(request);

        /**通过如下的办法曲线取到请求完成的数据
         *
         * 原本想通过  originalResponse.body().string()
         * 去取到请求完成的数据,但是一直报错,不知道是okhttp的bug还是操作不当
         *
         * 然后去看了okhttp的源码,找到了这个曲线方法,取到请求完成的数据后,根据特定的判断条件去判断token过期
         */
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = buffer.clone().readString(charset);
        LogUtils.d("body---------->" + bodyString);


        
        /***************************************/
        if (isTokenExpired(originalResponse)) {//根据和服务端的约定判断token过期
            // create a new request and modify it accordingly using the new token
            Request newRequest = request.newBuilder().header("token", getNewToken())
                    .build();
            // retry the request
            originalResponse.body().close();
            return chain.proceed(newRequest);
        }

        // otherwise just pass the original response on
        return originalResponse;
    }


    /**
     * * 根据Response，判断Token是否失效
     * *
     * * @param response
     * * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 404) {
            return true;
        }
        return false;
    }

    /**
     * 41      * 同步请求方式，获取最新的Token
     * 42      *
     * 43      * @return
     * 44
     */
    private String getNewToken() throws IOException {


        //取出本地的refreshToken
        String refreshToken = "sssgr122222222";
        String newToken=null;
        Call<String> call;
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        ApiService service = RequestPresenter.getApiService();
//        call = service.refreshToken(refreshToken);

        //要用retrofit的同步方式
//         newToken = call.execute().body();

        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
    /*    Response_Login loginInfo = CacheManager.restoreLoginInfo(BaseApplication.getContext());
        String username = loginInfo.getUserName();
        String password = loginInfo.getPassword();

        LogUtil.print("loginInfo=" + loginInfo.toString());
        Call<Response_Login> call = WebHelper.getSyncInterface().synclogin(new Request_Login(username, password));
        loginInfo = call.execute().body();
        LogUtil.print("loginInfo=" + loginInfo.toString());

        loginInfo.setPassword(password);
        CacheManager.saveLoginInfo(loginInfo);*/
        return newToken;
    }

}
