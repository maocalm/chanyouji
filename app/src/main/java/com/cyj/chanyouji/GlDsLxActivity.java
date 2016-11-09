package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cyj.chanyouji.adapter.GlDsLxAdapter;
import com.cyj.chanyouji.bean.GlDsLxLv;
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

public class GlDsLxActivity extends AppCompatActivity {

    private String path;
    String url ;
    private int page = 1;
    private PullToRefreshListView  mListView;
    private GlDsLxAdapter  adapter;
    private List <GlDsLxLv> data;
    private LinearLayout  back ;
    private ImageView  map ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_ds_lx);

        String id = getIntent().getStringExtra("id");
        String backname = getIntent().getStringExtra("name");
         url = JsonUrl.SUB_LXD_START + id + JsonUrl.SUB_LXD_END ;
          path = url+ page;

        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position--;
                Intent  intent= new Intent(GlDsLxActivity.this , LxConActivity.class) ;
                intent.putExtra("id" , data.get(position).getId()) ;
                GlDsLxActivity.this.startActivity(intent);
            }
        });
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {


            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Resfresh(url+1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                load() ;
            }
        });
        RequestParams params= new RequestParams(url+1) ;
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Type  type  = new TypeToken<List<GlDsLxLv>>(){}.getType() ;
               List<GlDsLxLv>  list =  new Gson().fromJson(result  ,type) ;
                data.addAll(list)  ;
                mListView.setAdapter(adapter);

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

    private   void init() {
        data = new ArrayList<>() ;
        mListView  = (PullToRefreshListView) this.findViewById(R.id.lv);
        adapter = new GlDsLxAdapter(this ,data) ;
        back= (LinearLayout) findViewById(R.id.back);
        map = (ImageView)findViewById(R.id.map) ;
    }


    private void Resfresh(String path) {

        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<GlDsLxLv> l = new Gson().fromJson(result, new TypeToken<List<GlDsLxLv>>() {
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

    private void load()
    {
        page++;
        RequestParams params = new RequestParams(url+page);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<GlDsLxLv> l = new Gson().fromJson(result,new TypeToken<List<GlDsLxLv>>(){}.getType());
                data.addAll(l);
                adapter.notifyDataSetChanged();
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
