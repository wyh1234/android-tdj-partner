package com.tdjpartner.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.tdjpartner.R;


/**
 * Created by LFM on 2021/4/16.
 */
public class DialogUtils {

    public static Dialog getResourceDialog(Context context, int resourceId) {
        return getResourceDialog(context, resourceId, null, null, null, null, null);
    }

    public static Dialog getResourceDialog(Context context, int resourceId, @Nullable View.OnClickListener ok, @Nullable View.OnClickListener deny) {
        return getResourceDialog(context, resourceId, null, null, ok, deny, null);
    }

    public static Dialog getResourceDialog(Context context, int resourceId, @Nullable View.OnClickListener ok, @Nullable View.OnClickListener deny, @Nullable View.OnClickListener close) {
        return getResourceDialog(context, resourceId, null, null, ok, deny, close);
    }

    public static Dialog getResourceDialog(Context context, int resourceId, String title, String hint, @Nullable View.OnClickListener ok, @Nullable View.OnClickListener deny, @Nullable View.OnClickListener close) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(resourceId);
        dialog.setCanceledOnTouchOutside(false);

        if (!TextUtils.isEmpty(title))
            ((TextView) dialog.findViewById(R.id.tv_title)).setText(title);
        if (!TextUtils.isEmpty(hint))
            ((TextView) dialog.findViewById(R.id.et_content)).setHint(hint);

        View view;
        if (ok != null) {
            view = dialog.findViewById(R.id.dialog_btn_yes);
            if (view != null) view.setOnClickListener(ok);
            view = dialog.findViewById(R.id.dialog_tv_yes);
            if (view != null) view.setOnClickListener(ok);
        }
        if (deny != null) {
            view = dialog.findViewById(R.id.dialog_btn_no);
            if (view != null) view.setOnClickListener(deny);
            view = dialog.findViewById(R.id.dialog_tv_no);
            if (view != null) view.setOnClickListener(deny);
        }
        if (close != null) {
            view = dialog.findViewById(R.id.tv_close);
            if (view != null) view.setOnClickListener(close);
        }

        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int) (point.x);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    public static void dismissDelay(Dialog dialog, long time) {
        if (!dialog.isShowing()) return;
        new Thread(() -> {
            try {
                Thread.sleep(time);
                dialog.dismiss();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
