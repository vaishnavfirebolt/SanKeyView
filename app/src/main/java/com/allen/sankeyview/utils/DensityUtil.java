package com.allen.sankeyview.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * <p>Wind Resolution Conversion Tool</p>
 *
 * @author guor
 * @version 2017-6-20 18:19
 */
public class DensityUtil {

    /**
     * <p>Convert from dp units to px (pixels) according to the resolution of the phone</p>
     *
     * @param context context
     * @param dpValue dp value
     * @return px value
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * <p>Convert from px (pixel) unit to dp</p>
     *
     * @param context context
     * @param pxValue px value
     * @return dp value
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * <p>Convert from px (pixel) unit to sp</p>
     *
     * @param context 上下文环境
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * <p>Convert from the unit of sp to px (pixel) according to the resolution of the mobile phone</p>
     *
     * @param context 上下文环境
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * <p>Get screen width (pixel value)</p>
     *
     * @param context 上下文环境
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * <p>Get screen height (pixel value)</p>
     *
     * @param context 上下文环境
     * @return 屏幕高度
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}
