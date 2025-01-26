package com.example.read.log;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.material.textfield.TextInputLayout;

public class ResignActivity extends AppCompatActivity {

    private TextInputLayout mTilUserName;
    private TextInputLayout mTilUserPassword;
    private TextInputEditText mEtUserName;
    private TextInputEditText mEtUserPassword;
    private Button mBtResign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resign);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    private void initView() {
        mTilUserName = findViewById(R.id.til_userNameResign);
        mTilUserPassword = findViewById(R.id.til_userPasswordResign);
        mEtUserName = findViewById(R.id.et_userNameResign);
        mEtUserPassword = findViewById(R.id.et_userPasswordResign);
        mBtResign = findViewById(R.id.bt_resign);
    }

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
                String input = s == null ? "" : s.toString();
                if (!userNameRight(input)) {
                    mTilUserName.setError("用户名应在0-20之间");
                } else {
                    mTilUserName.setError(null);
                }
            }
        });

        mEtUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s == null ? "" : s.toString();
                if (!userPasswordRight(input)) {
                    mTilUserPassword.setError("密码应在0-20之间且包含字母和数字");
                } else {mTilUserPassword.setError(null);}
            }
        });

        mBtResign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mEtUserName.getText() == null ? "" : mEtUserName.getText().toString();
                String userPassword = mEtUserPassword.getText() == null ? "" : mEtUserPassword.getText().toString();
                if (userNameRight(userName) && userPasswordRight(userPassword)) {
                    resignEvent();
                } else {
                    Toast.makeText(ResignActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resignEvent() {
        String userName = mEtUserName.getText() == null ? "" : mEtUserName.getText().toString();
        String userPassword = mEtUserPassword.getText() == null ? "" : mEtUserPassword.getText().toString();
        SaveUserInformation saveUserInformation = new SaveUserInformation(this);
        String saveUserName = saveUserInformation.getUserName();
        String saveUserPassword = saveUserInformation.getUserPassword();

        if ((saveUserName != null && saveUserPassword != null) && (saveUserName.equals(userName) && saveUserPassword.equals(userPassword))) {
            Toast.makeText(this, "已有账号，登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            saveUserInformation.setUserName(userName);
            saveUserInformation.setPassword(userPassword);
        }
        Intent intent = new Intent(ResignActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private boolean userNameRight(String input) {
        return !input.isEmpty() && input.length() <= 20;
    }

    private boolean userPasswordRight(String input) {
        boolean rightCount = !input.isEmpty() && input.length() <= 20;
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }
        boolean rightInput = hasLetter && hasDigit;
        return rightCount && rightInput;
    }
}