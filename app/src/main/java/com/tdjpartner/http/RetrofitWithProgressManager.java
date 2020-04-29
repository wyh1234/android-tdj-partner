package com.tdjpartner.http;


import com.tdjpartner.common.PublicCache;
import com.tdjpartner.http.interceptor.RetrofitDownloadInterceptor;
import com.tdjpartner.http.listener.RetrofitDownloadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Administrator on 2018/6/23.
 */

public class RetrofitWithProgressManager {

    private static RetrofitWithProgressManager mRetrofitWithProgressManager;
    private static final int DEFAULT_TIMEOUT = 15;
    private Retrofit retrofit;
    private RetrofitDownloadListener listener;


    private RetrofitWithProgressManager(RetrofitDownloadListener listener) {
        this.listener = listener;
        RetrofitDownloadInterceptor mInterceptor = new RetrofitDownloadInterceptor(listener);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(mInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(PublicCache.getROOT_URL().get(0))
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitWithProgressManager getInstance(RetrofitDownloadListener retrofitDownloadListener) {
        if(mRetrofitWithProgressManager == null){
            synchronized (RetrofitServiceManager.class){
                if(mRetrofitWithProgressManager == null){
                    mRetrofitWithProgressManager = new RetrofitWithProgressManager(retrofitDownloadListener);
                }
            }
        }
        return mRetrofitWithProgressManager;
    }


    public <T> T creat(Class<T> tClass) {
        return retrofit.create(tClass);
    }



    /**
     * 将输入流写入文件
     *
     * @param inputString
     * @param filePath
     */
    private void writeFile(InputStream inputString, String filePath) {

        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];

            int len;
            while ((len = inputString.read(b)) != -1) {
                fos.write(b,0,len);
            }
            inputString.close();
            fos.close();

        } catch (FileNotFoundException e) {
            listener.onFail("FileNotFoundException");
        } catch (IOException e) {
            listener.onFail("IOException");
        }

    }
   /* public void download(View view) {//下载，要implements RetrofitDownloadListener
        addSubscribe(RetrofitWithProgressManager

                .getInstance(this)
                .creat(ApiService.class)
                .downLoad("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529672863611&di=e9f66a3571b06f405c6f99d0df55b954&imgtype=0&src=http%3A%2F%2Fimg.fashionmoon.com%2Fuploadfile%2F2014%2F0628%2F20140628060940275.jpg")
                .subscribeOn(Schedulers.io())
                .map(ResponseBody::byteStream)
                .observeOn(Schedulers.computation())
                .doOnNext(inputStream -> {




                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseObserver<InputStream>(this) {

                    @Override
                    protected void onSuccess(InputStream inputStream) {
                        ThreadPoolManager.getInstance().submit(() -> {
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            runOnUiThread(() -> iv.setImageBitmap(bitmap));
                        });
                    }
                })
        );

    }*/
}
