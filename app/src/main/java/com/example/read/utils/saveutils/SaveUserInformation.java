/**
* description ：使用 SharedPreferences 来存储和检索用户信息。 TODO:SaveUserInformation 类用于保存和获取用户信息（用户名和密码）。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月25日，22:30
*/
package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveUserInformation {
    /**
     * SharedPreferences 文件名，用于存储用户名。
     */
    private static final String prefsUserName = "prefsUserName";

    /**
     * SharedPreferences 文件名，用于存储用户密码。
     */
    private static final String prefsUserPassword = "prefsUserPassword";

    /**
     * SharedPreferences 键名，用于存储用户名。
     */
    private static final String keyUserName = "keyUserName";

    /**
     * SharedPreferences 键名，用于存储用户密码。
     */
    private static final String keyUserPassword = "keyUserPassword";

    /**
     * SharedPreferences 实例，用于存储和读取用户名。
     */
    private final SharedPreferences userNameSharePreference;

    /**
     * SharedPreferences 实例，用于存储和读取用户密码。
     */
    private final SharedPreferences userPasswordSharePreference;

    /**
     * 构造函数，初始化 SharedPreferences 实例。
     *
     * @param context 应用程序上下文。
     */
    public SaveUserInformation(Context context) {
        userNameSharePreference = context.getSharedPreferences(prefsUserName, Context.MODE_PRIVATE);
        userPasswordSharePreference = context.getSharedPreferences(prefsUserPassword, Context.MODE_PRIVATE);
    }

    /**
     * 获取保存的用户名。
     *
     * @return 保存的用户名，如果未找到则返回 null。
     */
    public String getUserName() {
        return userNameSharePreference.getString(keyUserName, null);
    }

    /**
     * 获取保存的用户密码。
     *
     * @return 保存的用户密码，如果未找到则返回 null。
     */
    public String getUserPassword() {
        return userPasswordSharePreference.getString(keyUserPassword, null);
    }

    /**
     * 保存用户名到 SharedPreferences。
     *
     * @param userName 要保存的用户名。
     */
    public void setUserName(String userName) {
        SharedPreferences.Editor editor = userNameSharePreference.edit();
        editor.putString(keyUserName, userName);
        editor.apply();
    }

    /**
     * 保存用户密码到 SharedPreferences。
     *
     * @param userPassword 要保存的用户密码。
     */
    public void setPassword(String userPassword) {
        SharedPreferences.Editor editor = userPasswordSharePreference.edit();
        editor.putString(keyUserPassword, userPassword);
        editor.apply();
    }
}