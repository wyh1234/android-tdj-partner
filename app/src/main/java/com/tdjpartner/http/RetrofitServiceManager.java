package com.tdjpartner.http;

import android.os.Environment;

import com.ihsanbal.logging.LoggingInterceptor;
import com.tdjpartner.AppAplication;
import com.tdjpartner.common.PublicCache;
import com.tdjpartner.http.interceptor.CommonParamsInterceptor;
import com.tdjpartner.http.cookie.CookieManger;
import com.tdjpartner.http.interceptor.HttpCacheInterceptor;
import com.tdjpartner.http.interceptor.HttpHeaderInterceptor;
import com.tdjpartner.http.interceptor.RetrofitDownloadInterceptor;
import com.tdjpartner.http.listener.RetrofitDownloadListener;
import com.tdjpartner.utils.jackSon.JacksonConverterFactoryCustomer;
import com.tdjpartner.utils.jackSon.JacksonUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//import com.franmontiel.persistentcookiejar.PersistentCookieJar;
//import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
//import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;


/**
 * @author Administrator
 * @date 2018/3/24
 */

public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 10;//超时时间
    private static final int DEFAULT_READ_TIME_OUT = 10;//读取时间
    private static final int DEFAULT_WRITE_TIME_OUT = 10;//读取时间
    private static RetrofitServiceManager mRetrofitServiceManager;
    private Retrofit mRetrofit;
    private RetrofitDownloadListener retrofitDownloadListener;

    private RetrofitServiceManager() {

        /**
         * 设置证书的三种方式
         */
        /**
         * 设置可访问所有的https网站
         */
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
//                其他配置
//                .build();

        /**
         *  设置具体的证书
         */

//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(证书的inputstream, null, null);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager))
//        //其他配置
//         .build();

        /**双向认证
         *
         */
//        HttpsUtils.getSslSocketFactory(
//                证书的inputstream,
//                本地证书的inputstream,
//                本地证书的密码)


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS);
        //设置支持所有https请求
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        builder.hostnameVerifier((hostname, session) -> true).sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

//        builder.cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance())));
//        builder.cookieJar(new CookieManger(UIUtils.getContext()));
        addInterceptor(builder);


        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(PublicCache.getROOT_URL().get(0))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(JacksonConverterFactoryCustomer.create(JacksonUtils.getObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * 添加各种拦截器
     *
     * @param builder
     */
    private void addInterceptor(OkHttpClient.Builder builder) {
        // 添加日志拦截器，非debug模式不打印任何日志
        //打印网络请求日志
        LoggingInterceptor httpLoggingInterceptor = new LoggingInterceptor.Builder().loggable(true)
//                    .setLevel(Level.BASIC)
//                    .log(Platform.INFO)
                .request("reader")
//                    .response("reader")
                .build();

        RetrofitDownloadInterceptor retrofitDownloadInterceptor = new RetrofitDownloadInterceptor(retrofitDownloadListener);

        HttpHeaderInterceptor httpHeaderInterceptor = new HttpHeaderInterceptor.Builder().build();
        HttpCacheInterceptor httpCacheInterceptor = new HttpCacheInterceptor();
        if (retrofitDownloadListener != null) {
            builder.addInterceptor(retrofitDownloadInterceptor);
        }


        builder.addInterceptor(httpHeaderInterceptor);
        builder.addInterceptor(new CommonParamsInterceptor());
//        builder.addInterceptor(new TokenInterceptor());
        builder.addInterceptor(httpCacheInterceptor);
        builder.addNetworkInterceptor(httpCacheInterceptor);
        builder.cookieJar(new CookieManger(AppAplication.getAppContext()));
        builder.addInterceptor(httpLoggingInterceptor);
        builder.cache(new Cache(new File(Environment.getExternalStorageDirectory() + "/RxJavaDemo"), 1024 * 1024 * 10));

    }

    public static RetrofitServiceManager getInstance() {
        return SingletonHolder.retrofitServiceManager;

    }

    public <T> T creat(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    private static class SingletonHolder {
        private static RetrofitServiceManager retrofitServiceManager = new RetrofitServiceManager();
    }

}
