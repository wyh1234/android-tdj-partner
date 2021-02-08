package com.tdjpartner.widget.view;

import android.view.MotionEvent;

public class WheelViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

final WheelView wheelView;

        WheelViewGestureListener(WheelView wheelview) {
        wheelView = wheelview;
        }

@Override
public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        wheelView.scrollBy(velocityY);
        return true;
        }
}
