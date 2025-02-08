package com.example.read.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.repository.model.Essay;
import com.example.read.repository.network.GetEssay;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.NetworkUtils;
import com.example.read.utils.jsonutils.JSONGetWord;
import com.example.read.utils.saveutils.SaveGetEssay;
import com.example.read.view.activity.home.HomeActivity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class DictionaryActivity extends AppCompatActivity {

    private TextView mTvDictionaryWord;
    private TextView mTvDictionaryPinyin;
    private TextView mTvDictionaryRadical;
    private TextView mTvDictionaryStrokes;
    private TextView mTvDictionaryExplain;
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
        initView();
        try {
            initEvent();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initView() {
        mTvDictionaryWord = findViewById(R.id.tv_dictionaryWord);
        mTvDictionaryPinyin = findViewById(R.id.tv_dictionaryPinyin);
        mTvDictionaryRadical = findViewById(R.id.tv_dictionaryRadical);
        mTvDictionaryStrokes = findViewById(R.id.tv_dictionaryStrokes);
        mTvDictionaryExplain = findViewById(R.id.tv_dictionaryExplain);
        mTvDictionaryOldWord = findViewById(R.id.tv_dictionaryOld);
    }

    private void initEvent() throws MalformedURLException {
        showWord();
    }

    private void showWord() throws MalformedURLException {
        String word = IntentUtils.intentGetExtraString("keyIntentWord");
        URL url = new URL("https://v3.alapi.cn/api/word?token=ucjjjrduhgtia30hr6xfvmtb85rsli&word=" + word);
        NetworkUtils.networkRequestsGet(url, this, new NetworkUtils.onDataReceivedListener() {
            @Override
            public void onDataReceive(String responseData) {
                JSONGetWord jsonGetWord = JSONGetWord.parseJSON(responseData);
                if (jsonGetWord != null && jsonGetWord.getData() != null) {
                    JSONGetWord.GetWordData getWordData = jsonGetWord.getData().get(0);
                    mTvDictionaryWord.setText(getWordData.getWord());
                    mTvDictionaryPinyin.setText(getWordData.getPinyin());
                    mTvDictionaryStrokes.setText(getWordData.getStrokes());
                    mTvDictionaryRadical.setText(getWordData.getRadical());
                    mTvDictionaryOldWord.setText(getWordData.getOldWord());
                    String replaceSpace = getWordData.getExplanation().replaceAll(" ", "\n");
                    String finalString = replaceSpace.replaceAll(";", "；\n");
                    mTvDictionaryExplain.setText(finalString);
                } else {
                    Toast.makeText(DictionaryActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    IntentUtils.intent(DictionaryActivity.this, HomeActivity.class);
                }
            }
        });
    }
}