/**
* description ： TODO:更改用户名和密码
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月15日，13:59
*/
package com.example.read.view.activity.menu;

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
import com.example.read.utils.saveutils.SaveUserInformation;
import com.example.read.utils.views.BackView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChangeUserInformationActivity extends AppCompatActivity {

    private BackView mBvChange;
    private TextInputLayout mTilUserName;
    private TextInputLayout mTilUserPassword;
    private TextInputEditText mEtUserName;
    private TextInputEditText mEtUserPassword;
    private Button mBtChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_user_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        initEvent();
    }

    /**
     * 初始化视图组件，绑定 UI 元素。
     */
    private void initView() {
        mBvChange = findViewById(R.id.bv_changeInformation);
        mTilUserName = findViewById(R.id.til_userNameChange);
        mTilUserPassword = findViewById(R.id.til_userPasswordChange);
        mEtUserName = findViewById(R.id.et_userNameChange);
        mEtUserPassword = findViewById(R.id.et_userPasswordChange);
        mBtChange = findViewById(R.id.bt_change);
    }

    /**
     * 初始化事件监听器，设置返回按钮、用户名和密码输入监听以及修改按钮的点击事件。
     */
    private void initEvent() {
        mBvChange.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 返回上一个页面
            }
        });

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
                } else {
                    mTilUserPassword.setError(null);
                }
            }
        });

        mBtChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mEtUserName.getText() == null ? "" : mEtUserName.getText().toString();
                String userPassword = mEtUserPassword.getText() == null ? "" : mEtUserPassword.getText().toString();
                if (userNameRight(userName) && userPasswordRight(userPassword)) {
                    changeEvent(); // 修改用户信息
                } else {
                    Toast.makeText(ChangeUserInformationActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 修改用户信息，保存新的用户名和密码。
     */
    private void changeEvent() {
        String userName = mEtUserName.getText() == null ? "" : mEtUserName.getText().toString();
        String userPassword = mEtUserPassword.getText() == null ? "" : mEtUserPassword.getText().toString();
        SaveUserInformation saveUserInformation = new SaveUserInformation(this);

        if (userName != null || userPassword != null) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            saveUserInformation.setUserName(userName);
            saveUserInformation.setPassword(userPassword);
        }
    }

    /**
     * 验证用户名是否符合要求（长度不超过20）。
     */
    private boolean userNameRight(String input) {
        return !input.isEmpty() && input.length() <= 20;
    }

    /**
     * 验证密码是否符合要求（长度不超过20，且包含字母和数字）。
     */
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