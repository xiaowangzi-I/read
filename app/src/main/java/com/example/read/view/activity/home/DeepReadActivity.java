/**
* description ：通过 WebView 加载指定的网页内容，并提供了返回按钮和进度条。此外，还支持通过返回键返回上一页或关闭 Activity。 TODO:DeepReadActivity 是一个用于深度阅读的 Activity。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月8日，21:13
*/
package com.example.read.view.activity.home;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.views.BackView;

public class DeepReadActivity extends AppCompatActivity {
    /**
     * 返回按钮视图。
     */
    private BackView mBvDeepRead;

    /**
     * WebView，用于加载和显示网页内容。
     */
    private WebView mWvDeepRead;

    /**
     * 进度条，用于显示网页加载进度。
     */
    private ProgressBar mPbDeepRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_deep_read);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();  // 初始化视图
        initEvent(); // 初始化事件
    }

    /**
     * 初始化视图组件。
     */
    private void initView() {
        mBvDeepRead = findViewById(R.id.bv_deepRead);  // 返回按钮
        mWvDeepRead = findViewById(R.id.wv_deepRead);  // WebView
        mPbDeepRead = findViewById(R.id.pb_deepRead);  // 进度条
    }

    /**
     * 初始化事件监听器。
     */
    private void initEvent() {
        // 设置返回按钮的点击事件
        mBvDeepRead.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 关闭当前 Activity
            }
        });

        // 设置 WebView 的客户端，处理网页加载事件
        mWvDeepRead.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());  // 加载请求的 URL
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPbDeepRead.setVisibility(ProgressBar.VISIBLE);  // 显示进度条
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbDeepRead.setVisibility(ProgressBar.GONE);  // 隐藏进度条
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(view.getContext(), "页面加载失败", Toast.LENGTH_SHORT).show();  // 提示加载失败
                mPbDeepRead.setVisibility(ProgressBar.GONE);  // 隐藏进度条
                IntentUtils.intent(DeepReadActivity.this, HomeActivity.class);  // 返回首页
            }
        });

        // 设置 WebChromeClient，监听网页加载进度
        mWvDeepRead.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mPbDeepRead.setProgress(newProgress);  // 更新进度条进度
            }
        });

        // 处理返回键事件
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mWvDeepRead.canGoBack()) {
                    mWvDeepRead.goBack();  // 返回上一页
                } else {
                    remove();  // 移除回调
                    finish();  // 关闭 Activity
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        showDeepReadWeb();  // 加载网页
    }

    /**
     * 加载指定的网页内容。
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void showDeepReadWeb() {
        String url = "https://www.biquge11.cc/";  // 笔趣阁网站地址
        WebSettings webSettings = mWvDeepRead.getSettings();
        webSettings.setJavaScriptEnabled(true);  // 启用 JavaScript 支持
        webSettings.setAllowFileAccessFromFileURLs(true);  // 允许从文件 URL 访问文件
        webSettings.setAllowUniversalAccessFromFileURLs(true);  // 允许从文件 URL 访问通用资源

        mWvDeepRead.loadUrl(url);  // 加载网页
    }

    @Override
    protected void onDestroy() {
        if (mWvDeepRead != null) {
            mWvDeepRead.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);  // 清空 WebView 内容
            mWvDeepRead.clearHistory();  // 清除历史记录
            ((ViewGroup) mWvDeepRead.getParent()).removeView(mWvDeepRead);  // 从父视图移除 WebView
            mWvDeepRead.destroy();  // 销毁 WebView
            mWvDeepRead = null;
        }
        super.onDestroy();
    }
}