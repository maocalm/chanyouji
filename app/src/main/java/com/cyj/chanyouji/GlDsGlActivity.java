package com.cyj.chanyouji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cyj.chanyouji.adapter.GlDsGlAdapter;
import com.cyj.chanyouji.bean.GlDsGl;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GlDsGlActivity extends AppCompatActivity {

    private ListView mListView;
    private List<GlDsGl> data;
    private GlDsGlAdapter adapter;
    private LinearLayout back;
    private ImageView search;
    private ProgressBar pb;
    String path ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_ds_gl);
        String id = getIntent().getStringExtra("id");
        String name  =  getIntent().getStringExtra("name");
        // http://chanyouji.com/api/wiki/destinations/55.json
        init();
        path = JsonUrl.SUB_GL_START +id +JsonUrl.SUB_GL_END  ;
        RequestParams params = new RequestParams(path);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type type  = new TypeToken<List<GlDsGl>>(){}.getType() ;
                List<GlDsGl> lis =  new Gson().fromJson(result ,type) ;
                data.addAll(lis) ;
                 adapter  = new GlDsGlAdapter (GlDsGlActivity.this ,data)  ;
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
        }) ;

    }

    private void init() {
        data= new ArrayList<>() ;
        mListView = (ListView) findViewById(R.id.lv);
        back = (LinearLayout) findViewById(R.id.back);
        search = (ImageView) findViewById(R.id.search);
        pb = (ProgressBar) findViewById(R.id.pb);
    }
}
