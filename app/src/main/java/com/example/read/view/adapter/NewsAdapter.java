/**
 * description ：RecyclerView的适配器类，用于展示新闻列表。该适配器接收一个新闻数据列表，并将其绑定到RecyclerView的条目视图中。 TODO:新闻适配器
 * author : 王子旻
 * email : 2461095759@qq.com
 * date : 2025年2月2日，18:55
 */
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

    // 新闻数据列表，存储从JSON解析出来的新闻对象
    private final List<JSONGetNews.GetNewsResult.GetNews> newsList;

    /**
     * 构造函数，初始化适配器并传入新闻数据列表。
     *
     * @param newsList 新闻数据列表
     */
    public NewsAdapter(List<JSONGetNews.GetNewsResult.GetNews> newsList) {
        this.newsList = newsList;
    }

    /**
     * 创建新的ViewHolder实例，用于管理RecyclerView条目的视图。
     * 该方法会在RecyclerView需要新条目时被调用。
     *
     * @param parent   父视图容器（RecyclerView）
     * @param viewType 条目视图的类型（本示例中未使用）
     * @return 新创建的ViewHolder实例
     */
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 使用LayoutInflater将布局文件（news_item.xml）转换为视图对象
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        // 返回一个新的ViewHolder实例，将视图对象传递给它
        return new NewsViewHolder(view);
    }

    /**
     * 将数据绑定到ViewHolder中的视图上。
     *
     * @param holder   ViewHolder实例，包含条目的视图
     * @param position 当前条目的位置
     */
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // 从新闻数据列表中获取当前条目的新闻对象
        JSONGetNews.GetNewsResult.GetNews newsEntity = newsList.get(position);
        // 将新闻标题绑定到ViewHolder中的TextView上
        holder.mTvTitle.setText(newsEntity.getTitle());
    }

    /**
     * 返回新闻数据列表的大小，告诉RecyclerView有多少条目需要显示。
     *
     * @return 新闻数据列表的大小
     */
    @Override
    public int getItemCount() {
        return newsList.size();
    }

    /**
     * 静态内部类，用于管理RecyclerView条目的视图。
     * ViewHolder的作用是缓存条目视图中的控件，避免在每次绑定数据时重复调用findViewById。
     */
    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        // 条目视图中的标题TextView
        TextView mTvTitle;

        /**
         * 构造函数，初始化ViewHolder并绑定视图。
         *
         * @param itemView 条目的根视图
         */
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            // 通过findViewById获取条目视图中的标题TextView
            mTvTitle = itemView.findViewById(R.id.tv_newsTitle);
        }
    }
}