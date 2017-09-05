package com.shu.shust2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shu.shust2.R;
import com.shu.shust2.activity.MainActivity;
import com.shu.shust2.model.Club;

import java.util.List;

/**
 * Created by Leo on 2017/9/4.
 */

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ViewHolder> {

    private Context mContext;
    private List<Club> clubList;

    private final static int TYPE_ITEM=0;
    private final static int TYPE_FOOT=1;
    

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView clubLogo;
        TextView clubName;
        TextView clubType;
        TextView clubStar;
        TextView clubIntro;

        public ViewHolder(View itemView) {
            super(itemView);
            clubLogo = itemView.findViewById(R.id.iv_club_logo);
            clubName = itemView.findViewById(R.id.tv_club_name);
            clubType = itemView.findViewById(R.id.tv_club_type);
            clubStar = itemView.findViewById(R.id.tv_club_star);
            clubIntro = itemView.findViewById(R.id.tv_club_intro);
        }
    }

    public ClubAdapter(List<Club> clubs) {
        clubList = clubs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null)
            mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_club, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Club club = clubList.get(position);
        Glide.with(mContext).load(club.getClubLogo()).into(holder.clubLogo);
        holder.clubName.setText(club.getClubName());

        switch (club.getClubType()) {
            case "学术科技":
                holder.clubType.setBackgroundResource(R.drawable.blue_border);
                holder.clubType.setTextColor(mContext.getResources().getColor(R.color.soft_blue));
                break;
            case "体育健身":
                holder.clubType.setBackgroundResource(R.drawable.green_border);
                holder.clubType.setTextColor(mContext.getResources().getColor(R.color.soft_green));
                break;
            case "公益实践":
                holder.clubType.setBackgroundResource(R.drawable.orange_border);
                holder.clubType.setTextColor(mContext.getResources().getColor(R.color.orange));
                break;
            case "文化艺术":
                holder.clubType.setBackgroundResource(R.drawable.red_border);
                holder.clubType.setTextColor(mContext.getResources().getColor(R.color.red));
                break;
            case "社会科学":
                holder.clubType.setBackgroundResource(R.drawable.pink_round);
                holder.clubType.setTextColor(mContext.getResources().getColor(R.color.pink));
                break;
            case "理论学习":
                holder.clubType.setBackgroundResource(R.drawable.purple_border);
                holder.clubType.setTextColor(mContext.getResources().getColor(R.color.purple));
                break;
            default:
                break;
        }
        holder.clubType.setText(club.getClubType());

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
        holder.clubStar.setText(star);
        holder.clubIntro.setText(club.getClubIntro());
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }
}