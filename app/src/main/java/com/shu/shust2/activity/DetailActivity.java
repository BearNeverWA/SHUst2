package com.shu.shust2.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shu.shust2.R;
import com.shu.shust2.model.ActivityDetail;
import com.shu.shust2.model.ClubDetail;
import com.shu.shust2.util.JsonUtil;
import com.shu.shust2.util.OkConnect;

import java.io.IOException;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private Intent intent;
    private TextView type, status, association, personCount, location, duration, intro;
    private Handler handler;
    private String activityDetailData;
    private ActivityDetail activityDetail;
    private ActivityDetail.ResultsBean resultsBean;

    private static final String TAG = "DetailActivity";
    private static final String URL_TEMP = "http://api.dev.shust.cn/activity/detail?id=";
    private static final int REQUEST_SUCCESS = 1;
    private static final int REQUEST_FAIL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        intent = getIntent();
        initView();
        initData();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activity_detail);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctb_activity_detail);
        ImageView imageView = (ImageView) findViewById(R.id.iv_activity_banner);
        type = (TextView) findViewById(R.id.activity_type);
        status = (TextView) findViewById(R.id.activity_status);
        association = (TextView) findViewById(R.id.activity_association);
        personCount = (TextView) findViewById(R.id.activity_person_count);
        location = (TextView) findViewById(R.id.activity_location);
        duration = (TextView) findViewById(R.id.activity_duration);
        intro = (TextView) findViewById(R.id.activity_intro);

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
                        activityDetail = JsonUtil.parseJson(activityDetailData, ActivityDetail.class);
                        if (activityDetail.getErrorCode() == 0) {
                            if (activityDetail.getErrorStr().equals("ok")) {
                                resultsBean = activityDetail.getResults();
                                switch (resultsBean.getStatus()) {
                                    case "已修改":
                                        status.setBackgroundResource(R.drawable.blue_border);
                                        status.setTextColor(getResources().getColor(R.color.soft_blue));
                                        break;
                                    case "未开始":
                                        status.setBackgroundResource(R.drawable.grey_border);
                                        status.setTextColor(getResources().getColor(R.color.new_grey));
                                        break;
                                    case "进行中":
                                        status.setBackgroundResource(R.drawable.green_border);
                                        status.setTextColor(getResources().getColor(R.color.soft_green));
                                        break;
                                    case "审核中":
                                        status.setBackgroundResource(R.drawable.red_border);
                                        status.setTextColor(getResources().getColor(R.color.red));
                                        break;
                                    default:
                                        break;
                                }
                                status.setText(resultsBean.getStatus());
                                if (resultsBean.getType().equals(""))
                                    type.setText("无");
                                else
                                    type.setText(resultsBean.getType());
                                if (resultsBean.getAssociation().equals(""))
                                    association.setText("无");
                                else
                                    association.setText(resultsBean.getAssociation());
                                personCount.setText(String.valueOf(resultsBean.getSet_people()));
                                if (resultsBean.getLocation().equals(""))
                                    location.setText("无");
                                else
                                    location.setText(resultsBean.getLocation());
                                if (resultsBean.getStart_time().equals("") && resultsBean.getEnd_time().equals(""))
                                    duration.setText("无");
                                else
                                    duration.setText(resultsBean.getStart_time() + "--" + resultsBean.getStart_time());
                                if (resultsBean.getIntroduction().equals(""))
                                    intro.setText("无");
                                else
                                    intro.setText(Html.fromHtml(resultsBean.getIntroduction()));
                            }
                        }
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
                    activityDetailData = connect.run(URL_TEMP + intent.getIntExtra("id", 0));
                    if (!activityDetailData.equals("error"))
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
