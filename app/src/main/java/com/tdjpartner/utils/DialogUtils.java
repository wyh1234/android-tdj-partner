package com.tdjpartner.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
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

    public static Dialog getResourceDialog(Context context, int resourceId, @Nullable View.OnClickListener ok, @Nullable View.OnClickListener deny) {
        return getResourceDialog(context, resourceId, null, null, ok, deny);
    }

    public static Dialog getResourceDialog(Context context, int resourceId, String title, String hint, @Nullable View.OnClickListener ok, @Nullable View.OnClickListener deny) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(resourceId);
        dialog.setCanceledOnTouchOutside(false);

        if (!TextUtils.isEmpty(title))
            ((TextView) dialog.findViewById(R.id.tv_title)).setText(title);
        if (!TextUtils.isEmpty(hint))
            ((TextView) dialog.findViewById(R.id.et_content)).setHint(hint);

        if (ok != null) dialog.findViewById(R.id.dialog_btn_yes).setOnClickListener(ok);
        if (ok != null) dialog.findViewById(R.id.dialog_btn_no).setOnClickListener(deny);

        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int) (point.x);
        dialog.getWindow().setAttributes(layoutParams);
        return dialog;
    }
}
