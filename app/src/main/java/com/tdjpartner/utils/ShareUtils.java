package com.tdjpartner.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.tdjpartner.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;


/**
 *
 */
public class ShareUtils {

    private Activity mContext;

    public ShareUtils(Activity mContext) {
        this.mContext = mContext;
    }

    public void shareWeb(String url, String title, String description) {
        shareWeb(R.mipmap.icon, url, title, description);
    }

    /**
     * @param obj         图标可以为int  或String
     * @param url         链接地址
     * @param title       标题
     * @param description 说明
     */
    public void shareWeb(Object obj, String url, String title, String description) {
        UMImage thumb;
        if (obj instanceof Integer) {
            thumb = new UMImage(mContext, (int) obj);
        } else {
            thumb = new UMImage(mContext, obj.toString());
        }


        UMWeb web = new UMWeb(url);
        web.setThumb(thumb);
        web.setDescription(description);
        web.setTitle(title);

        new ShareAction(mContext).withMedia(web).setDisplayList(/*SHARE_MEDIA.SINA, */SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener).open();
    }

    public ShareUtils setUmShareListener(UMShareListener umShareListener) {
        this.umShareListener = umShareListener;
        return this;
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat", "platform" + platform);
            GeneralUtils.showToastshort("分享成功");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (t != null) {
                Log.d("throw", "throw:" + t.getMessage());

                GeneralUtils.showToastshort(t.getMessage());
            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            GeneralUtils.showToastshort("分享取消");
        }
    };
}
