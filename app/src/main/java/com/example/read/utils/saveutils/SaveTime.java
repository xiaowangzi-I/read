/**
* description ：使用 SharedPreferences 来存储和检索时间戳（长整型值）。 TODO:SaveTime 类用于保存和获取时间戳。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月8日，23:16
*/
package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 *
 */
public class SaveTime {
    /**
     * SharedPreferences 文件名，用于存储时间戳。
     */
    private static final String prefsTime = "time";

    /**
     * SharedPreferences 键名，用于存储时间戳的长整型值。
     */
    private static final String keyTime = "keyTime";

    /**
     * SharedPreferences 实例，用于存储和读取时间戳。
     */
    private final SharedPreferences timeSharePreference;

    /**
     * 构造函数，初始化 SharedPreferences 实例。
     *
     * @param context 应用程序上下文。
     */
    public SaveTime(Context context) {
        timeSharePreference = context.getSharedPreferences(prefsTime, Context.MODE_PRIVATE);
    }

    /**
     * 保存时间戳到 SharedPreferences。
     *
     * @param time 要保存的时间戳（长整型值）。
     */
    public void setTime(Long time) {
        SharedPreferences.Editor editor = timeSharePreference.edit();
        editor.putLong(keyTime, time);
        editor.apply();
    }

    /**
     * 从 SharedPreferences 获取保存的时间戳。
     *
     * @return 保存的时间戳（长整型值）。如果未找到，则返回默认值 0。
     */
    public Long getTime() {
        return timeSharePreference.getLong(keyTime, 0L);
    }
}