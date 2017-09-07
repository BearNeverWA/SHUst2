package com.shu.shust2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.shu.shust2.R;
import com.shu.shust2.activity.ClubDetailActivity;
import com.shu.shust2.model.Club;

import java.util.List;

/**
 * Created by Leo on 2017/9/4.
 */

public class ClubAdapter extends Adapter<ViewHolder> {

    private Context mContext;
    private List clubList;
    private Club club;

    private final static int TYPE_ITEM = 0;
    private final static int TYPE_FOOT = 1;


    public ClubAdapter(List clubs) {
        clubList = clubs;
    }

    @Override
    public int getItemCount() {
        return clubList.size() == 0 ? 0 : clubList.size() + 1;
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_club, parent, false);
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
            club = (Club) clubList.get(position);
            Glide.with(mContext).load(club.getClubLogo()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(((ItemViewHolder) holder).clubLogo);
            ((ItemViewHolder) holder).clubName.setText(club.getClubName());
            switch (club.getClubType()) {
                case "学术科技":
                    ((ItemViewHolder) holder).clubType.setBackgroundResource(R.drawable.blue_border);
                    ((ItemViewHolder) holder).clubType.setTextColor(mContext.getResources().getColor(R.color.soft_blue));
                    break;
                case "体育健身":
                    ((ItemViewHolder) holder).clubType.setBackgroundResource(R.drawable.green_border);
                    ((ItemViewHolder) holder).clubType.setTextColor(mContext.getResources().getColor(R.color.soft_green));
                    break;
                case "公益实践":
                    ((ItemViewHolder) holder).clubType.setBackgroundResource(R.drawable.orange_border);
                    ((ItemViewHolder) holder).clubType.setTextColor(mContext.getResources().getColor(R.color.orange));
                    break;
                case "文化艺术":
                    ((ItemViewHolder) holder).clubType.setBackgroundResource(R.drawable.red_border);
                    ((ItemViewHolder) holder).clubType.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
                case "社会科学":
                    ((ItemViewHolder) holder).clubType.setBackgroundResource(R.drawable.pink_round);
                    ((ItemViewHolder) holder).clubType.setTextColor(mContext.getResources().getColor(R.color.pink));
                    break;
                case "理论学习":
                    ((ItemViewHolder) holder).clubType.setBackgroundResource(R.drawable.purple_border);
                    ((ItemViewHolder) holder).clubType.setTextColor(mContext.getResources().getColor(R.color.purple));
                    break;
                default:
                    break;
            }
            ((ItemViewHolder) holder).clubType.setText(club.getClubType());
            String star;
            switch (club.getClubStar()) {
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
            ((ItemViewHolder) holder).clubStar.setText(star);
            ((ItemViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getAdapterPosition();
                    club = (Club) clubList.get(pos);
                    Intent intent = new Intent(mContext, ClubDetailActivity.class);
                    intent.putExtra("name", club.getClubName());
                    intent.putExtra("logo", club.getClubLogo());
                    intent.putExtra("id", club.getId());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    static class ItemViewHolder extends ViewHolder {
        ImageView clubLogo;
        TextView clubName;
        TextView clubType;
        TextView clubStar;

        public ItemViewHolder(View itemView) {
            super(itemView);
            clubLogo = itemView.findViewById(R.id.iv_club_logo);
            clubName = itemView.findViewById(R.id.tv_club_name);
            clubType = itemView.findViewById(R.id.tv_club_type);
            clubStar = itemView.findViewById(R.id.tv_club_star);
        }
    }

    static class FootViewHolder extends ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}