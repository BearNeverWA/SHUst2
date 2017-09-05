package com.shu.shust2.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shu.shust2.R;
import com.shu.shust2.adapter.ClubAdapter;
import com.shu.shust2.model.Club;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Leo on 2017/9/3.
 */

public class ClubFragment extends Fragment {

    private List<Club> clubs = new ArrayList<>();
    private Club club;
    private ClubAdapter adapter = new ClubAdapter(clubs);
    private String[] types = {"学术科技", "体育健身", "公益实践", "文化艺术", "社会科学", "理论学习"};
    private Handler handler = new Handler();

    boolean isLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);

        //导航栏i
        Toolbar toolbar = view.findViewById(R.id.toolbar_club);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        //社团list
        RecyclerView recyclerView = view.findViewById(R.id.rv_club);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    if (!isLoading) {
                        isLoading = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });
        initData();

        return view;
    }

    private void initData() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            club = new Club(R.mipmap.ic_launcher, "测试" + i, types[random.nextInt(5)], random.nextInt(4) + 1, "测试介绍" + i);
            clubs.add(club);
        }
        adapter.notifyDataSetChanged();
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

}
