package com.shu.shust2.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shu.shust2.R;
import com.shu.shust2.model.ClubDetail;
import com.shu.shust2.util.JsonUtil;
import com.shu.shust2.util.OkConnect;

import java.io.IOException;

public class ClubDetailActivity extends AppCompatActivity {

    private Intent intent;
    private TextView type, star, chairman, teacher, intro;
    private ImageView qqCode, wechatCode;
    private Handler handler;
    private String clubDetailData;
    private ClubDetail clubDetail;

    private static final String TAG = "ClubDetailActivity";
    private static final String URL_TEMP = "http://api.dev.shust.cn/association/detail?id=";
    private static final int REQUEST_SUCCESS = 1;
    private static final int REQUEST_FAIL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_detail);
        intent = getIntent();
        initView();
        initData();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_club_detail);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctb_club_detail);
        ImageView imageView = (ImageView) findViewById(R.id.iv_club_banner);
        type = (TextView) findViewById(R.id.club_type);
        star = (TextView) findViewById(R.id.club_star);
        chairman = (TextView) findViewById(R.id.club_chairman);
        teacher = (TextView) findViewById(R.id.club_teacher);
        qqCode = (ImageView) findViewById(R.id.qq_code);
        wechatCode = (ImageView) findViewById(R.id.wechat_code);
        intro = (TextView) findViewById(R.id.club_intro);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setTitle(intent.getStringExtra("name"));
        Glide.with(this).load(intent.getStringExtra("logo")).error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(imageView);

    }

    private void initData() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case REQUEST_SUCCESS:
                        clubDetail= JsonUtil.parseJson(clubDetailData,ClubDetail.class);

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
                Message msg = handler.obtainMessage();
                OkConnect connect = new OkConnect();
                try {
                    clubDetailData = connect.run(URL_TEMP + intent.getIntExtra("id", 0));
                    if (!clubDetailData.equals("error"))
                        msg.what = REQUEST_SUCCESS;
                    else
                        msg.what = REQUEST_FAIL;
                } catch (IOException e) {
                    msg.what = REQUEST_FAIL;
                }
                handler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
