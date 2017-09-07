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
import com.shu.shust2.model.ClubDetail;
import com.shu.shust2.util.JsonUtil;
import com.shu.shust2.util.OkConnect;

import java.io.IOException;
import java.util.List;

public class ClubDetailActivity extends AppCompatActivity {

    private Intent intent;
    private TextView type, star, chairman, teacher, intro;
    private Handler handler;
    private String clubDetailData;
    private ClubDetail clubDetail;
    private ClubDetail.ResultsBean resultsBean;

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
                        clubDetail = JsonUtil.parseJson(clubDetailData, ClubDetail.class);
                        if (clubDetail.getErrorCode() == 0) {
                            if (clubDetail.getErrorStr().equals("ok")) {
                                resultsBean = clubDetail.getResults();
                                switch (resultsBean.getType()) {
                                    case "学术科技":
                                        type.setBackgroundResource(R.drawable.blue_border);
                                        type.setTextColor(getResources().getColor(R.color.soft_blue));
                                        break;
                                    case "体育健身":
                                        type.setBackgroundResource(R.drawable.green_border);
                                        type.setTextColor(getResources().getColor(R.color.soft_green));
                                        break;
                                    case "公益实践":
                                        type.setBackgroundResource(R.drawable.orange_border);
                                        type.setTextColor(getResources().getColor(R.color.orange));
                                        break;
                                    case "文化艺术":
                                        type.setBackgroundResource(R.drawable.red_border);
                                        type.setTextColor(getResources().getColor(R.color.red));
                                        break;
                                    case "社会科学":
                                        type.setBackgroundResource(R.drawable.pink_round);
                                        type.setTextColor(getResources().getColor(R.color.pink));
                                        break;
                                    case "理论学习":
                                        type.setBackgroundResource(R.drawable.purple_border);
                                        type.setTextColor(getResources().getColor(R.color.purple));
                                        break;
                                    default:
                                        break;
                                }
                                if (resultsBean.getType().equals(""))
                                    type.setText("无");
                                else
                                    type.setText(resultsBean.getType());

                                String st;
                                switch (resultsBean.getStar()) {
                                    case 1:
                                        st = "一星级";
                                        break;
                                    case 2:
                                        st = "两星级";
                                        break;
                                    case 3:
                                        st = "三星级";
                                        break;
                                    case 4:
                                        st = "四星级";
                                        break;
                                    case 5:
                                        st = "五星级";
                                        break;
                                    default:
                                        st = "无";
                                        break;
                                }
                                star.setText(st);
                                if (resultsBean.getChairperson().equals(""))
                                    chairman.setText("无");
                                else
                                    chairman.setText(resultsBean.getChairperson());
                                if (resultsBean.getInstructor().equals(""))
                                    teacher.setText("无");
                                else
                                    teacher.setText(resultsBean.getInstructor());
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
