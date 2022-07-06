package com.example.laogao.communication_sdk.util;

import android.util.Log;

import java.util.Arrays;

public class LogUtil {

    public static boolean DEBUG = true;

    private static final String TAG = "laogao";


    public static void d(Object... objects) {
        if (DEBUG) {
            Log.d(TAG, Arrays.toString(objects));
        }
    }

    public static void i(Object... objects) {
        if (DEBUG) {
            Log.i(TAG, Arrays.toString(objects));
        }
    }

    public static void e(Object... objects) {
        if (DEBUG) {
            Log.e(TAG, Arrays.toString(objects));
        }
    }
}
