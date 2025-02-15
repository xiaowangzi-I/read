/**
* description ： TODO:知乎日报RecycleView的适配器
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月3日，21:37
*/
package com.example.read.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    /**
     * 定义点击事件的回调接口。
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * 设置点击事件的监听器。
     *
     * @param listener 点击事件的监听器。
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 构造函数，初始化适配器的数据列表。
     *
     * @param jsonGetDailyNewsList 知乎日报的数据列表。
     */
    public DailyNewsAdapter(List<JSONGetDailyNews.GetDailyNewsData.GetDailyNews> jsonGetDailyNewsList) {
        this.dailyNewsList = jsonGetDailyNewsList;
    }

    /**
     * 创建新的 ViewHolder。
     *
     * @param parent 父视图。
     * @param viewType 视图类型。
     * @return 新的 ViewHolder。
     */
    @NonNull
    @Override
    public DailyNewsAdapter.DailyNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_news_item, parent, false);
        return new DailyNewsViewHolder(view);
    }

    /**
     * 绑定数据到 ViewHolder。
     *
     * @param holder ViewHolder。
     * @param position 当前项的位置。
     */
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

    /**
     * 获取数据项的数量。
     *
     * @return 数据项的数量。
     */
    @Override
    public int getItemCount() {
        return dailyNewsList.size();
    }

    /**
     * ViewHolder，用于绑定数据到视图。
     */
    public static class DailyNewsViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;
        ImageView mIvPicture;

        /**
         * 构造函数，初始化视图组件。
         *
         * @param itemView 当前项的视图。
         */
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