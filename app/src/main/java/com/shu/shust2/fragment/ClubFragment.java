package com.shu.shust2.fragment;

import android.os.Bundle;
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

    private List<Club> clubs;
    private Club club;
    private ClubAdapter adapter;
    private String[] types = {"学术科技", "体育健身", "公益实践", "文化艺术", "社会科学", "理论学习"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);
        initData();

        //导航栏
        Toolbar toolbar = view.findViewById(R.id.toolbar_club);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        //社团list
        RecyclerView recyclerView = view.findViewById(R.id.rv_club);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new ClubAdapter(clubs);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initData() {
        clubs = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            club = new Club(R.mipmap.ic_launcher, "测试" + i, types[random.nextInt(5)], random.nextInt(4) + 1, "测试介绍" + i);
            clubs.add(club);
        }
    }

}
