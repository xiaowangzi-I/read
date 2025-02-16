/**
* description ： TODO:实现登录功能
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月25日，22:29
*/
package com.example.read.view.activity.log;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.utils.saveutils.SavePortrait;
import com.example.read.view.activity.home.HomeActivity;
import com.example.read.utils.saveutils.SaveIsLogged;
import com.example.read.utils.saveutils.SaveUserInformation;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LogActivity extends AppCompatActivity {

    private ImageView mIvPortrait;
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

    /**
     * 初始化视图组件，绑定 UI 元素。
     */
    private void initView() {
        mIvPortrait = findViewById(R.id.iv_portraitLog);
        mEtUserName = findViewById(R.id.et_userNameLog);
        mEtUserPassword = findViewById(R.id.et_userPasswordLog);
        mBtLog = findViewById(R.id.bt_log);
        mBtToResign = findViewById(R.id.bt_toResign);
    }

    /**
     * 初始化事件监听器，设置用户名输入监听、登录按钮和注册按钮的点击事件。
     */
    private void initEvent() {
        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                setImage(input); // 根据输入动态设置头像
            }
        });

        mBtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logEvent(); // 处理登录逻辑
            }
        });

        mBtToResign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toResignEvent(); // 跳转到注册界面
            }
        });
    }

    /**
     * 处理登录逻辑，验证用户名和密码并跳转到主页。
     */
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

    /**
     * 跳转到注册界面。
     */
    private void toResignEvent() {
        Intent intent = new Intent(LogActivity.this, ResignActivity.class);
        startActivity(intent);
    }

    /**
     * 根据输入的用户名动态设置头像。
     */
    private void setImage(String input) {
        SaveUserInformation saveUserInformation = new SaveUserInformation(this);
        String userName = saveUserInformation.getUserName() == null ? "" : saveUserInformation.getUserName();
        if (input.equals(userName) && userName != "") {
            Bitmap bitmap = SavePortrait.getImage(LogActivity.this);
            if (bitmap != null) {
                mIvPortrait.setImageBitmap(bitmap);
            }
        }
    }
}