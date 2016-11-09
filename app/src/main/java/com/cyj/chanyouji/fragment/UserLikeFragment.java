package com.cyj.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.UserLikeAdapter;
import com.cyj.chanyouji.bean.ParseUserLike;
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
public class UserLikeFragment extends Fragment {
    private PullToRefreshGridView mPullGv;
    private List<ParseUserLike.UserLike> list;
    private UserLikeAdapter adapter;
    private String url = JsonUrl.USER_LIKE;
    private int page = 1;
    private ProgressBar pb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_userlike,container,false);

        mPullGv = (PullToRefreshGridView) view.findViewById(R.id.pull_gv);
        pb = (ProgressBar) view.findViewById(R.id.pb);

        list = new ArrayList<>();
        adapter = new UserLikeAdapter(list,getActivity());
        mPullGv.setAdapter(adapter);

        final String id = getArguments().getString("id");
        final String str = ".json?per_page=15&page=";
        init(url,id,str,page);

        mPullGv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mPullGv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                page++;
                init(url,id,str,page);
            }
        });
        return view;
    }

    private void init(String url,String id, String str, int page) {
        RequestParams params = new RequestParams(url+id+str+page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ParseUserLike.UserLike> datas = ParseUserLike.parseData(result);
                if (datas != null) {
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
