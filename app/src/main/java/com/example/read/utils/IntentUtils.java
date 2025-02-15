/**
* description ：提供静态方法用于启动 Activity，并支持传递额外的数据。 TODO:IntentUtils 是一个工具类，用于简化 Intent 的创建和数据传递。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月2日，16:38
*/
package com.example.read.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtils {

    /**
     * 用于存储当前 Intent 的静态变量。
     * 注意：此变量应仅在方法内部使用，避免外部直接访问。
     */
    private static Intent intent;

    /**
     * 启动目标 Activity。
     *
     * @param thisContext 当前上下文（通常为 Activity 或 Context）。
     * @param targetActivityClass 目标 Activity 的类。
     */
    public static void intent(Context thisContext, Class<?> targetActivityClass) {
        intent = new Intent(thisContext, targetActivityClass);
        thisContext.startActivity(intent);
    }

    /**
     * 启动目标 Activity，并传递一个字符串数据。
     *
     * @param thisContext 当前上下文（通常为 Activity 或 Context）。
     * @param targetActivityClass 目标 Activity 的类。
     * @param value 要传递的字符串值。
     * @param keyIntent 传递数据时使用的键名。
     */
    public static void intentPutExtraString(Context thisContext, Class<?> targetActivityClass, String value, String keyIntent) {
        intent = new Intent(thisContext, targetActivityClass);
        intent.putExtra(keyIntent, value);
        thisContext.startActivity(intent);
    }

    /**
     * 从 Intent 中获取传递的字符串数据。
     *
     * @param keyIntent 传递数据时使用的键名。
     * @return 返回传递的字符串值，如果未找到则返回 null。
     */
    public static String intentGetExtraString(String keyIntent) {
        return intent.getStringExtra(keyIntent);
    }
}