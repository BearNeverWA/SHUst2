package com.shu.shust2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shu.shust2.GlideImageLoader;
import com.shu.shust2.R;
import com.shu.shust2.activity.DetailActivity;
import com.shu.shust2.adapter.HotAdapter;
import com.shu.shust2.adapter.RecommendAdapter;
import com.shu.shust2.model.Hot;
import com.shu.shust2.model.HotBean;
import com.shu.shust2.model.Recommend;
import com.shu.shust2.util.JsonUtil;
import com.shu.shust2.util.OkConnect;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Leo on 2017/9/3.
 */

public class WelcomeFragment extends Fragment implements OnBannerListener {

    private List<String> images;
    private List<String> titles;
    private List<Recommend> recommends;
    private List<Hot> hots;
    private Recommend recommend;
    private RecommendAdapter adapter;
    private Hot hot;
    private HotBean hotBean;
    private HotBean.ResultsBean resultsBean;
    private List<HotBean.ResultsBean.AssociationBean> associations;
    private HotAdapter adapter1;
    private String hotData;     //接收到的服务器发的热门社团的数据
    private Handler requestHandler;

    private static final String HOT_CLUB_URL = "http://api.dev.shust.cn/index?page=1";
    private static final int REQUEST_SUCCESS = 1;
    private static final int REQUEST_FAIL = 0;

    private static final String TAG = "WelcomeFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        initData();

        //导航栏
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");


        //轮播图
        Banner banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setDelayTime(2000);
        banner.setOnBannerListener(this);
        banner.start();

        //推荐活动
        RecyclerView recyclerView = view.findViewById(R.id.rv_recommend_activity);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        adapter = new RecommendAdapter(recommends);
        recyclerView.setAdapter(adapter);

        //热门社团
        RecyclerView recyclerView1 = view.findViewById(R.id.rv_hot_club);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(manager1);
        adapter1 = new HotAdapter(hots);
        recyclerView1.setAdapter(adapter1);

        return view;
    }

    private void initData() {
        //Banner图片的数据初始化
        images = new ArrayList<>();
        images.add("http://i.imgur.com/DvpvklR.png");
        images.add("http://pic138.nipic.com/file/20170816/22554547_123516885000_2.jpg");
        images.add("http://pic138.nipic.com/file/20170816/22554547_123534011000_2.jpg");

        //Banner标题的数据初始化
        titles = new ArrayList<>();
        titles.add("aaaaaaaaa");
        titles.add("bbbbbbbbb");
        titles.add("ccccccccc");

        //推荐活动和热门社团的数据初始化
        recommends = new ArrayList<>();
        hots = new ArrayList<>();
        String path = "http://pic130.nipic.com/file/20170521/3588216_163420961230_2.jpg";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            recommend = new Recommend(R.mipmap.ic_launcher, "测试" + i);
            recommends.add(recommend);
            hot = new Hot(path, "测试" + i, random.nextInt(4) + 1);
            hots.add(hot);
        }

        //热门社团数据初始化
//        requestHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case REQUEST_SUCCESS:
////                        Log.d(TAG, "handleMessage: " + hotData);
//                        hotBean = JsonUtil.parseJson(hotData, HotBean.class);
//                        if (hotBean.getErrorCode() != 0)
//                            Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
//                        else {
//                            if (!hotBean.getErrorStr().equals("ok"))
//                                Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
//                            else {
//                                Toast.makeText(getActivity(), "连接成功", Toast.LENGTH_SHORT).show();
//                                resultsBean = hotBean.getResults();
//                                associations = resultsBean.getAssociation();
//                                for (HotBean.ResultsBean.AssociationBean associationBean : associations) {
//                                    Log.d(TAG, "handleMessage: " + associationBean.getLogo() + associationBean.getNick_name() + associationBean.getStar());
//                                    hot = new Hot(associationBean.getLogo(), associationBean.getNick_name(), associationBean.getStar());
//                                    hots.add(hot);
//                                }
//                            }
//                        }
//                        break;
//                    case REQUEST_FAIL:
//                        break;
//                    default:
//                        break;
//                }
//            }
//        };
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Message msg = requestHandler.obtainMessage();
//                OkConnect connect = new OkConnect();
//                try {
//                    hotData = connect.run(HOT_CLUB_URL);
//                    if (!hotData.equals("error"))
//                        msg.what = REQUEST_SUCCESS;
//                    else
//                        msg.what = REQUEST_FAIL;
//                    requestHandler.sendMessage(msg);
//                } catch (IOException e) {
//                    msg.what = REQUEST_FAIL;
//                }
//            }
//        }).start();


    }

    /**
     * banner点击事件处理
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        String title = titles.get(position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("title", title);
        startActivity(intent);
    }
}
