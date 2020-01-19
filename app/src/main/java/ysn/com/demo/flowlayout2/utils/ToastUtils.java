package ysn.com.demo.flowlayout2.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * @Author yangsanning
 * @ClassName ToastUtils
 * @Description 通用toast类
 * @Date 2020/1/19
 * @History 2020/1/19 author: description:
 */
public class ToastUtils {
    private static Toast mToastNormal;
    private static long ThreadID = android.os.Process.myTid();
    private static Handler handler;

    /**
     * 普通的吐司提示
     *
     * @param message 吐司内容
     */
    public static void showNormalToast(final Context context, final String message) {
        long id = Thread.currentThread().getId();
        if (ThreadID == id) {
            makeToast(context, message);
        } else {
            if (handler == null) {
                handler = new Handler();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    makeToast(context, message);
                }
            });
        }
    }

    private static void makeToast(Context context, String msg) {
        ToastUtils.cancel();
        mToastNormal = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        mToastNormal.show();
    }

    public static void cancel() {
        if (mToastNormal != null) {
            mToastNormal.cancel();
        }
    }
}
