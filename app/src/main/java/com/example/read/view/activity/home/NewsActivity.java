package com.example.read.view.activity.home;

import android.os.Bundle;
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
import com.example.read.view.adapter.NewsAdapter;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

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
        try {
            initEvent();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initView () {
        mRvNews = findViewById(R.id.rv_news);
    }

    private void initEvent () throws MalformedURLException {
        mRvNews.setLayoutManager(new LinearLayoutManager(this));
        showNews();
    }

    private void showNews () throws MalformedURLException {
        GetEssay.getNews(this);
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