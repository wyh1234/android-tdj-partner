package com.tdjpartner.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewUrils {

    public static <T extends View> T getFragmentView(ViewGroup parent, @LayoutRes int layoutResid) {
        return (T) LayoutInflater.from(parent.getContext()).inflate(layoutResid, parent, false);
    }

    /**
     * 获取布局
     *
     * @ resId
     * @
     */

    public static <T extends View> T getLayoutView(Context context, @LayoutRes int res_layid) {
        return (T) LayoutInflater.from(context).inflate(res_layid, null);
    }

    /* 获取根视图 */
    public static View getRootView(Activity act) {
        return act.getWindow().getDecorView().findViewById(android.R.id.content);
    }



}
