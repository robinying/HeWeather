package com.yubin.heweather.utils;

import android.util.Log;

/**
 * author : Yubin.Ying
 * time : 2018/11/19
 */
public class XLog {
    private static int V = 1;
    private static int D = 2;
    private static int I = 3;
    private static int W = 4;
    private static int E = 5;


    private static int LEVEL = 0;
    private static String TAG = XLog.class.getSimpleName();

    static {
        XLog.LEVEL = 6;
//        XLog.LEVEL = 0;
    }

    public static void setLevel(int level) {
        if (level < 0) {
            closeLog();
        } else if (level >= 6) {
            openLog();
        } else {
            XLog.LEVEL = level;
        }
    }

    public static void closeLog() {
        XLog.LEVEL = 0;
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (msg == null || msg.isEmpty()) {
            msg = "data is null";
        }
        if (XLog.LEVEL >= D) {
            Log.d(tag, msg);
        }
    }

    public static void e(int n) {
        e(TAG, n);
    }

    public static void e(Exception ex) {
        if (XLog.LEVEL >= E) {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            XLog.e(TAG, ex.getLocalizedMessage());
            if (stackTrace.length > 3) {
                for (int i = 0; i < 3; ++i) {
                    StackTraceElement stackTraceElement = stackTrace[i];
                    Log.e(TAG, "-----name----->" + stackTraceElement.getClassName());
                    Log.e(TAG, "-----medthod----->" + stackTraceElement.getMethodName());
                    Log.e(TAG, "-----num----->" + stackTraceElement.getLineNumber());
                }
            }
        }
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, float n) {
        if (XLog.LEVEL >= E) {
            Log.e(tag, n + "");
        }
    }

    public static void e(String tag, int n) {
        if (XLog.LEVEL >= E) {
            Log.e(tag, n + "");
        }
    }

    public static void e(String tag, Exception ex) {
        if (XLog.LEVEL >= E) {
            StackTraceElement[] stackTrace = ex.getStackTrace();
            eLine();
            XLog.e(tag, ex.getLocalizedMessage());
            if (stackTrace.length > 3) {
                for (int i = 0; i < 3; ++i) {
                    StackTraceElement stackTraceElement = stackTrace[i];
                    Log.e(tag, "-----name----->" + stackTraceElement.getClassName());
                    Log.e(tag, "-----medthod----->" + stackTraceElement.getMethodName());
                    Log.e(tag, "-----num----->" + stackTraceElement.getLineNumber());
                }
            }
            eLine();
        }
    }

    public static void e(String tag, String msg) {
        if (msg == null) {
            msg = "data is null";
        } else if (msg.isEmpty()) {
            msg = "data is \" \"";
        }
        if (XLog.LEVEL >= E) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable t) {
        if (XLog.LEVEL >= E) {
            Log.e(tag, msg, t);
        }
    }

    public static void e(String tag, boolean b) {
        if (XLog.LEVEL >= E) {
            Log.e(tag, b + "");
        }
    }

    public static void e(Throwable t) {
        if (XLog.LEVEL >= E) {
            for (StackTraceElement stackTraceElement : t.getStackTrace()) {
                Log.e(TAG, "======>>>>>>>>>>>>ClassName:" + stackTraceElement.getClassName() + "\n" + "MethodName:" + stackTraceElement.getMethodName() + "\n" + "LineNumber:" + stackTraceElement.getLineNumber() + "\n");
            }
        }
    }

    public static void e(boolean b) {
        e(TAG, b);
    }

    public static void e(String[] array) {
        if (XLog.LEVEL >= E && array != null) {
            eLine();
            for (int length = array.length, i = 0; i < length; ++i) {
                e(array[i]);
            }
            eLine();
        }
    }

    public static void eLine() {
        e(TAG, "================================");
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (msg == null || msg.isEmpty()) {
            msg = "data is null";
        }
        if (XLog.LEVEL >= I) {
            Log.i(tag, msg);
        }
    }

    public static void openLog() {
        XLog.LEVEL = 6;
    }

    public static void v(String msg) {
        v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (msg == null || msg.isEmpty()) {
            msg = "data is null";
        }
        if (XLog.LEVEL >= V) {
            Log.v(tag, msg);
        }
    }

    public static void w(String msg) {
        w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (msg == null || msg.isEmpty()) {
            msg = "data is null";
        }
        if (XLog.LEVEL >= W) {
            Log.w(tag, msg);
        }
    }
}
