package com.example.read.view.activity.home;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.utils.saveutils.SaveTime;
import com.example.read.view.activity.DeepReadActivity;
import com.example.read.R;
import com.example.read.repository.network.GetEssay;
import com.example.read.utils.saveutils.SaveIsLogged;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.NetworkUtils;
import com.example.read.view.activity.log.LogActivity;

import java.net.MalformedURLException;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    private EditText mEtSearchBox;
    private Button mBtMenu;
    private PopupWindow mPwMenu;
    private Button mBtSearch;
    private Button mBtPoetry;
    private Button mBtNews;
    private Button mBtProse;
    private Button mBtDailyNews;
    private Button mBtDeepRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        try {
            initEvent();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SaveTime saveTime = new SaveTime(this);
        saveTime.setTime(System.currentTimeMillis());
    }

    private void initView () {
        mBtMenu = findViewById(R.id.bt_menu);
        mEtSearchBox = findViewById(R.id.et_searchBox);
        mBtPoetry = findViewById(R.id.bt_poetry);
        mBtSearch = findViewById(R.id.bt_search);
        mBtNews = findViewById(R.id.bt_news);
        mBtProse = findViewById(R.id.bt_prose);
        mBtDailyNews = findViewById(R.id.bt_dailyNews);
        mBtDeepRead = findViewById(R.id.bt_deepRead);
    }

    public void initEvent () throws MalformedURLException {
        checkAndRefresh();
        SaveIsLogged saveIsLogged = new SaveIsLogged(this);
        saveIsLogged.setIsLogged(true);

        mBtMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSidePopupWindow();
            }
        });

        mBtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtPoetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPoetry();
            }
        });

        mBtNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNews();
            }
        });

        mBtProse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProse();
            }
        });

        mBtDailyNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDailyNews();
            }
        });

        mBtDeepRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDeepRead();
            }
        });
    }

    private void toPoetry () {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, PoetryActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private void toNews () {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, NewsActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private void toProse () {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, ProseActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private void toDailyNews () {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, DailyNewsActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private void toDeepRead () {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, DeepReadActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSidePopupWindow() {
        @SuppressLint("InflateParams") View popupView = LayoutInflater.from(this).inflate(R.layout.menu_popup, null);
        mPwMenu = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        mPwMenu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mPwMenu.setOutsideTouchable(true);
        mPwMenu.setFocusable(true);

        Button mBtBack = popupView.findViewById(R.id.bt_backMenu);
        Button mBtLogout = popupView.findViewById(R.id.bt_logout);

        mBtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPwMenu.dismiss();
            }
        });
        mPwMenu.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPwMenu.showAtLocation(findViewById(R.id.main), Gravity.START, 0, 0);

        mBtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPwMenu.dismiss();
                IntentUtils.intent(HomeActivity.this, LogActivity.class);
                SaveIsLogged saveIsLogged = new SaveIsLogged(HomeActivity.this);
                saveIsLogged.setIsLogged(false);
                finish();
            }
        });
    }

    private void checkAndRefresh() throws MalformedURLException {
        SaveTime saveTime = new SaveTime(this);
        long lastTime = saveTime.getTime();

        if (hasMidnightPassed(lastTime)) {
            if (NetworkUtils.isNetworkAvailable(this)) {
                GetEssay.getNews(this);
                GetEssay.getAPoetry(this);
                GetEssay.getAProse(this);
                GetEssay.getDailyNews(this);
            } else {
                Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean hasMidnightPassed(long lastTime) {
        Calendar now = Calendar.getInstance();
        Calendar last = Calendar.getInstance();
        last.setTimeInMillis(lastTime);

        return now.get(Calendar.DATE) > last.get(Calendar.DATE);
    }

}