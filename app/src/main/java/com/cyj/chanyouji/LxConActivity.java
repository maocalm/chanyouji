package com.cyj.chanyouji;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.LxCondapter;
import com.cyj.chanyouji.bean.LxCon;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class LxConActivity extends AppCompatActivity {

    private ProgressBar pb;
    private ListView mListView;
    private LxCondapter adapter;
    //LxCon  mLxCon ;
    LxCon c ;
    private List<LxCon.AttractionTripTagsBean> data;
    private LinearLayout back;

    private ImageView like, share, image;
    private String path;
    private TextView name, name_en, des, photo, trip, map ;
    WebView  html ;
    // 尾部
    private HorizontalScrollView scrollA, scrollH;
    private TextView countA, countH;

    RequestParams  params ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lx_con);
        // Log.i("l" , "=====sdsdf====="+data.size()) ;

        String id = getIntent().getStringExtra("id");
        final String days = getIntent().getStringExtra("day");
        final String counts = getIntent().getStringExtra("count");

        init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  showCustomDialag();
                showShare() ;
            }
        });

        path = JsonUrl.SUB_LX_TWO_START + id + JsonUrl.SUB_LX_TWO_END;
         params = new RequestParams(path);
        getHead();
        x.http().get(params, new Callback.CommonCallback<String>() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onSuccess(String result) {
                 c = new Gson().fromJson(result, LxCon.class);
                List<LxCon.AttractionTripTagsBean> l = c.getAttraction_trip_tags();
                data.addAll(l);
                adapter = new LxCondapter(data, LxConActivity.this);
                mListView.setAdapter(adapter);

                // 设置头部
               // getHead();
                name.setText(c.getName_zh_cn());
                name_en.setText(c.getName_en());
                ImageOptions  options  =new  ImageOptions.Builder().setConfig(Bitmap.Config.ARGB_4444)
                        .setFadeIn(true).setUseMemCache(true) .build();
                x.image().bind(image, c.getImage_url() ,options);
                photo.setText("图片" + c.getPhotos_count());
                trip.setText("游记" + c.getAttraction_trips_count());
                des.setText(c.getDescription());
                //html.setText(c.getTips_html());
                html.getSettings().setDefaultTextEncodingName("UTF-8");
                html.getSettings().setAllowContentAccess(true);
               html. setNetworkAvailable (true );
                html.getSettings().setAllowFileAccessFromFileURLs(true);
                html.loadDataWithBaseURL(null,c.getTips_html(),"text/html"," UTF-8" ,null);

                getFoot();
                pb.setVisibility(View.GONE);

                for (int i = 0; i < c.getAttractions().size(); i++) {
                    View viewI = getLayoutInflater().inflate(R.layout.lxconfoot_pic, null);
                    ImageView fimage = (ImageView) viewI.findViewById(R.id.fimage);
                    TextView fdistance, fname;
                    fdistance = (TextView) viewI.findViewById(R.id.fdistance);
                    fname = (TextView) viewI.findViewById(R.id.fname);

                    double a = Math.rint(Double.valueOf(c.getAttractions().get(i).getDistance()) * 100) / 100;
                    String di = String.valueOf(a);
                    ImageOptions  option  =new  ImageOptions.Builder().setConfig(Bitmap.Config.RGB_565)
                            .setFadeIn(true).setUseMemCache(true) .build();
                    x.image().bind(fimage, c.getAttractions().get(i).getImage_url(), option);
                    fdistance.setText(di);
                    String na = c.getAttractions().get(i).getName_zh_cn();
                    fname.setText(na);
                    scrollA.addView(viewI);

                }


                for (int i = 0; i < c.getHotels().size(); i++) {
                    View viewI = LayoutInflater.from(LxConActivity.this).inflate(R.layout.lxconfoot_pic, null);
                    ImageView fimage = (ImageView) viewI.findViewById(R.id.fimage);
                    TextView fdistance, fname;
                    fimage = (ImageView) viewI.findViewById(R.id.fimage);
                    fdistance = (TextView) viewI.findViewById(R.id.fdistance);
                    fname = (TextView) viewI.findViewById(R.id.fname);

                    double a = Math.rint(Double.valueOf(c.getHotels().get(i).getDistance()) * 100) / 100;
                    String di = String.valueOf(a);
                    ImageOptions  option  =new  ImageOptions.Builder().setConfig(Bitmap.Config.RGB_565)
                            .setFadeIn(true).setUseMemCache(true) .build();
                    x.image().bind(fimage, c.getHotels().get(i).getImage_url(),option);
                    fdistance.setText(di);
                    String na = c.getHotels().get(i).getName_zh_cn();
                    fname.setText(na);
                    scrollH.addView(viewI);
                }
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

//        photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                  Intent intent= new Intent(LxConActivity.this  , LcConPhotoActivity.class) ;
//                intent.putExtra("id" ,c.getId()) ;
//                //   Log.i("id" , "============"+c.getId())  ;
//                startActivity(intent);
//            }
//        });


    }

    public void init() {
        mListView = (ListView) findViewById(R.id.lv);
        data = new ArrayList<>();
        back = (LinearLayout) findViewById(R.id.back);
        like = (ImageView) findViewById(R.id.like);
        share = (ImageView) findViewById(R.id.share);
        pb = (ProgressBar) findViewById(R.id.pb);
    }

    private void getHead() {

        View view = getLayoutInflater().inflate(R.layout.lxcon_head_item, null);
        image = (ImageView) view.findViewById(R.id.image);
        name = (TextView) view.findViewById(R.id.name);
        name_en = (TextView) view.findViewById(R.id.name_en);
        photo = (TextView) view.findViewById(R.id.photo);
        trip = (TextView) view.findViewById(R.id.trip);
        map = (TextView) view.findViewById(R.id.map);
        des = (TextView) view.findViewById(R.id.des);
        html = (WebView) view.findViewById(R.id.html);
        mListView.addHeaderView(view);
    }

    private void getFoot() {

        View view = getLayoutInflater().inflate(R.layout.lxcon_foot_item, null);

        countA = (TextView) view.findViewById(R.id.countA);
        countH = (TextView) view.findViewById(R.id.countH);
        scrollA = (HorizontalScrollView) view.findViewById(R.id.scrollA);
        scrollH = (HorizontalScrollView) view.findViewById(R.id.scrollH);



    }

//    public void showCustomDialag() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("分享到");
//        View view = getLayoutInflater().inflate(R.layout.share_dialg, null);
//        builder.setView(view);
//        //  builder.setCancelable(true) ;
//        builder.setNegativeButton("取消", null);
//        builder.show();
//
//    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("推荐蝉游记");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://chanyouji.com/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("发现旅行之美，保存旅行回忆");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://chanyouji.com/");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("发现旅行之美，保存旅行回忆");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://chanyouji.com/");

// 启动分享GUI
        oks.show(this);
    }
}
