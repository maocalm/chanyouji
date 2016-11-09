package com.cyj.chanyouji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cyj.chanyouji.adapter.CountryAdapter;
import com.cyj.chanyouji.bean.ParseCountry;
import com.cyj.chanyouji.url.JsonUrl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MonthActivity extends AppCompatActivity {
    private PullToRefreshGridView mPullGv;
    private TextView tv_title;
    private String title,id;
    private List<ParseCountry.Country> list;
    private boolean isNeedLoading = true;
    private CountryAdapter adapter;
    private ImageView back;
    private String url = JsonUrl.SEARCH_MONTH;
    private String finalUrl = "";
    private int page = 1;
    private ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        tv_title = (TextView) findViewById(R.id.title);
        mPullGv = (PullToRefreshGridView) findViewById(R.id.pull_gv);
        pb = (ProgressBar) findViewById(R.id.pb);
        back = (ImageView) findViewById(R.id.back);
        list = new ArrayList<>();
        adapter = new CountryAdapter(this,list);
        mPullGv.setAdapter(adapter);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(title+"游记");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        finalUrl = url+id+".json?page=";
        init(finalUrl,page);

        mPullGv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullGv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                refresh(finalUrl,page);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                if(isNeedLoading){
                    page++;
                    init(finalUrl,page);
                }else{
                    mPullGv.onRefreshComplete();
                    Toast.makeText(MonthActivity.this,"没有更多的数据了",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void refresh(String finalUrl, int page) {
        RequestParams params = new RequestParams(url+page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseCountry.Country> datas = ParseCountry.parseData(result);
                if(list.size()>0){
                    if(list.get(0).getId().equals(datas.get(0).getId())){
                        mPullGv.onRefreshComplete();
                    }else{
                        list.clear();
                        list.addAll(datas);
                        adapter.notifyDataSetChanged();
                        mPullGv.onRefreshComplete();
                    }
                }else{
                    list.addAll(datas);
                    adapter.notifyDataSetChanged();
                    mPullGv.onRefreshComplete();
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
    }

    private void init(String url, int page) {
        RequestParams params = new RequestParams(url+page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseCountry.Country> datas = ParseCountry.parseData(result);

                if(datas.size()!=0 && datas!=null){
                    list.addAll(datas);
                    adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
                    mPullGv.onRefreshComplete();
                }else{
                    mPullGv.onRefreshComplete();

                    isNeedLoading = false;
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
    }
}
