package com.shu.shust2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.shu.shust2.R;
import com.shu.shust2.activity.ClubDetailActivity;
import com.shu.shust2.activity.DetailActivity;
import com.shu.shust2.model.Activity;
import com.shu.shust2.model.Club;

import java.util.List;

/**
 * Created by Leo on 2017/9/4.
 */

public class ActivityAdapter extends Adapter<ViewHolder> {

    private Context mContext;
    private List activityList;
    private Activity activity;

    private final static int TYPE_ITEM = 0;
    private final static int TYPE_FOOT = 1;


    public ActivityAdapter(List activitys) {
        activityList = activitys;
    }

    @Override
    public int getItemCount() {
        return activityList.size() == 0 ? 0 : activityList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount())
            return TYPE_FOOT;
        else
            return TYPE_ITEM;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_activity, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            activity = (Activity) activityList.get(position);
            Glide.with(mContext).load(activity.getActivityLogo()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(((ItemViewHolder) holder).activityLogo);
            ((ItemViewHolder) holder).activityName.setText(activity.getActivityName());
            ((ItemViewHolder) holder).activityType.setText(activity.getActivityType());
            switch (activity.getActivityStatus()) {
                case "已修改":
                    ((ItemViewHolder) holder).activityStatus.setBackgroundResource(R.drawable.blue_border);
                    ((ItemViewHolder) holder).activityStatus.setTextColor(mContext.getResources().getColor(R.color.soft_blue));
                    break;
                case "未开始":
                    ((ItemViewHolder) holder).activityStatus.setBackgroundResource(R.drawable.grey_border);
                    ((ItemViewHolder) holder).activityStatus.setTextColor(mContext.getResources().getColor(R.color.new_grey));
                    break;
                case "进行中":
                    ((ItemViewHolder) holder).activityStatus.setBackgroundResource(R.drawable.green_border);
                    ((ItemViewHolder) holder).activityStatus.setTextColor(mContext.getResources().getColor(R.color.soft_green));
                    break;
                case "审核中":
                    ((ItemViewHolder) holder).activityStatus.setBackgroundResource(R.drawable.red_border);
                    ((ItemViewHolder) holder).activityStatus.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
            }
            ((ItemViewHolder) holder).activityStatus.setText(activity.getActivityStatus());
            ((ItemViewHolder) holder).activityLocation.setText(activity.getActivityLocation());
            ((ItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getAdapterPosition();
                    activity = (Activity) activityList.get(pos);
                    Intent intent = new Intent(mContext, DetailActivity.class);
                    intent.putExtra("name", activity.getActivityName());
                    intent.putExtra("logo", activity.getActivityLogo());
                    intent.putExtra("id", activity.getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    static class ItemViewHolder extends ViewHolder {
        ImageView activityLogo;
        TextView activityName;
        TextView activityType;
        TextView activityStatus;
        TextView activityLocation;

        public ItemViewHolder(View itemView) {
            super(itemView);
            activityLogo = itemView.findViewById(R.id.iv_activity_logo);
            activityName = itemView.findViewById(R.id.tv_activity_name);
            activityType = itemView.findViewById(R.id.tv_activity_type);
            activityStatus = itemView.findViewById(R.id.tv_activity_status);
            activityLocation = itemView.findViewById(R.id.tv_activity_location);
        }
    }

    static class FootViewHolder extends ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}