package com.example.read.view.activity.log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.view.activity.home.HomeActivity;
import com.example.read.utils.saveutils.SaveIsLogged;
import com.example.read.utils.saveutils.SaveUserInformation;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LogActivity extends AppCompatActivity {
    private TextInputEditText mEtUserName;
    private TextInputEditText mEtUserPassword;
    private Button mBtLog;
    private Button mBtToResign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
        SaveIsLogged saveIsLogged = new SaveIsLogged(this);
        Intent intent = new Intent(LogActivity.this, HomeActivity.class);
        if (saveIsLogged.getIsLogged()) {
            startActivity(intent);
            finish();
        }
    }

//    初始化
    private void initView() {
        mEtUserName = findViewById(R.id.et_userNameLog);
        mEtUserPassword = findViewById(R.id.et_userPasswordLog);
        mBtLog = findViewById(R.id.bt_log);
        mBtToResign = findViewById(R.id.bt_toResign);
    }

//    检测用户输入以及登录、注册
    private void initEvent() {
        mBtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logEvent();
            }
        });

        mBtToResign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toResignEvent();
            }
        });
    }

//    登录跳转到主页
    private void logEvent() {
        String userName = mEtUserName.getText() == null ? "" : mEtUserName.getText().toString();
        String userPassword = mEtUserPassword.getText() == null ? "" : mEtUserPassword.getText().toString();
        SaveUserInformation saveUserInformation = new SaveUserInformation(this);
        Intent intent = new Intent(LogActivity.this, HomeActivity.class);
        if (Objects.equals(userName, saveUserInformation.getUserName()) && Objects.equals(userPassword, saveUserInformation.getUserPassword()) && (!userName.isEmpty() && !userPassword.isEmpty())) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

//    跳转到注册界面
    private void toResignEvent() {
        Intent intent = new Intent(LogActivity.this, ResignActivity.class);
        startActivity(intent);
    }
}
