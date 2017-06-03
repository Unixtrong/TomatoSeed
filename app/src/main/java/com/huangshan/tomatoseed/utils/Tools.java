package com.huangshan.tomatoseed.utils;

import android.util.Log;

import java.io.Closeable;
import java.io.IOException;


/**
 * Author(s): danyun
 * Date: 2017/5/29
 */
public class Tools {
    private static final String TAG = "TS";

    public static void debug(String msg) {
        Log.d(TAG, msg);
    }

    public static void warn(String msg, Throwable tr) {
        Log.w(TAG, msg, tr);
    }

    public static void warn(Throwable tr) {
        Log.w(TAG, tr);
    }

    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                warn(e);
            }
        }
    }
}
