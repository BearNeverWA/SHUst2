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
import android.widget.Toast;

import com.shu.shust2.R;
import com.shu.shust2.adapter.ActivityAdapter;
import com.shu.shust2.model.Activity;
import com.shu.shust2.model.ActivityListBean;
import com.shu.shust2.util.JsonUtil;
import com.shu.shust2.util.OkConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Leo on 2017/9/3.
 */

public class ActivityFragment extends Fragment {

    private List<Activity> activities = new ArrayList<>();
    private Activity activity;
    private ActivityAdapter adapter = new ActivityAdapter(activities);
    private Handler handler = new Handler();
    private Handler dloadHandler;
    private RecyclerView recyclerView;
    private ActivityListBean activityListBean;
    private ActivityListBean.ResultsBean resultsBean;
    private List<ActivityListBean.ResultsBean.ActivityBean> activityBeanList;
    private Random random;
    private LinearLayoutManager manager;
    private Button btnSearch;
    private EditText etSearch;
//    private ActivitySearch ActivitySearch;
//    private ActivitySearch.ResultsBean resultsSearch;
//    private List<ActivitySearch.ResultsBean.AssociationBean> association_search;

    boolean isLoading;
    private String ActivityData;
    private String searchData;

    private static final String TAG = "ActivityFragment";
    private static final int REQUEST_SUCCESS = 1;
    private static final int REQUEST_FAIL = 0;
    private static final int SEARCH_SUCCESS = 2;

    private static String ACTIVITY_URL_TMP = "http://api.dev.shust.cn/activity/list";
    private static String SEARCH_URL_TMP = "http://api.dev.shust.cn/activity/result";
    private int page;
    private int pageNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        //导航栏
        Toolbar toolbar = view.findViewById(R.id.toolbar_activity);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");

        //活动list
        recyclerView = view.findViewById(R.id.rv_activity);
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

        //搜索
//        btnSearch = view.findViewById(R.id.btn_Activity_search);
//        etSearch = view.findViewById(R.id.et_Activity_search);
//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Message msg = dloadHandler.obtainMessage();
//                        OkConnect connect = new OkConnect();
//                        try {
//                            searchData = connect.run(urlOp(SEARCH_URL_TMP, etSearch.getText().toString(), 0, 0, 1));
//                            if (!searchData.equals("error"))
//                                msg.what = SEARCH_SUCCESS;
//                            else
//                                msg.what = REQUEST_FAIL;
//                            dloadHandler.sendMessage(msg);
//                        } catch (IOException e) {
//                            msg.what = REQUEST_FAIL;
//                        }
//                    }
//                }).start();
//            }
//        });

        initData();

        return view;
    }

    private void initData() {
        page = (adapter.getItemCount() - 1) / 13;
        if (page == 0)
            pageNum = 1;
        else
            pageNum = page + 1;
        random = new Random();

        //activity列表数据初始化
        dloadHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        activityListBean = JsonUtil.parseJson(ActivityData, ActivityListBean.class);
                        if (activityListBean.getErrorCode() != 0)
                            Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                        else {
                            if (!activityListBean.getErrorStr().equals("ok"))
                                Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                            else {
                                resultsBean = activityListBean.getResults();
                                activityBeanList = resultsBean.getActivity();
                                for (ActivityListBean.ResultsBean.ActivityBean activityBean : activityBeanList) {
                                    activity = new Activity(WelcomeFragment.path[random.nextInt(7)], activityBean.getName(),
                                            activityBean.getType(), activityBean.getStatus(), activityBean.getLocation(),
                                            activityBean.getId());
                                    activities.add(activity);
                                }
                            }
                        }
                        if (pageNum == 1) {
                            recyclerView.setAdapter(adapter);
                        } else
                            adapter.notifyDataSetChanged();
                        break;
//                    case SEARCH_SUCCESS:
//                        activities.clear();
//                        ActivitySearch = JsonUtil.parseJson(searchData, ActivitySearch.class);
//                        if (ActivitySearch.getErrorCode() != 0)
//                            Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
//                        else {
//                            if (!ActivitySearch.getErrorStr().equals("ok"))
//                                Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
//                            else {
//                                resultsSearch = ActivitySearch.getResults();
//                                association_search = resultsSearch.getAssociation();
//                                for (ActivitySearch.ResultsBean.AssociationBean as : association_search) {
//                                    activity = new Activity(WelcomeFragment.path[random.nextInt(7)], as.getNick_name(),
//                                            as.getType(), as.getStar(), as.getWord_introduction(),
//                                            as.getId());
//                                    activities.add(activity);
//                                }
//                            }
//                        }
//                        adapter = new ActivityAdapter(activities);
//                        recyclerView.setAdapter(adapter);
//                        break;
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
                    ActivityData = connect.run(urlOp(ACTIVITY_URL_TMP, "", 0, 0, pageNum));
                    if (!ActivityData.equals("error")) {
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

    private String urlOp(String tmp, String searchContent, int tp, int stat, int pg) {
        return tmp + "?association=" + searchContent + "&type=" + tp + "&status=" + stat + "&page=" + pg;
    }
}
