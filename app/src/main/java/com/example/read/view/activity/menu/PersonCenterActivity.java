/**
* description ： TODO:查看自己账号信息
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月13日，20:52
*/
package com.example.read.view.activity.menu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.read.utils.IntentUtils;
import com.example.read.utils.saveutils.SavePortrait;
import com.example.read.utils.saveutils.SaveUserInformation;
import com.example.read.utils.views.BackView;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.IOException;
import java.io.InputStream;

public class PersonCenterActivity extends AppCompatActivity {

    private BackView mBack;
    private Button mBtSetPortrait;
    private ImageView mIvPortrait;
    private TextView mTvUserName;
    private TextView mTvUserPassword;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    private RxPermissions rxPermissions;
    private Button mBtSetUserInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_person_center);
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
        mBack = findViewById(R.id.bv_personCenter);
        mBtSetPortrait = findViewById(R.id.bt_portraitPersonCenter);
        mIvPortrait = findViewById(R.id.iv_portraitPersonCenter);
        mTvUserName = findViewById(R.id.tv_accountUserName);
        mTvUserPassword = findViewById(R.id.tv_accountUserPassword);
        mBtSetUserInformation = findViewById(R.id.bt_accountChangeUserInformation);

        rxPermissions = new RxPermissions(this);
    }

    /**
     * 初始化事件监听器，设置返回按钮、设置头像按钮和修改用户信息按钮的点击事件。
     */
    private void initEvent() {
        SaveUserInformation saveUserInformation = new SaveUserInformation(this);
        mIvPortrait.setImageBitmap(SavePortrait.getImage(this)); // 设置当前头像
        mTvUserName.setText(saveUserInformation.getUserName()); // 设置用户名
        mTvUserPassword.setText(saveUserInformation.getUserPassword()); // 设置用户密码

        mBtSetPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(); // 请求权限并打开图库选择头像
            }
        });

        registerPickImageLauncher(); // 注册图片选择器的回调

        mBack.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 返回上一个页面
            }
        });

        mBtSetUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.intent(PersonCenterActivity.this, ChangeUserInformationActivity.class); // 跳转到修改用户信息页面
            }
        });
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
                            Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
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
}