package com.tdjpartner.utils.appupdate;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * 文件名:    MyDownload
 * 描述:     TODO 自己实现apk的下载过程
 *
 */


public class MyDownload extends BaseHttpDownloadManager {

    @Override
    public void download(final String apkUrl, final String apkName, final OnDownloadListener listener) {
        listener.start();
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");//第一个参数为 返回实现指定安全套接字协议的SSLContext对象。第二个为提供者
            TrustManager[] tm = {new MyX509TrustManager()};
            sslContext.init(null, tm, new SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();



            URL url = new URL(apkUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setReadTimeout(Constant.HTTP_TIME_OUT);
            con.setConnectTimeout(Constant.HTTP_TIME_OUT);
            con.setSSLSocketFactory(ssf);
            if (con.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                InputStream is = con.getInputStream();
                int length = con.getContentLength();
                int len;
                //当前已下载完成的进度
                int progress = 0;
                byte[] buffer = new byte[1024 * 4];
                File file = FileUtil.createFile(Environment.getExternalStorageDirectory() + "/AppUpdate", apkName);
                FileOutputStream stream = new FileOutputStream(file);
                while ((len = is.read(buffer)) != -1) {
                    //将获取到的流写入文件中
                    stream.write(buffer, 0, len);
                    progress += len;
                    listener.downloading(length, progress);
                }
                //完成io操作,释放资源
                stream.flush();
                stream.close();
                is.close();
                listener.done(file);
            } else {
                listener.error(new SocketTimeoutException("连接超时！"));
            }
            con.disconnect();
        } catch (Exception e) {
            listener.error(e);
            e.printStackTrace();
        }

    }
}
