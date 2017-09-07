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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.shu.shust2.R;
import com.shu.shust2.adapter.ClubAdapter;
import com.shu.shust2.model.Club;
import com.shu.shust2.model.ClubBean;
import com.shu.shust2.model.ClubSearch;
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
    private Handler handler = new Handler();
    private Handler dloadHandler;
    private RecyclerView recyclerView;
    private ClubBean clubBean;
    private ClubBean.ResultsBean resultsBean;
    private List<ClubBean.ResultsBean.AssociationBean> associations;
    private Random random;
    private LinearLayoutManager manager;
    private Button btnSearch;
    private RadioGroup rg;
    private EditText etSearch;
    private ClubSearch clubSearch;
    private ClubSearch.ResultsBean resultsSearch;
    private List<ClubSearch.ResultsBean.AssociationBean> association_search;

    boolean isLoading;
    private String clubData;
    //    private String CLUB_URL;
    private String searchData;

    private static final String TAG = "ClubFragment";
    private static final int REQUEST_SUCCESS = 1;
    private static final int REQUEST_FAIL = 0;
    private static final int SEARCH_SUCCESS = 2;

    private static String CLUB_URL_TMP = "http://api.dev.shust.cn/association/list";
    private static String SEARCH_URL_TMP = "http://api.dev.shust.cn/association/result";
    private int page;
    private int pageNum;
    private int checkedId;

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
        manager = new LinearLayoutManager(getActivity());
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

        //单选框
        rg = view.findViewById(R.id.rg_club);

        //搜索
        btnSearch = view.findViewById(R.id.btn_club_search);
        etSearch = view.findViewById(R.id.et_club_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rg.getCheckedRadioButtonId()) {
                    case R.id.rb_all:
                        checkedId = 0;
                        break;
                    case R.id.rb_five:
                        checkedId = 5;
                        break;
                    case R.id.rb_four:
                        checkedId = 4;
                        break;
                    case R.id.rb_three:
                        checkedId = 3;
                        break;
                    case R.id.rb_two:
                        checkedId = 2;
                        break;
                    case R.id.rb_one:
                        checkedId = 1;
                        break;
                }
                page = (adapter.getItemCount() - 1) / 13;
                if (page == 0) {
                    pageNum = 1;
                } else
                    pageNum = page + 1;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = dloadHandler.obtainMessage();
                        OkConnect connect = new OkConnect();
                        try {
                            searchData = connect.run(urlOp(SEARCH_URL_TMP, etSearch.getText().toString(), 0, checkedId, pageNum));
                            if (!searchData.equals("error"))
                                msg.what = SEARCH_SUCCESS;
                            else
                                msg.what = REQUEST_FAIL;
                            dloadHandler.sendMessage(msg);
                        } catch (IOException e) {
                            msg.what = REQUEST_FAIL;
                        }
                    }
                }).start();
            }
        });

        initData();

        return view;
    }

    private void initData() {
        page = (adapter.getItemCount() - 1) / 13;
        if (page == 0) {
            pageNum = 1;
        } else
            pageNum = page + 1;
        random = new Random();
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
                    case SEARCH_SUCCESS:
                        clubs.clear();
                        clubSearch = JsonUtil.parseJson(searchData, ClubSearch.class);
                        if (clubSearch.getErrorCode() != 0)
                            Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                        else {
                            if (!clubSearch.getErrorStr().equals("ok"))
                                Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                            else {
                                resultsSearch = clubSearch.getResults();
                                association_search = resultsSearch.getAssociation();
                                for (ClubSearch.ResultsBean.AssociationBean as : association_search) {
                                    club = new Club(WelcomeFragment.path[random.nextInt(7)], as.getNick_name(),
                                            as.getType(), as.getStar(), as.getWord_introduction(),
                                            as.getId());
                                    clubs.add(club);
                                }
                            }
                        }
                        adapter = new ClubAdapter(clubs);
                        recyclerView.setAdapter(adapter);
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
                    Log.d(TAG, "run: " + urlOp(CLUB_URL_TMP, "", 0, 0, pageNum));
                    clubData = connect.run(urlOp(CLUB_URL_TMP, "", 0, 0, pageNum));
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

    private String urlOp(String tmp, String searchContent, int tp, int st, int pg) {
        return tmp + "?association=" + searchContent + "&type=" + tp + "&star=" + st + "&page=" + pg;
    }
}
