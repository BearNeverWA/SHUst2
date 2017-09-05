package com.shu.shust2.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shu.shust2.R;
import com.shu.shust2.adapter.MainFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private MainFragmentPagerAdapter fragmentPagerAdapter;

    private TabLayout.Tab club, activity, welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {

//        //导航栏的初始化
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");

        //控件初始化
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_main);


        //绑定viewpager和fragment
        fragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), new String[]{"首页", "社团", "活动"});
        viewPager.setAdapter(fragmentPagerAdapter);

        //绑定tablayout和viewpager
        tabLayout.setupWithViewPager(viewPager);

        //指定Tab的位置
        welcome = tabLayout.getTabAt(0);
        welcome.setIcon(getResources().getDrawable(R.mipmap.ic_launcher_round));
        club = tabLayout.getTabAt(1);
        club.setIcon(getResources().getDrawable(R.mipmap.ic_launcher_round));
        activity = tabLayout.getTabAt(2);
        activity.setIcon(getResources().getDrawable(R.mipmap.ic_launcher_round));
    }
}
