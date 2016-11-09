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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cyj.chanyouji.GlDesActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.GongLueAdapter;
import com.cyj.chanyouji.bean.Country;
import com.cyj.chanyouji.customview.GLGridView;
import com.cyj.chanyouji.url.JsonUrl;
import com.cyj.chanyouji.utils.GLRecordUtil;
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
 * Created by maocalm on 2016/10/31.
 */
public class GongLueFrag extends Fragment {

    private PullToRefreshListView lv;
    private ProgressBar pb;

    private List<Country> list;
    private GongLueAdapter adapter;

    private LayoutInflater inflater;

    private List<String> record;
    private ArrayAdapter<String> headerAdapter;
    private View headerView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    adapter.notifyDataSetChanged();
                    lv.onRefreshComplete();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frag_gonglue,container,false);
        this.inflater = inflater;

        pb = (ProgressBar) v.findViewById(R.id.pb);
        lv = (PullToRefreshListView) v.findViewById(R.id.lv);
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

        record = new ArrayList<String>();
        headerAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item_gv_header, record);
        headerView = inflater.inflate(R.layout.header_gonglue, null);
        GLGridView browse = (GLGridView) headerView.findViewById(R.id.browse);
        browse.setAdapter(headerAdapter);
        browse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String i = GLRecordUtil.getDescRecord(getActivity(),GLRecordUtil.ID).get(position);
                String name = GLRecordUtil.getDescRecord(getActivity(),GLRecordUtil.RECORD).get(position);
                Intent intent= new Intent(getActivity(), GlDesActivity.class) ;
                intent.putExtra("id", i) ;
                intent.putExtra("name" ,name) ;
                getActivity().startActivity(intent);
            }
        });

        getData();

        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                Refresh();
            }
        });
        return v;
    }

    private void Refresh()
    {
        RequestParams params = new RequestParams(JsonUrl.GONGLUE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<Country> l = new Gson().fromJson(result,new TypeToken<List<Country>>(){}.getType());
                if(l.get(0).getDestinations().get(0).getId().equals(list.get(0).getDestinations().get(0).getId()))
                {
                    list.clear();
                    list.addAll(l);
                    handler.sendEmptyMessage(1);
                }
                else
                    lv.onRefreshComplete();
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

        RequestParams params = new RequestParams(JsonUrl.GONGLUE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                list = new Gson().fromJson(result,new TypeToken<List<Country>>(){}.getType());
                adapter = new GongLueAdapter(getActivity(),list,lv,headerView,headerAdapter,record);
                lv.setAdapter(adapter);

                record.clear();
                record.addAll(GLRecordUtil.getDescRecord(getActivity(),GLRecordUtil.RECORD));
                if(record.size()!=0)
                {
                    lv.getRefreshableView().addHeaderView(headerView);
                }

                View footer = getFooterView();
                lv.getRefreshableView().addFooterView(footer);
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
    private View getFooterView() {
        View v = inflater.inflate(R.layout.footer_gv,null);
        return v;
    }
}
