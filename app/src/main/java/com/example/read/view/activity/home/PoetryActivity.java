package com.example.read.view.activity.home;

import android.os.Bundle;
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
import com.example.read.utils.saveutils.SaveGetEssay;
import com.example.read.utils.jsonutils.JSONGetPoetry;

import java.net.MalformedURLException;

public class PoetryActivity extends AppCompatActivity {

    private TextView mTvPoetryTitle;
    private TextView mTvPoetryAuthor;
    private TextView mTvPoetryContent;

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
        try {
            initEvent();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initView () {
        mTvPoetryTitle = findViewById(R.id.tv_poetryTitle);
        mTvPoetryAuthor = findViewById(R.id.tv_poetryAuthor);
        mTvPoetryContent = findViewById(R.id.tv_PoetryContent);
    }

    private void initEvent () throws MalformedURLException {
        showPoetry();
    }

    private void showPoetry () throws MalformedURLException {
        GetEssay.getAPoetry(this);
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