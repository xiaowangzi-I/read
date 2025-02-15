/**
* description ：它从 Essay.DAILYNEWS 枚举实例中获取数据，并使用 RecyclerView 展示新闻列表。提供返回按钮和新闻点击事件处理。 TODO:DailyNewsActivity 是一个显示知乎日报的 Activity。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月3日，20:58
*/
package com.example.read.view.activity.home.dailynews;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.read.R;
import com.example.read.repository.model.Essay;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.jsonutils.JSONGetDailyNews;
import com.example.read.utils.views.BackView;
import com.example.read.view.activity.home.HomeActivity;
import com.example.read.view.adapter.DailyNewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class DailyNewsActivity extends AppCompatActivity {

    /**
     * 返回按钮视图。
     */
    private BackView mBvDailyNews;

    /**
     * RecyclerView，用于展示知乎日报列表。
     */
    private RecyclerView mRvDailyNews;

    /**
     * 知乎日报适配器。
     */
    private DailyNewsAdapter dailyNewsAdapter;

    /**
     * 知乎日报数据列表。
     */
    private List<JSONGetDailyNews.GetDailyNewsData.GetDailyNews> dailyNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // 启用边缘到边缘显示
        setContentView(R.layout.activity_daily_news);

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
    public void initView() {
        mBvDailyNews = findViewById(R.id.bv_dailyNews);  // 返回按钮
        mRvDailyNews = findViewById(R.id.rv_dailyNews);  // RecyclerView
    }

    /**
     * 初始化事件监听器。
     */
    public void initEvent() {
        // 设置返回按钮的点击事件
        mBvDailyNews.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 关闭当前 Activity
            }
        });

        // 设置 RecyclerView 的布局管理器
        mRvDailyNews.setLayoutManager(new LinearLayoutManager(this));
        showDailyNews();  // 加载并展示每日新闻
    }

    /**
     * 加载并展示知乎日报。
     */
    private void showDailyNews() {
        Essay essay = Essay.DAILYNEWS;  // 获取 Essay.DAILYNEWS 枚举实例
        String getEssay = essay.get(this);  // 获取知乎日报数据

        // 解析 JSON 数据
        JSONGetDailyNews jsonGetDailyNews = JSONGetDailyNews.parseJSON(getEssay);
        if (jsonGetDailyNews != null && jsonGetDailyNews.getData() != null && jsonGetDailyNews.getData().getStories() != null) {
            dailyNewsList = new ArrayList<>();
            dailyNewsList.addAll(jsonGetDailyNews.getData().getStories());  // 将新闻数据添加到列表

            // 初始化适配器并设置点击事件
            dailyNewsAdapter = new DailyNewsAdapter(dailyNewsList);
            dailyNewsAdapter.setOnItemClickListener(new DailyNewsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    JSONGetDailyNews.GetDailyNewsData.GetDailyNews news = dailyNewsList.get(position);
                    String dailyNewsUrl = news.getUrl();  // 获取新闻链接
                    IntentUtils.intentPutExtraString(DailyNewsActivity.this, DailyNewsWebActivity.class, dailyNewsUrl, "keyDailyNewsWeb");
                }
            });

            mRvDailyNews.setAdapter(dailyNewsAdapter);  // 设置适配器

            // 添加分割线装饰
            DividerItemDecoration mDivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            mRvDailyNews.addItemDecoration(mDivider);
        } else {
            Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();  // 提示解析失败
            IntentUtils.intent(this, HomeActivity.class);  // 返回首页
        }
    }
}