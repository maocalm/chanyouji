package com.cyj.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.AbroadCountryAdapter;
import com.cyj.chanyouji.bean.ParseSearchCountry;
import com.cyj.chanyouji.url.JsonUrl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandy on 2016/10/31.
 */
public class AbroadFrgment extends Fragment{
    private PullToRefreshGridView mPullGv;
    private AbroadCountryAdapter adapter;
    private List<ParseSearchCountry.AbroadCountry> list;
    private String url = JsonUrl.SEARCH;
    private boolean isNeedLoading = true;
    private ProgressBar pb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_abroad,container,false);
        mPullGv = (PullToRefreshGridView) view.findViewById(R.id.pull_gv);
        pb = (ProgressBar) view.findViewById(R.id.pb);
        list = new ArrayList<>();

        init(url);


        mPullGv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mPullGv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                if(isNeedLoading){
                    init(url);
                }else{
                    mPullGv.onRefreshComplete();
                    Toast.makeText(getActivity(),"没有更多的数据了",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void init(String url) {
        Log.i("tag",url);
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseSearchCountry.AbroadCountry> datas = ParseSearchCountry.parseAbroadData(result);
                if(datas.size()!=0 && datas!=null){
                    list.addAll(datas);
                    adapter = new AbroadCountryAdapter(getActivity(),list);
                    mPullGv.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                    pb.setVisibility(View.GONE);
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
}
