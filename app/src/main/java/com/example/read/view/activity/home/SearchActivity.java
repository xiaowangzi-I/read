/**
* description ：通过打开夸克搜索 TODO:SearchActivity 是一个用于搜索的 Activity。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月9日，16:32
*/
package com.example.read.view.activity.home;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
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

public class SearchActivity extends AppCompatActivity {

    private WebView mWvHome;
    private ProgressBar mPbHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    /**
     * 初始化视图组件，绑定 WebView 和 ProgressBar。
     */
    private void initView() {
        mWvHome = findViewById(R.id.wv_home);
        mPbHome = findViewById(R.id.pb_home);
    }

    /**
     * 初始化事件监听器，设置 WebView 的加载事件和返回按钮的点击事件。
     */
    private void initEvent() {
        mWvHome.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPbHome.setVisibility(ProgressBar.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbHome.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(view.getContext(), "页面加载失败", Toast.LENGTH_SHORT).show();
                mPbHome.setVisibility(ProgressBar.GONE);
                IntentUtils.intent(SearchActivity.this, HomeActivity.class);
            }
        });

        mWvHome.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mPbHome.setProgress(newProgress);
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mWvHome.canGoBack()) {
                    mWvHome.goBack();
                } else {
                    remove();
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        showSearch();
    }

    /**
     * 加载搜索结果页面，通过 WebView 加载搜索 URL。
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void showSearch() {
        String textSearch = IntentUtils.intentGetExtraString("keyIntentSearch");
        String url = "https://quark.sm.cn/s?q=" + textSearch;
        WebSettings webSettings = mWvHome.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        mWvHome.loadUrl(url);
    }

    /**
     * 清理 WebView 资源，防止内存泄漏。
     */
    @Override
    protected void onDestroy() {
        if (mWvHome != null) {
            mWvHome.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWvHome.clearHistory();
            ((ViewGroup) mWvHome.getParent()).removeView(mWvHome);
            mWvHome.destroy();
            mWvHome = null;
        }
        super.onDestroy();
    }
}