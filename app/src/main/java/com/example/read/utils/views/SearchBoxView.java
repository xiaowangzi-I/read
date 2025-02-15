/**
* description ：它继承自 ConstraintLayout，并提供一个搜索输入框（EditText）和一个搜索按钮（Button）。该视图支持设置点击事件监听器，用于处理搜索框点击和搜索按钮点击事件。 TODO:SearchBoxView 是一个自定义的搜索框视图。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月1日，12:46
*/
package com.example.read.utils.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.read.R;

public class SearchBoxView extends ConstraintLayout {

    /**
     * 搜索输入框的引用。
     */
    private EditText mSearchEditText;

    /**
     * 搜索按钮的引用。
     */
    private Button mSearchButton;

    /**
     * 构造函数，初始化自定义视图。
     *
     * @param context      应用程序上下文。
     * @param attributeSet XML 属性集。
     */
    public SearchBoxView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);  // 初始化视图
        initEvent();        // 初始化事件
    }

    /**
     * 初始化视图布局。
     * 使用 LayoutInflater 将 R.layout.search_box 布局文件加载到当前视图中。
     *
     * @param context 应用程序上下文。
     */
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.search_box, this, true);
        mSearchEditText = findViewById(R.id.et_searchBox);  // 获取搜索输入框的引用
        mSearchButton = findViewById(R.id.bt_search);       // 获取搜索按钮的引用
    }

    /**
     * 初始化事件监听器。
     * 设置搜索输入框和搜索按钮的点击事件监听器。
     */
    private void initEvent() {
        // 搜索输入框点击事件
        mSearchEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchBoxClicked();  // 调用搜索框点击事件处理方法
            }
        });

        // 搜索按钮点击事件
        mSearchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchEditText.getText().toString().trim();  // 获取搜索内容
                if (!searchText.isEmpty()) {
                    onSearchClicked(searchText);  // 调用搜索事件处理方法
                } else {
                    onSearchBoxClicked();  // 如果搜索内容为空，调用搜索框点击事件处理方法
                }
            }
        });
    }

    /**
     * 定义搜索框点击事件的回调接口。
     */
    public interface OnSearchBoxClickListener {
        /**
         * 搜索框点击事件的回调方法。
         */
        void onSearchBoxClick();
    }

    /**
     * 定义搜索事件的回调接口。
     */
    public interface OnSearchClickListener {
        /**
         * 搜索事件的回调方法。
         *
         * @param searchText 用户输入的搜索内容。
         */
        void onSearch(String searchText);
    }

    /**
     * 搜索框点击事件的监听器。
     */
    private OnSearchBoxClickListener searchBoxClickListener;

    /**
     * 搜索事件的监听器。
     */
    private OnSearchClickListener searchClickListener;

    /**
     * 设置搜索框点击事件的监听器。
     *
     * @param onSearchBoxClickListener 搜索框点击事件的监听器。
     */
    public void setOnSearchBoxClickListener(OnSearchBoxClickListener onSearchBoxClickListener) {
        this.searchBoxClickListener = onSearchBoxClickListener;
    }

    /**
     * 设置搜索事件的监听器。
     *
     * @param onSearchClickListener 搜索事件的监听器。
     */
    public void setOnSearchClickListener(OnSearchClickListener onSearchClickListener) {
        this.searchClickListener = onSearchClickListener;
    }

    /**
     * 处理搜索框点击事件。
     */
    private void onSearchBoxClicked() {
        if (searchBoxClickListener != null) {
            searchBoxClickListener.onSearchBoxClick();  // 调用监听器的回调方法
        }
    }

    /**
     * 处理搜索事件。
     *
     * @param searchText 用户输入的搜索内容。
     */
    private void onSearchClicked(String searchText) {
        if (searchClickListener != null) {
            searchClickListener.onSearch(searchText);  // 调用监听器的回调方法
        }
    }

    /**
     * 设置搜索输入框的提示文本。
     *
     * @param text 要设置的提示文本。
     */
    public void setEtHint(String text) {
        mSearchEditText.setHint(text);
    }
}