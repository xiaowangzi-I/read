/**
* description ：包括搜索框、菜单按钮、以及跳转到诗歌、新闻、散文、每日新闻和深度阅读等功能。 TODO:HomeActivity 是应用的主页面，提供导航到不同功能模块的入口。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月25日，22:25
*/
package com.example.read.view.activity.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.repository.network.GetEssay;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.NetworkUtils;
import com.example.read.utils.saveutils.SaveIsLogged;
import com.example.read.utils.saveutils.SavePortrait;
import com.example.read.utils.saveutils.SaveTime;
import com.example.read.utils.views.SearchBoxView;
import com.example.read.view.activity.home.dailynews.DailyNewsActivity;
import com.example.read.view.activity.home.poetry.PoetryActivity;
import com.example.read.view.activity.log.LogActivity;
import com.example.read.view.activity.menu.PersonCenterActivity;

import java.net.MalformedURLException;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {

    /**
     * 搜索框视图。
     */
    private SearchBoxView mSbHome;

    /**
     * 菜单按钮。
     */
    private Button mBtMenu;

    /**
     * 菜单弹窗。
     */
    private PopupWindow mPwMenu;

    /**
     * 诗歌按钮。
     */
    private Button mBtPoetry;

    /**
     * 新闻按钮。
     */
    private Button mBtNews;

    /**
     * 散文按钮。
     */
    private Button mBtProse;

    /**
     * 每日新闻按钮。
     */
    private Button mBtDailyNews;

    /**
     * 深度阅读按钮。
     */
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

    /**
     * 初始化视图组件。
     */
    private void initView() {
        mBtMenu = findViewById(R.id.bt_menu);
        mSbHome = findViewById(R.id.sb_home);
        mBtPoetry = findViewById(R.id.bt_poetry);
        mBtNews = findViewById(R.id.bt_news);
        mBtProse = findViewById(R.id.bt_prose);
        mBtDailyNews = findViewById(R.id.bt_dailyNews);
        mBtDeepRead = findViewById(R.id.bt_deepRead);
    }

    /**
     * 初始化事件监听器。
     * 包括搜索框、菜单按钮、功能按钮等的点击事件。
     */
    public void initEvent() throws MalformedURLException {
        checkAndRefresh(); // 检查是否需要刷新数据

        // 设置用户登录状态
        SaveIsLogged saveIsLogged = new SaveIsLogged(this);
        saveIsLogged.setIsLogged(true);

        // 菜单按钮点击事件
        mBtMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSidePopupWindow();
            }
        });

        // 搜索框点击事件
        mSbHome.setOnSearchClickListener(new SearchBoxView.OnSearchClickListener() {
            @Override
            public void onSearch(String searchText) {
                IntentUtils.intentPutExtraString(HomeActivity.this, SearchActivity.class, searchText, "keyIntentSearch");
            }
        });

        // 设置搜索框提示
        mSbHome.setEtHint("请输入任意问题");

        // 诗歌按钮点击事件
        mBtPoetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPoetry();
            }
        });

        // 新闻按钮点击事件
        mBtNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNews();
            }
        });

        // 散文按钮点击事件
        mBtProse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProse();
            }
        });

        // 每日新闻按钮点击事件
        mBtDailyNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDailyNews();
            }
        });

        // 深度阅读按钮点击事件
        mBtDeepRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDeepRead();
            }
        });
    }

    /**
     * 跳转到诗歌页面。
     */
    private void toPoetry() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, PoetryActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 跳转到新闻页面。
     */
    private void toNews() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, NewsActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 跳转到散文页面。
     */
    private void toProse() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, ProseActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 跳转到每日新闻页面。
     */
    private void toDailyNews() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, DailyNewsActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 跳转到深度阅读页面。
     */
    private void toDeepRead() {
        if (NetworkUtils.isNetworkAvailable(this)) {
            IntentUtils.intent(this, DeepReadActivity.class);
        } else {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示侧边菜单弹窗。
     */
    private void showSidePopupWindow() {
        @SuppressLint("InflateParams") View popupView = LayoutInflater.from(this).inflate(R.layout.menu_popup, null);
        mPwMenu = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        mPwMenu.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mPwMenu.setOutsideTouchable(true);
        mPwMenu.setFocusable(true);

        Button mBtBack = popupView.findViewById(R.id.bt_backMenu);
        ImageView mIvPortrait = popupView.findViewById(R.id.iv_portraitMenu);
        Button mBtLogout = popupView.findViewById(R.id.bt_logout);
        Button mBtPersonCenter = popupView.findViewById(R.id.bt_personCenter);

        mIvPortrait.setImageBitmap(SavePortrait.getImage(HomeActivity.this));

        mBtBack.setOnClickListener(v -> mPwMenu.dismiss());
        mPwMenu.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPwMenu.showAtLocation(findViewById(R.id.main), Gravity.START, 0, 0);

        mBtLogout.setOnClickListener(v -> {
            mPwMenu.dismiss();
            IntentUtils.intent(HomeActivity.this, LogActivity.class);
            SaveIsLogged saveIsLogged = new SaveIsLogged(HomeActivity.this);
            saveIsLogged.setIsLogged(false);
            finish();
        });

        mBtPersonCenter.setOnClickListener(v -> {
            mPwMenu.dismiss();
            IntentUtils.intent(HomeActivity.this, PersonCenterActivity.class);
        });
    }

    /**
     * 检查是否需要刷新数据。
     * 如果上次保存的时间是昨天或更早，则刷新数据。
     */
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

    /**
     * 检查是否已经过了午夜。
     * 比较当前日期和上次保存的时间，判断是否需要刷新数据。
     *
     * @param lastTime 上次保存的时间（毫秒值）。
     * @return 如果当前日期大于上次保存的日期，返回 true，表示需要刷新数据。
     */
    private boolean hasMidnightPassed(long lastTime) {
        Calendar now = Calendar.getInstance(); // 获取当前时间
        Calendar last = Calendar.getInstance(); // 获取上次保存的时间
        last.setTimeInMillis(lastTime); // 设置上次保存的时间

        // 比较当前日期和上次保存的日期
        return now.get(Calendar.DATE) > last.get(Calendar.DATE);
    }

}