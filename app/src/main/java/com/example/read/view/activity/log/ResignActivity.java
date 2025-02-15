/**
* description ： TODO:实现注册功能
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月25日，22:30
*/
package com.example.read.view.activity.log;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.read.R;
import com.example.read.utils.saveutils.SavePortrait;
import com.example.read.view.activity.home.HomeActivity;
import com.example.read.utils.saveutils.SaveUserInformation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.IOException;
import java.io.InputStream;

public class ResignActivity extends AppCompatActivity {

    private RxPermissions rxPermissions;
    private ActivityResultLauncher<Intent> pickImageLauncher;

    private Button mBtSetPortrait;
    private ImageView mIvPortrait;
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

    /**
     * 初始化视图组件，绑定 UI 元素。
     */
    private void initView() {
        mBtSetPortrait = findViewById(R.id.bt_portraitResign);
        mIvPortrait = findViewById(R.id.iv_portraitResign);
        mTilUserName = findViewById(R.id.til_userNameResign);
        mTilUserPassword = findViewById(R.id.til_userPasswordResign);
        mEtUserName = findViewById(R.id.et_userNameResign);
        mEtUserPassword = findViewById(R.id.et_userPasswordResign);
        mBtResign = findViewById(R.id.bt_resign);

        rxPermissions = new RxPermissions(this);
    }

    /**
     * 初始化事件监听器，设置用户名和密码输入监听、注册按钮和设置头像按钮的点击事件。
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

        mBtResign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mEtUserName.getText() == null ? "" : mEtUserName.getText().toString();
                String userPassword = mEtUserPassword.getText() == null ? "" : mEtUserPassword.getText().toString();
                if (userNameRight(userName) && userPasswordRight(userPassword)) {
                    resignEvent(); // 处理注册逻辑
                } else {
                    Toast.makeText(ResignActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtSetPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(); // 请求权限并打开图库选择头像
            }
        });

        registerPickImageLauncher(); // 注册图片选择器
    }

    /**
     * 处理注册逻辑，验证用户名和密码并保存用户信息。
     */
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
        finish();
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

    /**
     * 请求存储权限并打开图库选择头像。
     */
    @SuppressLint({"InlinedApi", "CheckResult"})
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
            openGallery(); // 已有权限，直接打开图库
        } else {
            rxPermissions.request(
                            Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)
                    .subscribe(granted -> {
                        if (granted) {
                            openGallery(); // 权限请求成功，打开图库
                        } else {
                            Toast.makeText(this, "请给予权限", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    /**
     * 注册图片选择器的回调。
     */
    private void registerPickImageLauncher() {
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if ((result.getResultCode() == RESULT_OK) && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            mIvPortrait.setImageURI(selectedImageUri); // 设置头像图片
                            try {
                                InputStream inputStream = this.getContentResolver().openInputStream(selectedImageUri);
                                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                SavePortrait.setImage(this, bitmap); // 保存头像
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
    }

    /**
     * 打开图库选择图片。
     */
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }
}