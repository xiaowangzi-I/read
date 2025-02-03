package com.example.read.view.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.utils.saveutils.SaveIsLogged;
import com.example.read.utils.IntentUtils;
import com.example.read.utils.NetworkUtils;

public class HomeActivity extends AppCompatActivity {

    private EditText mEtSearchBox;
    private Button mBtSearch;
    private Button mBtPoetry;
    private Button mBtNews;
    private Button mBtProse;

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
        initEvent();
    }

    private void initView () {
        mEtSearchBox = findViewById(R.id.et_searchBox);
        mBtPoetry = findViewById(R.id.bt_poetry);
        mBtSearch = findViewById(R.id.bt_search);
        mBtNews = findViewById(R.id.bt_news);
        mBtProse = findViewById(R.id.bt_prose);
    }

    public void initEvent () {
        SaveIsLogged saveIsLogged = new SaveIsLogged(this);
        saveIsLogged.setIsLogged(true);
        checkNetworkState();

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

    }

    private void checkNetworkState () {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        }
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

}