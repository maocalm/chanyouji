package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cyj.chanyouji.adapter.CountryAdapter;
import com.cyj.chanyouji.bean.ParseCountry;
import com.cyj.chanyouji.url.JsonUrl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class SearchCountryItemActivity extends AppCompatActivity {
    private PullToRefreshListView mPullLv;
    private TextView tv_title,tv_count;
    private String title,id;
    private List<ParseCountry.Country> list;
    private boolean isNeedLoading = true;
    private CountryAdapter adapter;
    private ImageView back;
    private String count;
    private String[] left = {"全部","1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
    private String[] right = {"人气","最新"};
    private Spinner left_spinner;
    private Spinner right_spinner;
    private ArrayAdapter<String> left_adapter;
    private ArrayAdapter<String> right_adapter;

    private ProgressBar pb;

    private int l,r;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_country_item);

        pb = (ProgressBar) findViewById(R.id.pb);
        left_spinner = (Spinner) findViewById(R.id.left_spinner);
        right_spinner = (Spinner) findViewById(R.id.right_spinner);

        left_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,left);
        right_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,right);

        left_adapter.setDropDownViewResource(R.layout.drop_down_item);
        right_adapter.setDropDownViewResource(R.layout.drop_down_item);

        left_spinner.setAdapter(left_adapter);
        right_spinner.setAdapter(right_adapter);


        l = 0;
        r = 0;
        tv_title = (TextView) findViewById(R.id.title);
        tv_count = (TextView) findViewById(R.id.count);
        mPullLv = (PullToRefreshListView) findViewById(R.id.pull_gv);
        back = (ImageView) findViewById(R.id.back);
        list = new ArrayList<>();
        adapter = new CountryAdapter(this,list);
        mPullLv.setAdapter(adapter);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        count = intent.getStringExtra("count");
        tv_title = (TextView) findViewById(R.id.title);
        tv_title.setText(title+"游记");
        tv_count.setText(count+"篇游记");

        left_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                l = position;
                mPullLv.setRefreshing(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        right_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                r = position;
                mPullLv.setRefreshing(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loadParams(getPath());

        mPullLv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                refreshParams(getPath());
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if(isNeedLoading){
                    page++;
                    loadParams(getPath());
                }else{
                    mPullLv.onRefreshComplete();
                    Toast.makeText(SearchCountryItemActivity.this,"没有更多的数据了",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private String getPath() {

//        finalUrl = url+id+".json?page=";
//        finalUrl1 = url+id+".json?order=date&page=";
//        monthurl = "&month=";
        String path = JsonUrl.SEARCH_TWO+id+".json?page="+page;
        page++;

        if(l != 0)
        {
            path+="&month="+l;
        }
        if(r == 1)
        {
            path+="&order=date";
        }

        Log.d("test","left:"+l+",right:"+r);
        Log.d("test","path:"+path);
        return  path;

    }


    private void loadParams(String path) {
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseCountry.Country> datas = ParseCountry.parseData(result);
                if(datas!=null&&datas.size()!=0){
                    list.addAll(datas);
                    adapter.notifyDataSetChanged();
                    mPullLv.onRefreshComplete();
                }else{
                    mPullLv.onRefreshComplete();
                    isNeedLoading = false;
                }
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
    private void refreshParams(String path)
    {
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseCountry.Country> datas = ParseCountry.parseData(result);
                if(list.size()>0)
                {
                    if(list.get(0).getId().equals(datas.get(0).getId()))
                    {
                        mPullLv.onRefreshComplete();
                    }
                    else
                    {
                        list.clear();
                        list.addAll(datas);
                        adapter.notifyDataSetChanged();
                        mPullLv.onRefreshComplete();
                    }
                }
                else{
                    list.addAll(datas);
                    adapter.notifyDataSetChanged();
                    mPullLv.onRefreshComplete();
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
