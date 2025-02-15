/**
* description ：它从 Essay.NEWS 枚举实例中获取数据 TODO:NewsActivity 是一个用于阅读新闻的 Activity。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月2日，17:03
*/
package com.example.read.view.activity.home;

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
import com.example.read.repository.network.GetEssay;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.jsonutils.JSONGetNews;
import com.example.read.utils.saveutils.SaveGetEssay;
import com.example.read.utils.views.BackView;
import com.example.read.view.adapter.NewsAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private BackView mBvNews;
    private RecyclerView mRvNews;
    private NewsAdapter newsAdapter;
    private List<JSONGetNews.GetNewsResult.GetNews> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    /**
     * 初始化视图组件。
     */
    private void initView() {
        mBvNews = findViewById(R.id.bv_news);
        mRvNews = findViewById(R.id.rv_news);
    }

    /**
     * 初始化事件监听器。
     */
    private void initEvent() {
        mBvNews.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRvNews.setLayoutManager(new LinearLayoutManager(this));
        showNews();
    }

    /**
     * 加载并展示新闻列表。
     */
    private void showNews() {
        Essay essay = Essay.NEWS;
        String getEssay = essay.get(this);
        JSONGetNews jsonGetNews = JSONGetNews.parseJSON(getEssay);

        if (jsonGetNews != null && jsonGetNews.getResult() != null && jsonGetNews.getResult().getList() != null) {
            newsList = new ArrayList<>();
            newsList.addAll(jsonGetNews.getResult().getList());
            newsAdapter = new NewsAdapter(newsList);
            mRvNews.setAdapter(newsAdapter);

            DividerItemDecoration mDivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            mRvNews.addItemDecoration(mDivider);
        } else {
            Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
            IntentUtils.intent(this, HomeActivity.class);
        }
    }
}