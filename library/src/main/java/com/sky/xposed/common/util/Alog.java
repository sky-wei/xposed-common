/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.common.util;

import android.util.Log;

/**
 * Created by sky on 2018/8/8.
 */
public class Alog {

    public final static String TAG = "Xposed";

    private static boolean sDebug = false;

    public static boolean isDebug() {
        return Alog.sDebug;
    }

    public static void setDebug(boolean debug) {
        Alog.sDebug = debug;
    }

    public static void i(String msg) {
        if (sDebug) i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (sDebug) Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (sDebug) Log.i(tag, msg, tr);
    }

    public static void d(String msg) {
        if (sDebug) d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (sDebug) Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (sDebug) Log.d(tag, msg, tr);
    }

    public static void e(String msg) {
        if (sDebug) e(TAG, msg);
    }

    public static void e(String msg, Throwable tr) {
        if (sDebug) e(TAG, msg, tr);
    }

    public static void e(String tag, String msg) {
        if (sDebug) Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (sDebug) Log.e(tag, msg, tr);
    }

    public static void v(String msg) {
        if (sDebug) v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (sDebug) Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (sDebug) Log.v(tag, msg, tr);
    }

    public static void w(String msg) {
        if (sDebug) w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (sDebug) Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (sDebug) Log.w(tag, msg, tr);
    }
}
