package com.example.read.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.read.R;
import com.example.read.utils.NetworkUtils;
import com.example.read.utils.jsonutils.JSONGetDailyNews;

import java.util.List;

public class DailyNewsAdapter extends RecyclerView.Adapter<DailyNewsAdapter.DailyNewsViewHolder> {

    private final List<JSONGetDailyNews.GetDailyNewsData.GetDailyNews> dailyNewsList;
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public DailyNewsAdapter(List<JSONGetDailyNews.GetDailyNewsData.GetDailyNews> jsonGetDailyNewsList) {
        this.dailyNewsList = jsonGetDailyNewsList;
    }

    @NonNull
    @Override
    public DailyNewsAdapter.DailyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_news_item, parent, false);
        return new DailyNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyNewsAdapter.DailyNewsViewHolder holder, int position) {
        JSONGetDailyNews.GetDailyNewsData.GetDailyNews dailyNewsEntity = dailyNewsList.get(position);
        if (dailyNewsList == null || position >= dailyNewsList.size()) {
            holder.mTvTitle.setText("No data");
            holder.mIvPicture.setImageResource(R.drawable.failedload);
        } else {

            holder.mTvTitle.setText(dailyNewsEntity.getTitle());
            List<String> images = dailyNewsEntity.getImages();
            if (images != null && !images.isEmpty()) {
                String imageUrl = images.get(0);
                if (imageUrl != null && !imageUrl.isEmpty()) {
                    NetworkUtils.imageRequest(imageUrl, holder.mIvPicture.getContext(), holder.mIvPicture);
                } else {
                    holder.mIvPicture.setImageResource(R.drawable.failedload);
                }
            } else {
                holder.mIvPicture.setImageResource(R.drawable.failedload);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dailyNewsList.size();
    }

    public static class DailyNewsViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        ImageView mIvPicture;

        public DailyNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_dailyNewsTitle);
            mIvPicture = itemView.findViewById(R.id.iv_dailyNewsPicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }


}
