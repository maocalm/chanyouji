package com.cyj.chanyouji;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.GLDesLvAdapter;
import com.cyj.chanyouji.bean.Destination;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class GlDesActivity extends AppCompatActivity {


    private PullToRefreshListView mListView;
    private GLDesLvAdapter adapter;
    private List<Destination> data;
    private ProgressBar pb;

    private LinearLayout mLayout;
    private TextView backname;
    private ImageView share ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_des);
        String id = getIntent().getStringExtra("id");

        final String path = JsonUrl.GONGLUE_TWO_START + id +JsonUrl.GONGLUE_TWO_END;
        Log.i("path" ,  "===="+path) ;
        init();
        backname.setText(getIntent().getStringExtra("name") + "攻略");
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showCustomDialag();
                showShare();
            }
        });

        mListView = (PullToRefreshListView) this.findViewById(R.id.lv);

        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Resfresh(path);
            }
        });
        backname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RequestParams requestParams = new RequestParams(path);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type = new TypeToken<List<Destination>>() {
                }.getType();
                List<Destination> de = new Gson().fromJson(result, type);
                data.addAll(de);
                adapter = new GLDesLvAdapter(data, GlDesActivity.this);
                mListView.setAdapter(adapter);
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

        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlDesActivity.this.finish();
            }
        });

    }

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

    private void init() {
        mLayout = (LinearLayout) findViewById(R.id.back);
        backname = (TextView) findViewById(R.id.backname);
        data = new ArrayList<>();
        share=  (ImageView)findViewById(R.id.share) ;
        pb = (ProgressBar) findViewById(R.id.pb);

    }

    public void showCustomDialag(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this) ;
        builder.setTitle("分享到") ;
        View  view = getLayoutInflater().inflate(R.layout.share_dialg ,null) ;
        builder.setView(view) ;
        //  builder.setCancelable(true) ;
        builder.setNegativeButton("取消", null);

        builder.show() ;

    }

    private void Resfresh(String path) {

        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<Destination> l = new Gson().fromJson(result, new TypeToken<List<Destination>>() {
                }.getType());
                if (!l.get(0).getId().equals(data.get(0).getId())) {
                    data.clear();
                    data.addAll(l);
                    adapter.notifyDataSetChanged();
                    mListView.onRefreshComplete();
                } else

                    mListView.onRefreshComplete();
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


}
