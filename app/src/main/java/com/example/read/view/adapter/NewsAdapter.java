package com.example.read.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.read.R;
import com.example.read.utils.jsonutils.JSONGetNews;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<JSONGetNews.GetNewsResult.GetNews> newsList;

    public NewsAdapter (List<JSONGetNews.GetNewsResult.GetNews> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        JSONGetNews.GetNewsResult.GetNews newsEntity = newsList.get(position);
        holder.mTvTitle.setText(newsEntity.getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTitle;

        public NewsViewHolder (@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_newsTitle);
        }
    }


}
