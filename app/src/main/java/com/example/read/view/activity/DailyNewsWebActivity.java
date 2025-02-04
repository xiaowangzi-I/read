package com.example.read.view.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.utils.IntentUtils;
import com.example.read.view.activity.home.DailyNewsActivity;

public class DailyNewsWebActivity extends AppCompatActivity {
    private WebView mWvDailyNews;
    private ProgressBar mPbDailyNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_news_web);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    private void initView() {
        mWvDailyNews = findViewById(R.id.wv_dailyNews);
        mPbDailyNews = findViewById(R.id.pb_dailyNews);
    }

    private void initEvent() {
        mWvDailyNews.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPbDailyNews.setVisibility(ProgressBar.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbDailyNews.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(view.getContext(), "页面加载失败", Toast.LENGTH_SHORT).show();
                mPbDailyNews.setVisibility(ProgressBar.GONE);
                IntentUtils.intent(DailyNewsWebActivity.this, DailyNewsActivity.class);
            }
        });

        mWvDailyNews.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mPbDailyNews.setProgress(newProgress);
            }
        });

        showDailyNewsWeb();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showDailyNewsWeb() {
        String url = IntentUtils.intentGetExtraString("keyDailyNewsWeb");
        WebSettings webSettings = mWvDailyNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (url != null && !url.isEmpty()) {
            mWvDailyNews.loadUrl(url);
        } else {
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (mWvDailyNews != null) {
            mWvDailyNews.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWvDailyNews.clearHistory();
            ((ViewGroup) mWvDailyNews.getParent()).removeView(mWvDailyNews);
            mWvDailyNews.destroy();
            mWvDailyNews = null;
        }
        super.onDestroy();
    }
}