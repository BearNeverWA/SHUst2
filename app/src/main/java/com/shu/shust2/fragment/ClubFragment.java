package com.shu.shust2.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shu.shust2.R;
import com.shu.shust2.adapter.ClubAdapter;
import com.shu.shust2.model.Club;
import com.shu.shust2.model.ClubBean;
import com.shu.shust2.util.JsonUtil;
import com.shu.shust2.util.OkConnect;

import java.io.IOException;
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
    private Handler dloadHandler;
    private RecyclerView recyclerView;
    private ClubBean clubBean;
    private ClubBean.ResultsBean resultsBean;
    private List<ClubBean.ResultsBean.AssociationBean> associations;
    private Random random;

    boolean isLoading;
    private String clubData;

    private static final String TAG = "ClubFragment";
    private static final int REQUEST_SUCCESS = 1;
    private static final int REQUEST_FAIL = 0;
    private static String CLUB_URL_TMP = "http://api.dev.shust.cn/association/list?page=";
    private int pageNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);

        //导航栏
        Toolbar toolbar = view.findViewById(R.id.toolbar_club);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        //社团list
        recyclerView = view.findViewById(R.id.rv_club);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
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
                        }, 2000);
                    }
                }
            }
        });
        initData();

        return view;
    }

    private void initData() {
        int page = (adapter.getItemCount() - 1) / 13;
        if (page == 0) {
            pageNum = 1;
        } else
            pageNum = page + 1;
        random = new Random();
//        for (int i = 0; i < 10; i++) {
//            club = new Club(WelcomeFragment.path[random.nextInt(7)], "测试" + i, types[random.nextInt(5)], random.nextInt(4) + 1, "测试介绍" + i);
//            clubs.add(club);
//        }

        //club列表数据初始化
        dloadHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        clubBean = JsonUtil.parseJson(clubData, ClubBean.class);
                        if (clubBean.getErrorCode() != 0)
                            Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                        else {
                            if (!clubBean.getErrorStr().equals("ok"))
                                Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                            else {
                                resultsBean = clubBean.getResults();
                                associations = resultsBean.getAssociation();
                                for (ClubBean.ResultsBean.AssociationBean associationBean : associations) {
                                    club = new Club(WelcomeFragment.path[random.nextInt(7)], associationBean.getNick_name(),
                                            associationBean.getType(), associationBean.getStar(), associationBean.getWord_introduction(),
                                            associationBean.getId());
                                    clubs.add(club);
                                }
                            }
                        }
                        if (pageNum == 1) {
                            recyclerView.setAdapter(adapter);
                        } else
                            adapter.notifyDataSetChanged();
                        break;
                    case REQUEST_FAIL:
                        break;
                    default:
                        break;
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = dloadHandler.obtainMessage();
                OkConnect connect = new OkConnect();
                try {
                    String CLUB_URL = CLUB_URL_TMP + pageNum;
                    clubData = connect.run(CLUB_URL);
                    if (!clubData.equals("error")) {
                        msg.what = REQUEST_SUCCESS;
                    } else
                        msg.what = REQUEST_FAIL;
                } catch (IOException e) {
                    msg.what = REQUEST_FAIL;
                }
                dloadHandler.sendMessage(msg);
            }
        }).start();
        adapter.notifyItemRemoved(adapter.getItemCount());
    }

}
