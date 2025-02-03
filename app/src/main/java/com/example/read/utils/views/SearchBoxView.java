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

    private EditText mSearchEditText;
    private Button mSearchButton;

    public SearchBoxView (Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }
//初始化
    private void init (Context context) {
        LayoutInflater.from(context).inflate(R.layout.search_box, this, true);
        mSearchEditText = findViewById(R.id.et_searchBox);
        mSearchButton = findViewById(R.id.bt_search);

        mSearchEditText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchBoxClicked();
            }
        });

        mSearchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = mSearchEditText.getText().toString().trim();
                if (!searchText.isEmpty()) {
                    onSearchClicked(searchText);
                } else {
                    onSearchBoxClicked();
                }
            }
        });
    }

    public interface OnSearchBoxClickListener {
        void onSearchBoxClick();
    }

    public interface OnSearchClickListener {
        void onSearch(String searchText);
    }

    private OnSearchBoxClickListener searchBoxClickListener;
    private OnSearchClickListener searchClickListener;

    public void setOnSearchBoxClickListener(OnSearchBoxClickListener onSearchBoxClickListener) {
        this.searchBoxClickListener = onSearchBoxClickListener;
    }

    public void setOnSearchClickListener(OnSearchClickListener onSearchClickListener) {
        this.searchClickListener = onSearchClickListener;
    }

    private void onSearchBoxClicked () {
        if (searchBoxClickListener != null) {
            searchBoxClickListener.onSearchBoxClick();
        }
    }

    private void onSearchClicked (String searchText) {
        if (searchClickListener != null) {
            searchClickListener.onSearch(searchText);
        }
    }
}
