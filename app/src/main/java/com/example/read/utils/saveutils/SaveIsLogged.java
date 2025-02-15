/**
* description ：使用 SharedPreferences 来存储用户的登录状态（布尔值）。 TODO:SaveIsLogged 类用于保存和获取用户的登录状态。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月27日，11:48
*/
package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveIsLogged {
    /**
     * SharedPreferences 文件名，用于存储登录状态。
     */
    private static final String prefsIsLogged = "prefsIsLogged";

    /**
     * SharedPreferences 键名，用于存储登录状态的布尔值。
     */
    private static final String keyIsLogged = "keyIsLogged";

    /**
     * SharedPreferences 实例，用于存储和读取登录状态。
     */
    private final SharedPreferences isLoggedSharePreferences;

    /**
     * 构造函数，初始化 SharedPreferences 实例。
     *
     * @param context 应用程序上下文。
     */
    public SaveIsLogged(Context context) {
        isLoggedSharePreferences = context.getSharedPreferences(prefsIsLogged, Context.MODE_PRIVATE);
    }

    /**
     * 获取用户的登录状态。
     *
     * @return 用户的登录状态（true 表示已登录，false 表示未登录）。
     */
    public boolean getIsLogged() {
        return isLoggedSharePreferences.getBoolean(keyIsLogged, false);
    }

    /**
     * 设置用户的登录状态。
     *
     * @param isLogged 用户的登录状态（true 表示已登录，false 表示未登录）。
     */
    public void setIsLogged(boolean isLogged) {
        SharedPreferences.Editor editor = isLoggedSharePreferences.edit();
        editor.putBoolean(keyIsLogged, isLogged);
        editor.apply();
    }
}