/**
* description ：它继承自 ConstraintLayout，并提供一个返回按钮（mBackButton）。该视图可以设置点击事件监听器，用于处理返回按钮的点击事件。 TODO:BackView 是一个自定义的返回按钮视图。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月13日，22:39
*/
package com.example.read.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.read.R;

public class BackView extends ConstraintLayout {
    /**
     * 返回按钮的引用。
     */
    private Button mBackButton;

    /**
     * 返回按钮点击事件的监听器。
     */
    private OnClickListener mBackClickListen;

    /**
     * 构造函数，初始化自定义视图。
     *
     * @param context      应用程序上下文。
     * @param attributeSet XML 属性集。
     */
    public BackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);  // 初始化视图
        initEvent();        // 初始化事件
    }

    /**
     * 初始化视图布局。
     * 使用 LayoutInflater 将 R.layout.back 布局文件加载到当前视图中。
     *
     * @param context 应用程序上下文。
     */
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.back, this, true);
        mBackButton = findViewById(R.id.bt_back);  // 获取返回按钮的引用
    }

    /**
     * 初始化事件监听器。
     * 设置返回按钮的点击事件监听器，当按钮被点击时，触发 mBackClickListen 的 onClick 方法。
     */
    private void initEvent() {
        mBackButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBackClickListen != null) {
                    mBackClickListen.onClick(v);  // 调用外部设置的监听器
                }
            }
        });
    }

    /**
     * 设置返回按钮的点击事件监听器。
     *
     * @param listener 要设置的点击事件监听器。
     */
    public void setBackClickListener(OnClickListener listener) {
        mBackClickListen = listener;
    }
}