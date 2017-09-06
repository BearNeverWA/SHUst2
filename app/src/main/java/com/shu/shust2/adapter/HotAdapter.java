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
import com.shu.shust2.activity.ClubDetailActivity;
import com.shu.shust2.model.Hot;

import java.util.List;

/**
 * Created by Leo on 2017/9/4.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    private Context mContext;
    private List<Hot> mHotList;
    private Hot hot;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView clubName;
        TextView clubStar;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_hot_logo);
            clubName = itemView.findViewById(R.id.tv_hot_name);
            clubStar = itemView.findViewById(R.id.tv_hot_star);
        }
    }

    public HotAdapter(List<Hot> hotList) {
        mHotList = hotList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_club, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        hot = mHotList.get(position);
        holder.clubName.setText(hot.getClubName());
        String star;
        switch (hot.getClubStar()) {
            case 1:
                star = "一星级";
                break;
            case 2:
                star = "两星级";
                break;
            case 3:
                star = "三星级";
                break;
            case 4:
                star = "四星级";
                break;
            case 5:
                star = "五星级";
                break;
            default:
                star = "无";
                break;
        }
        holder.clubStar.setText(star);
        Glide.with(mContext).load(hot.getClubLogo()).fitCenter().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageView);

        //热门活动监听事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                hot = mHotList.get(pos);
                String name = hot.getClubName();
                String logoPath = hot.getClubLogo();
                Intent intent = new Intent(mContext, ClubDetailActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("logo", logoPath);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHotList.size();
    }
}
