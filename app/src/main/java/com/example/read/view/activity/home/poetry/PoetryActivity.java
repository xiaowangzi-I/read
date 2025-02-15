/**
* description ：它从 Essay.POETRY 枚举实例中获取数据。 TODO:是一个显示诗歌的 Activity。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月31日，12:56
*/
package com.example.read.view.activity.home.poetry;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.repository.model.Essay;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.jsonutils.JSONGetPoetry;
import com.example.read.utils.views.BackView;
import com.example.read.utils.views.SearchBoxView;
import com.example.read.view.activity.home.HomeActivity;

public class PoetryActivity extends AppCompatActivity {

    private BackView mBvPoetry;
    private TextView mTvPoetryTitle;
    private TextView mTvPoetryAuthor;
    private TextView mTvPoetryContent;
    private SearchBoxView mSbPoetryDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_poetry);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    /**
     * 初始化界面中的视图组件。
     */
    private void initView() {
        mBvPoetry = findViewById(R.id.bv_poetry);
        mTvPoetryTitle = findViewById(R.id.tv_poetryTitle);
        mTvPoetryAuthor = findViewById(R.id.tv_poetryAuthor);
        mTvPoetryContent = findViewById(R.id.tv_PoetryContent);
        mSbPoetryDictionary = findViewById(R.id.sb_poetry);
    }

    /**
     * 初始化事件监听器，包括返回按钮和搜索框的点击事件。
     */
    private void initEvent() {
        mBvPoetry.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSbPoetryDictionary.setOnSearchClickListener(new SearchBoxView.OnSearchClickListener() {
            @Override
            public void onSearch(String searchText) {
                IntentUtils.intentPutExtraString(PoetryActivity.this, DictionaryActivity.class, searchText, "keyIntentWord");
            }
        });

        mSbPoetryDictionary.setEtHint("新华字典");
    }

    /**
     * 显示诗词内容，通过获取 JSON 数据并解析后填充到界面。
     */
    private void showPoetry() {
        Essay essay = Essay.POETRY;
        String getEssay = essay.get(this);
        JSONGetPoetry jsonGetPoetry = JSONGetPoetry.parseJSON(getEssay);
        if (jsonGetPoetry != null && jsonGetPoetry.getResult() != null && jsonGetPoetry.getResult().getList() != null) {
            JSONGetPoetry.GetPoetryResult.GetPoetry poetry = jsonGetPoetry.getResult().getList().get(0);
            mTvPoetryTitle.setText(poetry.getTitle());
            mTvPoetryAuthor.setText(poetry.getAuthor());
            mTvPoetryContent.setText(poetry.getContent());
        } else {
            Toast.makeText(this, "解析失败", Toast.LENGTH_SHORT).show();
            IntentUtils.intent(this, HomeActivity.class);
        }
    }
}