/**
* description ：提供方法将 Bitmap 图像保存到设备的私有存储中，并从存储中读取头像。 TODO:SavePortrait 类用于保存和获取用户头像。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月12日，20:51
*/
package com.example.read.utils.saveutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 *
 */
public class SavePortrait {

    /**
     * 将用户头像保存到设备的私有存储中。
     *
     * @param context 应用程序上下文。
     * @param bitmap  要保存的头像 Bitmap 对象。
     */
    public static void setImage(Context context, Bitmap bitmap) {
        try {
            FileOutputStream fos = context.openFileOutput("portrait", Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save portrait image", e);
        }
    }

    /**
     * 从设备的私有存储中获取用户头像。
     *
     * @param context 应用程序上下文。
     * @return 返回用户头像的 Bitmap 对象；如果头像不存在，则返回 null。
     */
    public static Bitmap getImage(Context context) {
        File file = new File(context.getFilesDir(), "portrait");
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        } else {
            return null;
        }
    }
}