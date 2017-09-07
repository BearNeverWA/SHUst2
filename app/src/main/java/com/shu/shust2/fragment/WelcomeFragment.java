package com.shu.shust2.fragment;

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
import com.shu.shust2.model.IndexBean;
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
    private List<Recommend> recommends = new ArrayList<>();
    private List<Hot> hots = new ArrayList<>();
    private Recommend recommend;
    private RecommendAdapter adapter;
    private Hot hot;
    private HotAdapter adapter1;
    private IndexBean indexBean;
    private IndexBean.ResultsBean resultsBean;
    private List<IndexBean.ResultsBean.ActivityBean> activitys;
    private List<IndexBean.ResultsBean.AssociationBean> associations;
    private String indexData;     //接收到的服务器发的首页数据
    private Handler requestHandler;

    private static final String HOT_CLUB_URL = "http://api.dev.shust.cn/index?page=1";
    private static final int REQUEST_SUCCESS = 1;
    private static final int REQUEST_FAIL = 0;

    private static final String TAG = "WelcomeFragment";

    private Random random = new Random();
    public static String[] path =
            {"http://img4.imgtn.bdimg.com/it/u=3032878196,1070032624&fm=214&gp=0.jpg", "http://www.chedan5.com/upload/article/201701/16/101752587c2d50dac82q8cQIM.jpg",
                    "http://k2.jsqq.net/uploads/allimg/1703/7_170308145938_9.jpg", "http://k1.jsqq.net/uploads/allimg/1612/1JAW137-2.jpg",
                    "http://www.shzbbc.com/uploads/tu/ztmb/slt/bd17365762.jpg", "http://www.chedan5.com/upload/article/201701/16/101747587c2d4b4a303E2Ya55.jpg",
                    "http://img3.imgtn.bdimg.com/it/u=619037615,2380657108&fm=214&gp=0.jpg",
                    "http://k1.jsqq.net/uploads/allimg/160526/5-1605260619440-L.jpg"};

    private RecyclerView recyclerView;
    private RecyclerView recyclerView1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initView(View view) {
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
        recyclerView = view.findViewById(R.id.rv_recommend_activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);


        //热门社团
        recyclerView1 = view.findViewById(R.id.rv_hot_club);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setNestedScrollingEnabled(false);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(manager1);
    }

    private void initData() {
        //Banner图片的数据初始化
        images = new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504700222567&di=ccc3515392c2906e0073fd8b38f3f9a2&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F0b55b319ebc4b745d1a5e304c5fc1e178a8215ea.jpg");
        images.add("http://pic138.nipic.com/file/20170816/22554547_123516885000_2.jpg");
        images.add("http://pic138.nipic.com/file/20170816/22554547_123534011000_2.jpg");

        //Banner标题的数据初始化
        titles = new ArrayList<>();
        titles.add("社团助手v1.0上线啦!");
        titles.add("快来和我们一起玩耍~!");
        titles.add("期待你的加入~");

        //首页数据初始化
        requestHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        recommends.clear();
                        hots.clear();
                        indexBean = JsonUtil.parseJson(indexData, IndexBean.class);
                        if (indexBean.getErrorCode() != 0)
                            Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                        else {
                            if (!indexBean.getErrorStr().equals("ok"))
                                Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                            else {
                                resultsBean = indexBean.getResults();
                                activitys = resultsBean.getActivity();
                                associations = resultsBean.getAssociation();
                                for (IndexBean.ResultsBean.ActivityBean activityBean : activitys) {
                                    recommend = new Recommend(path[random.nextInt(7)], activityBean.getName(), activityBean.getLocation(), activityBean.getId());
                                    recommends.add(recommend);
                                }
                                for (IndexBean.ResultsBean.AssociationBean associationBean : associations) {
                                    hot = new Hot(path[random.nextInt(7)], associationBean.getNick_name(), associationBean.getStar(), associationBean.getId());
                                    hots.add(hot);
                                }
                            }
                        }
                        adapter = new RecommendAdapter(recommends);
                        recyclerView.setAdapter(adapter);
                        adapter1 = new HotAdapter(hots);
                        recyclerView1.setAdapter(adapter1);
                        break;
                    case REQUEST_FAIL:
                        Toast.makeText(getActivity(), "网络不给力呀", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = requestHandler.obtainMessage();
                OkConnect connect = new OkConnect();
                try {
                    indexData = connect.run(HOT_CLUB_URL);
                    if (!indexData.equals("error"))
                        msg.what = REQUEST_SUCCESS;
                    else
                        msg.what = REQUEST_FAIL;
                    requestHandler.sendMessage(msg);
                } catch (IOException e) {
                    msg.what = REQUEST_FAIL;
                }
            }
        }).start();
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
