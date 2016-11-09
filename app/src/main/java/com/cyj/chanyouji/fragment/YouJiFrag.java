package com.cyj.chanyouji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cyj.chanyouji.BannerActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.YouJiAdapter;
import com.cyj.chanyouji.bean.Banner;
import com.cyj.chanyouji.bean.Trip;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Biu丶 on 2016/10/31.
 */
public class YouJiFrag extends Fragment{

    private PullToRefreshListView yj_lv;
    private ProgressBar pb;
    private YouJiAdapter adapter;

    private String banner = JsonUrl.BANNER;
    private String trip = JsonUrl.YOUJI;
    private int tripIndex = 1;

    private List<Banner> banners;
    private List<Trip> trips;
    private List<Banner> mid;

    private LayoutInflater inflater;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    adapter.notifyDataSetChanged();
                    yj_lv.onRefreshComplete();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_youji,container,false);
        this.inflater = inflater;

        init(v);
        getData();

        yj_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                load();
            }
        });


        return v;
    }

    private void init(View v)
    {
        yj_lv = (PullToRefreshListView) v.findViewById(R.id.yj_lv);
        pb = (ProgressBar) v.findViewById(R.id.pb);
        yj_lv.setMode(PullToRefreshBase.Mode.BOTH);
        banners = new ArrayList<Banner>();
        trips = new ArrayList<Trip>();
        mid = new ArrayList<Banner>();
    }

    private void refresh()
    {
        RequestParams params2 = new RequestParams(trip+1);
        x.http().get(params2, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<Trip> l = new Gson().fromJson(result,new TypeToken<List<Trip>>(){}.getType());
                if(!l.get(0).getId().equals(trips.get(0).getId())) {
                    trips.clear();
                    trips.addAll(l);
                    tripIndex = 2;
                    handler.sendEmptyMessage(1);
                }
                else {
                    yj_lv.onRefreshComplete();
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

    private void load()
    {
        RequestParams params = new RequestParams(trip+tripIndex);
        tripIndex++;
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<Trip> l = new Gson().fromJson(result,new TypeToken<List<Trip>>(){}.getType());
                trips.addAll(l);
                handler.sendEmptyMessage(1);
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

    private void getData() {

        RequestParams params = new RequestParams(banner);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<Banner> l = new Gson().fromJson(result,new TypeToken<List<Banner>>(){}.getType());
                banners.clear();
                banners.addAll(l);
                mid.clear();
                for(Banner banner:banners)
                {
                    if(!banner.getPosition().equals("0"))
                    {
                        mid.add(banner);
                    }
                }
                RequestParams params2 = new RequestParams(trip+tripIndex);
                tripIndex++;
                x.http().get(params2, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        List<Trip> l = new Gson().fromJson(result,new TypeToken<List<Trip>>(){}.getType());
                        trips.clear();
                        trips.addAll(l);
                        adapter = new YouJiAdapter(getActivity(),trips,mid);
                        yj_lv.setAdapter(adapter);
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

                final ImageView v = (ImageView) inflater.inflate(R.layout.header_youji,null);
                x.image().bind(v, banners.get(0).getImage_url());
                yj_lv.getRefreshableView().addHeaderView(v);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), BannerActivity.class);
                        String id = banners.get(0).getContent();
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                });
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
