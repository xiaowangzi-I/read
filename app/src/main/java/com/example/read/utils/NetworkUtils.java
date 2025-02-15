/**
* description ：提供方法检查网络状态、发送 GET 请求、处理响应数据以及加载图像。 TODO:NetworkUtils 是一个工具类，用于处理网络请求和图像加载。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月26日，22:46
*/
package com.example.read.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.read.R;
import com.example.read.repository.model.Essay;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    /**
     * 检查设备是否连接到可用的网络。
     *
     * @param context 应用程序上下文。
     * @return 如果网络可用返回 true，否则返回 false。
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = connectivityManager.getActiveNetwork();
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
        if (networkCapabilities != null) {
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        }
        return false;
    }

    /**
     * 发送 GET 请求并处理响应数据。
     * 将响应数据保存到指定的 Essay 枚举实例中。
     *
     * @param url     请求的 URL。
     * @param context 应用程序上下文。
     * @param essay   Essay 枚举实例，用于保存响应数据。
     */
    public static void networkRequestAndSavesGet(URL url, Context context, Essay essay) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "请求失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();
                    response.close();
                    new Handler(Looper.getMainLooper()).post(() -> {
                        essay.set(context, responseData);  // 保存响应数据
                        Log.i("NetworkUtils", "Response Data: " + responseData);
                    });
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(context, "请求失败，错误码: " + response.code(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    /**
     * 发送 GET 请求并处理响应数据。
     * 将响应数据传递给回调接口的监听器。
     *
     * @param url     请求的 URL。
     * @param context 应用程序上下文。
     * @param listener 数据接收的回调监听器。
     */
    public static void networkRequestsGet(URL url, Context context, onDataReceivedListener listener) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    Toast.makeText(context, "请求失败: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();
                    response.close();
                    new Handler(Looper.getMainLooper()).post(() -> {
                        listener.onDataReceive(responseData);  // 调用回调接口
                    });
                } else {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(context, "请求失败，错误码: " + response.code(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }

    /**
     * 定义数据接收的回调接口。
     */
    public interface onDataReceivedListener {
        /**
         * 数据接收的回调方法。
         *
         * @param responseData 接收到的响应数据。
         */
        void onDataReceive(String responseData);
    }

    /**
     * 使用 Glide 加载图像。
     *
     * @param url       图像的 URL。
     * @param context   应用程序上下文。
     * @param imageView 要加载图像的 ImageView。
     */
    public static void imageRequest(String url, Context context, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)  // 加载中的占位图
                .error(R.drawable.failedload)    // 加载失败的错误图
                .centerCrop();                   // 图像裁剪方式

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imageView);
    }
}