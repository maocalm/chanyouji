package com.cyj.chanyouji.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.adapter.ToolDestinationAdapter;
import com.cyj.chanyouji.bean.ToolDestination;
import com.cyj.chanyouji.url.JsonUrl;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by mandy on 2016/11/3.
 */
public class SearchAbroadFragment extends Fragment {
    private ExpandableListView lv;
    private List<ToolDestination.Destination> list;
    private ToolDestinationAdapter adapter;

    private String type;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_search_abroad,container,false);

        type = getArguments().getString("type");

        lv = (ExpandableListView) view.findViewById(R.id.lv);
        RequestParams params = new RequestParams(JsonUrl.TOOL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                list = ToolDestination.parseData(result);
                adapter = new ToolDestinationAdapter(getActivity(),list,type);
                lv.setAdapter(adapter);
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
        return view;
    }

}
