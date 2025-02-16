/**
* description ：通过网络请求获取单词数据，并解析 JSON 格式的数据展示到界面上。 TODO:字典活动页面，用于展示单词的详细信息，包括拼音、笔画、部首、释义等。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月6日，21:15
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
import com.example.read.utils.IntentUtils;
import com.example.read.utils.NetworkUtils;
import com.example.read.utils.jsonutils.JSONGetWord;
import com.example.read.utils.views.BackView;
import com.example.read.view.activity.home.HomeActivity;

import java.net.MalformedURLException;
import java.net.URL;

public class DictionaryActivity extends AppCompatActivity {

    // 返回按钮视图
    private BackView mBvDictionary;
    // 单词展示文本视图
    private TextView mTvDictionaryWord;
    // 拼音展示文本视图
    private TextView mTvDictionaryPinyin;
    // 部首展示文本视图
    private TextView mTvDictionaryRadical;
    // 笔画数展示文本视图
    private TextView mTvDictionaryStrokes;
    // 释义展示文本视图
    private TextView mTvDictionaryExplain;
    // 古代写法展示文本视图
    private TextView mTvDictionaryOldWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dictionary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 初始化视图
        initView();
        // 初始化事件监听器
        try {
            initEvent();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化视图组件，绑定布局中的控件。
     */
    private void initView() {
        mBvDictionary = findViewById(R.id.bv_dictionary);
        mTvDictionaryWord = findViewById(R.id.tv_dictionaryWord);
        mTvDictionaryPinyin = findViewById(R.id.tv_dictionaryPinyin);
        mTvDictionaryRadical = findViewById(R.id.tv_dictionaryRadical);
        mTvDictionaryStrokes = findViewById(R.id.tv_dictionaryStrokes);
        mTvDictionaryExplain = findViewById(R.id.tv_dictionaryExplain);
        mTvDictionaryOldWord = findViewById(R.id.tv_dictionaryOld);
    }

    /**
     * 初始化事件监听器，包括返回按钮的点击事件和单词数据的网络请求。
     *
     * @throws MalformedURLException 如果 URL 格式错误
     */
    private void initEvent() throws MalformedURLException {
        // 设置返回按钮的点击事件
        mBvDictionary.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回上一个页面
                finish();
            }
        });

        // 显示信息
        showWord();
    }

    /**
     * 显示单词的详细信息。
     * 通过网络请求获取数据，并解析 JSON 格式的数据展示到界面上。
     *
     * @throws MalformedURLException 如果 URL 格式错误
     */
    private void showWord() throws MalformedURLException {
        String word = IntentUtils.intentGetExtraString("keyIntentWord");
        URL url = new URL("https://v3.alapi.cn/api/word?token=ucjjjrduhgtia30hr6xfvmtb85rsli&word=" + word);
        NetworkUtils.networkRequestsGet(url, this, new NetworkUtils.onDataReceivedListener() {
            @Override
            public void onDataReceive(String responseData) {
                // 解析 JSON 数据
                JSONGetWord jsonGetWord = JSONGetWord.parseJSON(responseData);
                if (jsonGetWord != null && jsonGetWord.getData() != null) {
                    // 获取单词数据
                    JSONGetWord.GetWordData getWordData = jsonGetWord.getData().get(0);
                    // 设置单词信息到视图
                    mTvDictionaryWord.setText(getWordData.getWord());
                    mTvDictionaryPinyin.setText(getWordData.getPinyin());
                    mTvDictionaryStrokes.setText(getWordData.getStrokes());
                    mTvDictionaryRadical.setText(getWordData.getRadical());
                    mTvDictionaryOldWord.setText(getWordData.getOldWord());
                    // 格式化释义文本
                    String replaceSpace = getWordData.getExplanation().replaceAll(" ", "\n");
                    String finalString = replaceSpace.replaceAll(";", "；\n");
                    mTvDictionaryExplain.setText(finalString);
                } else {
                    // 如果解析失败，显示提示并返回主页
                    Toast.makeText(DictionaryActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    IntentUtils.intent(DictionaryActivity.this, HomeActivity.class);
                }
            }
        });
    }
}