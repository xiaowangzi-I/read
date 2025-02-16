/**
* description ：它通过 WebView 加载新闻链接，并提供了返回按钮和进度条。 TODO:DailyNewsWebActivity 是一个用于显示每日新闻网页内容的 Activity。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月4日，18:02
*/
package com.example.read.view.activity.home.dailynews;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.views.BackView;

/**
 *
 *
 */
public class DailyNewsWebActivity extends AppCompatActivity {
    /**
     * 返回按钮视图。
     */
    private BackView mBvDailyNews;

    /**
     * WebView，用于加载和显示新闻网页。
     */
    private WebView mWvDailyNews;

    /**
     * 进度条，用于显示网页加载进度。
     */
    private ProgressBar mPbDailyNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // 启用边缘到边缘显示
        setContentView(R.layout.activity_daily_news_web);

        // 设置窗口内边距监听器，适配系统栏
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
        mBvDailyNews = findViewById(R.id.bv_dailyNewsWeb);  // 返回按钮
        mWvDailyNews = findViewById(R.id.wv_dailyNews);     // WebView
        mPbDailyNews = findViewById(R.id.pb_dailyNews);     // 进度条
    }

    /**
     * 初始化事件监听器。
     */
    private void initEvent() {
        // 设置返回按钮的点击事件
        mBvDailyNews.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 关闭当前 Activity
            }
        });

        // 设置 WebView 的客户端，处理网页加载事件
        mWvDailyNews.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());  // 加载请求的 URL
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPbDailyNews.setVisibility(ProgressBar.VISIBLE);  // 显示进度条
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbDailyNews.setVisibility(ProgressBar.GONE);  // 隐藏进度条
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mPbDailyNews.setVisibility(ProgressBar.GONE);  // 隐藏进度条
            }
        });

        // 设置 WebChromeClient，监听网页加载进度
        mWvDailyNews.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mPbDailyNews.setProgress(newProgress);  // 更新进度条进度
            }
        });

        showDailyNewsWeb();  // 加载新闻网页
    }

    /**
     * 加载新闻网页。
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void showDailyNewsWeb() {
        String url = IntentUtils.intentGetExtraString("keyDailyNewsWeb");  // 获取传递的新闻链接
        WebSettings webSettings = mWvDailyNews.getSettings();
        webSettings.setJavaScriptEnabled(true);  // 启用 JavaScript 支持

        if (url != null && !url.isEmpty()) {
            mWvDailyNews.loadUrl(url);  // 加载新闻网页
        } else {
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show();  // 提示无效链接
        }
    }

    @Override
    protected void onDestroy() {
        if (mWvDailyNews != null) {
            mWvDailyNews.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);  // 清空 WebView 内容
            mWvDailyNews.clearHistory();  // 清除历史记录
            ((ViewGroup) mWvDailyNews.getParent()).removeView(mWvDailyNews);  // 从父视图移除 WebView
            mWvDailyNews.destroy();  // 销毁 WebView
            mWvDailyNews = null;
        }
        super.onDestroy();
    }
}