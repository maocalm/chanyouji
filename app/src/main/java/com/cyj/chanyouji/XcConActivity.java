package com.cyj.chanyouji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.XcCondapter;
import com.cyj.chanyouji.bean.XcCon;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class XcConActivity extends AppCompatActivity {

    private ListView mListView;
    private XcCondapter adapter;
    private ProgressBar pb;

    private List<XcCon.Plan_days> data;
    private LinearLayout back;

    private ImageView like, share ,image;

    private String path;
    private TextView name;
    private TextView day;
    private TextView count;
    private TextView des;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xc_con);

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
                //showCustomDialag() ;
                showShare() ;

            }
        });


        path = JsonUrl.SUB_XC_TWO_START + id + JsonUrl.SUB_XC_TWO_END;
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                XcCon c = new Gson().fromJson(result, XcCon.class);
                List<XcCon.Plan_days> l = c.getPlan_days();
                data.addAll(l) ;
                adapter= new XcCondapter(data ,XcConActivity.this) ;
                mListView.setAdapter(adapter);
                getHead();

                name.setText(c.getName());
                day.setText(days+"天 ");
                count.setText("/ "+counts+"个旅行地");
                des.setText( c.getDescription());
                x.image().bind(image ,c.getImage_url());

                pb.setVisibility(View.GONE);
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

    public void init() {
        mListView = (ListView) findViewById(R.id.lv);
        data = new ArrayList<>();
        back = (LinearLayout) findViewById(R.id.back);
        like = (ImageView) findViewById(R.id.like);
        share = (ImageView) findViewById(R.id.share);
        pb = (ProgressBar) findViewById(R.id.pb);
    }

    private  void getHead() {

        View  view  =  getLayoutInflater().inflate(R.layout.gl_xc_item ,null) ;
        image  = (ImageView) view.findViewById(R.id.image);
        name=  (TextView)view.findViewById(R.id.name);
        day=  (TextView)view.findViewById(R.id.day);
        count=  (TextView)view.findViewById(R.id.count);
        des=  (TextView)view.findViewById(R.id.des);
        mListView.addHeaderView(view);

    }
//    public void showCustomDialag(){
//        AlertDialog.Builder builder= new AlertDialog.Builder(this) ;
//        builder.setTitle("分享到") ;
//        View  view = getLayoutInflater().inflate(R.layout.share_dialg ,null) ;
//        builder.setView(view) ;
//        //  builder.setCancelable(true) ;
//        builder.setNegativeButton("取消", null);
//
//        builder.show() ;
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
