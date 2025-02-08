package com.example.read.view.activity;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.utils.IntentUtils;
import com.example.read.view.activity.home.DailyNewsActivity;

public class DeepReadActivity extends AppCompatActivity {

    private WebView mWvDeepRead;
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
        initView();
        initEvent();
    }

    private void initView() {
        mWvDeepRead = findViewById(R.id.wv_deepRead);
        mPbDeepRead = findViewById(R.id.pb_deepRead);
    }

    private void initEvent() {

        mWvDeepRead.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mPbDeepRead.setVisibility(ProgressBar.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mPbDeepRead.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(view.getContext(), "页面加载失败", Toast.LENGTH_SHORT).show();
                mPbDeepRead.setVisibility(ProgressBar.GONE);
                IntentUtils.intent(DeepReadActivity.this, DailyNewsActivity.class);
            }
        });

        mWvDeepRead.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mPbDeepRead.setProgress(newProgress);
            }
        });
        showDeepReadWeb();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void showDeepReadWeb() {
        String url = "https://m.jmss.cc/";
        WebSettings webSettings = mWvDeepRead.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWvDeepRead.loadUrl(url);
    }

    @Override
    protected void onDestroy() {
        if (mWvDeepRead != null) {
            mWvDeepRead.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWvDeepRead.clearHistory();
            ((ViewGroup) mWvDeepRead.getParent()).removeView(mWvDeepRead);
            mWvDeepRead.destroy();
            mWvDeepRead = null;
        }
        super.onDestroy();
    }

}