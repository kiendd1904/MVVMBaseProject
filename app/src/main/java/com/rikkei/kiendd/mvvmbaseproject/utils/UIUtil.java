package com.rikkei.kiendd.mvvmbaseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.Display;

public class UIUtil {
    private static boolean isClicked = false;
    private static final Handler CLICK_HANDLER = new Handler(
            Looper.getMainLooper());
    private static final long CLICK_DELAY = 500;


    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static int widthScreenPixel(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        return metrics.widthPixels;
    }

    public static int heightScreenPixel(Context context) {
        if (context == null) {
            return 0;
        }
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        return metrics.heightPixels;
    }

    public static int heightRealScreenPixel(Activity activity) {
        if (activity == null) {
            return 0;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);
        return metrics.heightPixels;
    }

    public static boolean isClickable() {
        synchronized (CLICK_HANDLER) {
            if (!isClicked) {
                isClicked = true;
                CLICK_HANDLER.postDelayed(() -> isClicked = false, CLICK_DELAY);
                return true;
            }
            return false;
        }
    }

    public static int getSoftButtonsBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

}
