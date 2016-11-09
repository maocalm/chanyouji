package com.cyj.chanyouji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.cyj.chanyouji.MonthActivity;
import com.cyj.chanyouji.R;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.LinkedList;

/**
 * Created by mandy on 2016/10/31.
 */
public class MonthFragment extends Fragment {
    private LinkedList<String> mListItems;
    private PullToRefreshGridView mPullGv;
    private ArrayAdapter<String> mAdapter;
    private int monthcount = 12;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_month,container,false);
        mPullGv = (PullToRefreshGridView) view.findViewById(R.id.pull_gv);

        init();

        mAdapter = new ArrayAdapter<String>(getActivity(),R.layout.abroad_item_gv,R.id.id_counrty,mListItems);
        mPullGv.setAdapter(mAdapter);

        mPullGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MonthActivity.class);
                intent.putExtra("id",mListItems.get(position));
                intent.putExtra("title",mListItems.get(position));
                startActivity(intent);
            }
        });
        return view;
    }

    private void init() {
        mListItems = new LinkedList<>();
        for (int i = 1; i <= monthcount; i++) {
            mListItems.add(i+"月");
        }
    }
}
