package com.example.read.view.activity.home;

import android.os.Bundle;
import android.util.Log;
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
import com.example.read.utils.NetworkUtils;
import com.example.read.utils.jsonutils.JSONGetDailyNews;
import com.example.read.view.activity.DailyNewsWebActivity;
import com.example.read.view.adapter.DailyNewsAdapter;
import com.example.read.view.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

public class DailyNewsActivity extends AppCompatActivity {

    private RecyclerView mRvDailyNews;
    private DailyNewsAdapter dailyNewsAdapter;
    private List<JSONGetDailyNews.GetDailyNewsData.GetDailyNews> dailyNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_news);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    public void initView() {
        mRvDailyNews = findViewById(R.id.rv_dailyNews);
    }

    public void initEvent() {
        mRvDailyNews.setLayoutManager(new LinearLayoutManager(this));
        showDailyNews();
    }

    private void showDailyNews() {
        Essay essay = Essay.DAILYNEWS;
        String getEssay = essay.get(this);
        JSONGetDailyNews jsonGetDailyNews = JSONGetDailyNews.parseJSON(getEssay);
        if (jsonGetDailyNews != null && jsonGetDailyNews.getData() != null && jsonGetDailyNews.getData().getStories() != null) {
            dailyNewsList = new ArrayList<>();
            dailyNewsList.addAll(jsonGetDailyNews.getData().getStories());
            dailyNewsAdapter = new DailyNewsAdapter(dailyNewsList);
            dailyNewsAdapter.setOnItemClickListener(new DailyNewsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    JSONGetDailyNews.GetDailyNewsData.GetDailyNews news = dailyNewsList.get(position);
                    String dailyNewsUrl = news.getUrl();
                    IntentUtils.intentPutExtraString(DailyNewsActivity.this, DailyNewsWebActivity.class, dailyNewsUrl, "keyDailyNewsWeb");
                }
            });
            mRvDailyNews.setAdapter(dailyNewsAdapter);
            DividerItemDecoration mDivider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            mRvDailyNews.addItemDecoration(mDivider);
        } else {
            Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
            IntentUtils.intent(this, HomeActivity.class);
        }
    }
}