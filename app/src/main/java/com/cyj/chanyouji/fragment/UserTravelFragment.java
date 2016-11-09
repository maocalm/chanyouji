package com.cyj.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.UserTravelAdapter;
import com.cyj.chanyouji.bean.ParseUserTravel;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;
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
public class UserTravelFragment extends Fragment {
    private PullToRefreshGridView mPullGv;
    private UserTravelAdapter adapter;
    private List<ParseUserTravel.Trips> list;
    private String url = JsonUrl.USER_TRAVEL;
    private int page = 1;
    private Boolean isNeedLoading = true;
    private String id = "";
    private String str = "";
    private ProgressBar pb;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_usertravel,container,false);
        mPullGv = (PullToRefreshGridView) view.findViewById(R.id.pull_gv);
        pb = (ProgressBar) view.findViewById(R.id.pb);

        list = new ArrayList<>();
        adapter = new UserTravelAdapter(getActivity(),list);
        mPullGv.setAdapter(adapter);

        id = getArguments().getString("id");
        str = ".json?page=";
        init(url,id,str,page);
        Log.i("tag",url+id+str+page);

        mPullGv.setMode(PullToRefreshBase.Mode.BOTH);
        mPullGv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                if(isNeedLoading){
                    page++;
                    init(url,id,str,page);
                }else{
                    mPullGv.onRefreshComplete();
                    Toast.makeText(getActivity(),"没有更多的数据了",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void init(String url,String id, String str, int page) {
        RequestParams params = new RequestParams(url+id+str+page);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                ParseUserTravel travel = new Gson().fromJson(result,ParseUserTravel.class);
                List<ParseUserTravel.Trips> datas = travel.getTrips();

                if (datas != null) {
                        list.addAll(datas);
                    TextView trips_count = (TextView) getActivity().findViewById(R.id.trips_count);
                    trips_count.setText(travel.getTrips_count()+"篇游记");
                    ImageView sex = (ImageView) getActivity().findViewById(R.id.sex);
                    if(travel.getGender().equals(0))
                        sex.setImageResource(R.drawable.female);
                    else
                        sex.setImageResource(R.drawable.male);
                    if(travel.getGender().equals(0))
                    {

                    }
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
