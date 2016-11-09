package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.GlDsXcAdapter;
import com.cyj.chanyouji.bean.GlDsxCLv;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.List;

public class GlDsXcActivity extends AppCompatActivity {


    private String url;
    private PullToRefreshListView mListView;
    private GlDsXcAdapter adapter;
    private List<GlDsxCLv> data;
    private LinearLayout layout;
    private TextView backname;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_ds_xc);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        url = JsonUrl.SUB_XC_START + id + JsonUrl.SUB_XC_END;
        init();

        backname.setText(name);
        mListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position--;
                Intent intent= new Intent(GlDsXcActivity.this,XcConActivity.class);
                intent.putExtra("id" ,data.get(position).getId()) ;
                intent.putExtra("day" ,data.get(position).getPlan_days_count()) ;
                intent.putExtra("count" ,data.get(position).getPlan_nodes_count()) ;
                startActivity(intent);
             }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Resfresh(url);
            }

        });

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Type type = new TypeToken<List<GlDsxCLv>>() {
                                }.getType();
                                data = new Gson().fromJson(result, type);
                                Log.d("test","data:"+data);
                                adapter = new GlDsXcAdapter(GlDsXcActivity.this, data);
                                mListView.setAdapter(adapter);
                                pb.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(Callback.CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        }

                );
    }

    private void init() {
        mListView = (PullToRefreshListView) this.findViewById(R.id.lv);
        layout = (LinearLayout) findViewById(R.id.back);

        pb = (ProgressBar) findViewById(R.id.pb);
        backname = (TextView) findViewById(R.id.backname);
    }

    private void Resfresh(String path) {

        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<GlDsxCLv> l = new Gson().fromJson(result, new TypeToken<List<GlDsxCLv>>() {
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



