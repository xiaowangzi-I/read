package com.example.read.log;

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
import com.example.read.home.HomeActivity;
import com.example.read.save.SaveUserInformation;
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
    }

    private void initView() {
        mEtUserName = findViewById(R.id.et_userNameLog);
        mEtUserPassword = findViewById(R.id.et_userPasswordLog);
        mBtLog = findViewById(R.id.bt_log);
        mBtToResign = findViewById(R.id.bt_toResign);
    }

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

    private void logEvent() {
        String userName = mEtUserName.getText().toString();
        String userPassword = mEtUserPassword.getText().toString();
        SaveUserInformation saveUserInformation = new SaveUserInformation(this);
        if (Objects.equals(userName, saveUserInformation.getUserName()) && Objects.equals(userPassword, saveUserInformation.getUserPassword()) && !userName.isEmpty() && !userPassword.isEmpty()) {
            Intent intent = new Intent(LogActivity.this, HomeActivity.class);
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void toResignEvent() {
        Intent intent = new Intent(LogActivity.this, ResignActivity.class);
        startActivity(intent);
    }
}
