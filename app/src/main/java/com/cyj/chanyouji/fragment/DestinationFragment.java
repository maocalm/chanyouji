package com.cyj.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.DestinationAdapter;
import com.cyj.chanyouji.bean.ParseDestination;
import com.cyj.chanyouji.url.JsonUrl;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandy on 2016/11/2.
 */
public class DestinationFragment extends Fragment {
    private PullToRefreshGridView mPullGv;
    private List<ParseDestination.Destination> list;
    private boolean isNeedLoading = true;
    private DestinationAdapter adapter;

    private String url = JsonUrl.DESTINATION;
    private String url1 = "";
    private String url2 ="";
    private int page = 1;
    private ProgressBar pb;
    private String q;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_destination,container,false);
        mPullGv = (PullToRefreshGridView) view.findViewById(R.id.pull_gv);
        pb = (ProgressBar) view.findViewById(R.id.pb);

        list = new ArrayList<>();
        adapter = new DestinationAdapter(getActivity(),list);
        mPullGv.setAdapter(adapter);

        q= getArguments().getString("q");

        url1 = url+"page=";
        url2 = "&q=";
        init(url1,page,url2,q);
        mPullGv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullGv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
                page = 1;
                refresh(url1,page,url2,q);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
                if(isNeedLoading){
                    page++;
                    init(url1,page,url2,q);
                }else{
                    mPullGv.onRefreshComplete();
                    Toast.makeText(getActivity(),"没有更多的数据了",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void refresh(String url1, int page, String url2, String q) {
        RequestParams params = new RequestParams(url1+page+url2+q);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseDestination.Destination> datas = ParseDestination.parseData(result);

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

    private void init(String url1, int page, String url2, String q) {
        RequestParams params = new RequestParams(url1+page+url2+q);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseDestination.Destination> datas = ParseDestination.parseData(result);

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
