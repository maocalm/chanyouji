package com.cyj.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.HomeCountryAdapter;
import com.cyj.chanyouji.bean.ParseSearchCountry;
import com.cyj.chanyouji.url.JsonUrl;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandy on 2016/11/1.
 */
public class HomeFragment extends Fragment {
    private PullToRefreshGridView mPullGv;
    private HomeCountryAdapter adapter;
    private List<ParseSearchCountry.HomeCountry> list;
    private String url = JsonUrl.SEARCH;
    private ProgressBar pb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_home,container,false);
        mPullGv = (PullToRefreshGridView) view.findViewById(R.id.pull_gv);
        pb = (ProgressBar) view.findViewById(R.id.pb);

        list = new ArrayList<>();
        adapter = new HomeCountryAdapter(getActivity(),list);
        mPullGv.setAdapter(adapter);
        init(url);
        return view;
    }
    private void init(String url) {
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseSearchCountry.HomeCountry> datas = ParseSearchCountry.parseHomeData(result);
                if(datas.size()!=0 && datas!=null){
                    list.addAll(datas);
                    adapter.notifyDataSetChanged();
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

