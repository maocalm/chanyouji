package com.cyj.chanyouji;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.SlidLvAdapter;
import com.cyj.chanyouji.adapter.TripDetailTestAdapter;
import com.cyj.chanyouji.bean.TripDetail;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class TripActivity extends AppCompatActivity {

    private ListView lv;
    private TripDetail td;
    private String days;

    private ProgressBar pb;
    private ImageView cricle;
    private ListView slidLv;
    private LinearLayout slid;
    private int tranWidth,tranHeight;
    private int duration = 300;
    private boolean isChange = false;
    private RelativeLayout slidLeft;

    private SlidLvAdapter slidAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        initView();

        initTranslate();

        String id = getIntent().getStringExtra("id");
        String path = JsonUrl.YOUJI_TWO_START+id+JsonUrl.YOUJI_TWO_END;
        days = getIntent().getStringExtra("days");

        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                td = new Gson().fromJson(result, TripDetail.class);
                lv.addHeaderView(getHeaderView());

                final TripDetailTestAdapter adapter = new TripDetailTestAdapter(TripActivity.this,td);
                lv.setAdapter(adapter);

                lv.addFooterView(getFooterView());

                slidAdapter = new SlidLvAdapter(TripActivity.this,td,adapter.getSlidIndex());
                slidLv.setAdapter(slidAdapter);
                slidLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        lv.setSelection(adapter.getLvPosition().get(position));
                        if(!isChange) {
                            isChange = true;
                            hideSlid();
                        }
                    }
                });
                pb.setVisibility(View.GONE);
                cricle.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv);
        pb = (ProgressBar) findViewById(R.id.pb);

        cricle = (ImageView) findViewById(R.id.cricle);
        slidLv = (ListView) findViewById(R.id.slidLv);
        slid = (LinearLayout) findViewById(R.id.slid);
        slidLeft = (RelativeLayout) findViewById(R.id.slidLeft);
    }

    private void initTranslate() {

        tranWidth = slidLeft.getLayoutParams().width;
        int height = cricle.getTop();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        tranHeight = dm.heightPixels-height;
    }

    public View getHeaderView()
    {
        View v = getLayoutInflater().inflate(R.layout.header_trip_detail,null);
        ImageView front_cover_photo_url = (ImageView) v.findViewById(R.id.front_cover_photo_url);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView date_days_photos_count = (TextView) v.findViewById(R.id.date_days_photos_count);

        name.setText(td.getName());
        date_days_photos_count.setText(td.getStart_date()+" | "+days+"天,"+td.getPhotos_count()+"图");
        x.image().bind(front_cover_photo_url,td.getFront_cover_photo_url());
        ImageOptions options = new ImageOptions.Builder().setRadius(10).build();
        x.image().bind(image,td.getUser().getImage(),options);
        return v;
    }

    public View getFooterView()
    {
        View v = getLayoutInflater().inflate(R.layout.footer_trip_detail,null);
        RelativeLayout tip = (RelativeLayout) v.findViewById(R.id.tip);

        TripDetail.Tip tp = td.getTip();
        List<String> list = null;
        if(tp!=null) {
            list = td.getTip().getTexts();
        }
        if(list==null || list.size() == 0)
        {
            tip.setVisibility(View.GONE);
        }
        else {
            LinearLayout texts = (LinearLayout) v.findViewById(R.id.texts);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = 20;
            for (int i = 0; i < list.size(); i++) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(params);
                tv.setText(list.get(i));
                tv.setTextColor(Color.parseColor("#ff222222"));
                tv.setTextSize(15);
                texts.addView(tv);
            }
        }
        return  v;
    }

    public void back(View view) {
        finish();
    }

    public void changSlid(final View view) {

        if(!isChange) {
            isChange = true;
            if (slid.getVisibility() == View.GONE) {
                showSlid();
            } else {
                hideSlid();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(!isChange) {
            if (slid.getVisibility() == View.VISIBLE) {
                isChange = true;
                hideSlid();
            } else {
                super.onBackPressed();
            }
        }
    }

    private void hideSlid()
    {

        Animation tanim = new TranslateAnimation(0,-tranWidth,0,0);
        tanim.setDuration(duration);
        slidLeft.startAnimation(tanim);

        cricle.setVisibility(View.VISIBLE);

        ObjectAnimator oa = ObjectAnimator.ofFloat(cricle,"translationY",tranHeight,0)
                .setDuration(duration);
        oa.start();
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                slid.setVisibility(View.GONE);
                isChange = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void showSlid()
    {

        slid.setVisibility(View.VISIBLE);
        Animation tanim = new TranslateAnimation(-tranWidth,0,0,0);
        tanim.setDuration(duration);
        slidLeft.startAnimation(tanim);

        ObjectAnimator oa = ObjectAnimator.ofFloat(cricle,"translationY",0,tranHeight)
                .setDuration(duration);
        oa.start();
        oa.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                cricle.setVisibility(View.GONE);
                isChange = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void hide(View view) {
        if(!isChange)
        {
            isChange = true;
            hideSlid();
        }
    }
}
