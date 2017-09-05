package com.shu.shust2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shu.shust2.R;
import com.shu.shust2.activity.DetailActivity;
import com.shu.shust2.model.Recommend;

import java.util.List;

/**
 * Created by Leo on 2017/9/4.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private Context mContext;
    private List<Recommend> mRecommendList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_recommend);
            textView = itemView.findViewById(R.id.tv_recommend);
        }
    }

    public RecommendAdapter(List<Recommend> recommendList) {
        mRecommendList = recommendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recommend_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recommend recommend = mRecommendList.get(position);
        holder.textView.setText(recommend.getName());
        Glide.with(mContext).load(recommend.getImageId()).into(holder.imageView);

        //注册点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=recommend.getName();
                Intent intent=new Intent(mContext, DetailActivity.class);
                intent.putExtra("title",title);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecommendList.size();
    }
}
