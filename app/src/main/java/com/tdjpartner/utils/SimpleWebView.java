package com.tdjpartner.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.tdjpartner.widget.ProgressDialog;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebSettings;

import java.util.Map;

public class SimpleWebView extends com.tencent.smtt.sdk.WebView{
    public SimpleWebView(Context context) {
        super(context);
        init();
    }



    public SimpleWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SimpleWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public SimpleWebView(Context context, AttributeSet attributeSet, int i, boolean b) {
        super(context, attributeSet, i, b);
        init();
    }

    public SimpleWebView(Context context, AttributeSet attributeSet, int i, Map<String, Object> map, boolean b) {
        super(context, attributeSet, i, map, b);
        init();
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        this.setWebViewClient(new SimpleWebViewClient());

        this.setWebChromeClient(new SimpleWebChromeClient()
  /*      {
            //这里可以设置进度条。但我是用另外一种
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
            }
        }*/);



    }


    public static class SimpleWebChromeClient extends com.tencent.smtt.sdk.WebChromeClient {

        @Override
        public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int newProgress) {
            super.onProgressChanged(webView, newProgress);
        }
/*
        public boolean onJsAlert(com.tencent.smtt.sdk.WebView webView, String url, String message,
                                 final JsResult result) {
            LogUtils.i(message);

            AlertDialog.Builder b = new AlertDialog.Builder(webView.getContext());
            b.setTitle("警告");
            b.setMessage(message);
            b.setPositiveButton("确定",
                    new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            result.confirm();

                        }
                    });
            b.setCancelable(true);
            b.create();
            b.show();
            return true;
        }*/
        @Override
        public void onReceivedTitle(com.tencent.smtt.sdk.WebView webView, String titles) {
            // TODO Auto-generated method stub
            super.onReceivedTitle(webView, titles);
//            title.setText(title_str);
        }

 /*       public boolean onJsConfirm(android.webkit.WebView view, String url,
                                   String message, final JsResult result) {
            AlertDialog.Builder b = new AlertDialog.Builder(view.getContext());
            b.setTitle("提示");
            b.setMessage(message);
            b.setPositiveButton("确定",
                    new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            result.confirm();

                        }
                    });
            b.setNegativeButton("取消", new AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,
                                    int which) {
                    result.cancel();
                }
            });
            b.setCancelable(true);
            b.create();
            b.show();
            return true;

        }*/
    }

    public static class SimpleWebViewClient extends com.tencent.smtt.sdk.WebViewClient {
        ProgressDialog loadingDialog;

        @Override
        public com.tencent.smtt.export.external.interfaces.WebResourceResponse shouldInterceptRequest(com.tencent.smtt.sdk.WebView webView, String url) {
            //做广告拦截，ADFIlterTool 为广告拦截工具类
            if (!ADFilterTool.hasAd(webView.getContext(),url)){
                return super.shouldInterceptRequest(webView, url);
            }else {
                return new WebResourceResponse(null,null,null);
            }
        }
        /**
         * 防止加载网页时调起系统浏览器
         */
        @Override
        public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }
        //在开始的时候，开始loadingDialog
        @Override
        public void onPageStarted(com.tencent.smtt.sdk.WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            try{
                loadingDialog = ProgressDialog.createDialog(webView.getContext());
                loadingDialog.setMessage("加载中...");
                loadingDialog.show();
            }catch (Exception e){}
        }
        //在页面加载结束的时候，关闭LoadingDialog
        @Override
        public void onPageFinished(com.tencent.smtt.sdk.WebView webView, String s) {
            super.onPageFinished(webView, s);
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
        }

        @Override
        public void onReceivedError(com.tencent.smtt.sdk.WebView webView, com.tencent.smtt.export.external.interfaces.WebResourceRequest webResourceRequest, com.tencent.smtt.export.external.interfaces.WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);

        }

        @Override
        public void onReceivedSslError(com.tencent.smtt.sdk.WebView webView, com.tencent.smtt.export.external.interfaces.SslErrorHandler sslErrorHandler, com.tencent.smtt.export.external.interfaces.SslError sslError) {
            sslErrorHandler.proceed();
        }

    }

}
