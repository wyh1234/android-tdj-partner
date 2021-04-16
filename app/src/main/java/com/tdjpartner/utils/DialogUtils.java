package com.tdjpartner.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.tdjpartner.R;


/**
 * Created by yangkuo on 2018-08-14.
 */
public class DialogUtils {

    public static Dialog getResourceDialog(Context context, int resourceId, @Nullable View.OnClickListener ok, @Nullable View.OnClickListener deny){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(resourceId);
        dialog.setCanceledOnTouchOutside(false);

        if(ok != null) dialog.findViewById(R.id.dialog_btn_yes).setOnClickListener(ok);
        if(ok != null) dialog.findViewById(R.id.dialog_btn_no).setOnClickListener(deny);

        Point point = new Point();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getSize(point);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int) (point.x);
        dialog.getWindow().setAttributes(layoutParams);
        return dialog;
    }
}
