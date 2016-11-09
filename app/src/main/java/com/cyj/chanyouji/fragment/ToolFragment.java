package com.cyj.chanyouji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cyj.chanyouji.AddActivity;
import com.cyj.chanyouji.ExchangeActivity;
import com.cyj.chanyouji.MapActivity;
import com.cyj.chanyouji.R;
import com.cyj.chanyouji.SelectActivity;
import com.cyj.chanyouji.TranslateActivity;
import com.cyj.chanyouji.bean.ToolCountry;
import com.cyj.chanyouji.url.JsonUrl;
import com.cyj.chanyouji.utils.ToolUtils;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by mandy on 2016/10/31.
 */
public class ToolFragment extends Fragment {

    private RelativeLayout dest, rl;
    private LinearLayout open;
    private TextView startTemp, endTemp, time, translate, account, map, money, openTool,country;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pager_tool,container,false);
        initView(view);

        String destinationId = ToolUtils.getDestinationId(getActivity());
        if(destinationId == null)
            dest.setVisibility(View.GONE);
        else {
            open.setVisibility(View.GONE);
            getData();
        }
        openTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                startActivityForResult(intent,101);
            }
        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                startActivityForResult(intent,101);
            }
        });


        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TranslateActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExchangeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101)
        {
            getData();
        }
    }
    private void getData()
    {
        String id = ToolUtils.getDestinationId(getActivity());
        if(id!=null)
        {
            if(open.getVisibility() == View.VISIBLE)
                open.setVisibility(View.GONE);
            if(dest.getVisibility() == View.GONE)
                dest.setVisibility(View.VISIBLE);
            RequestParams params = new RequestParams(JsonUrl.TEMP+id+".json");
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    ToolCountry tc = new Gson().fromJson(result,ToolCountry.class);
                    country.setText(ToolUtils.getDestination(getActivity()));
                    startTemp.setText(tc.getTemp_min());
                    endTemp.setText(tc.getTemp_max());
                    time.setText("当地时间："+tc.getCurrent_time());
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

    private void initView(View v) {
        dest = (RelativeLayout) v.findViewById(R.id.dest);
        open = (LinearLayout) v.findViewById(R.id.open);
        rl = (RelativeLayout) v.findViewById(R.id.rl);
        startTemp = (TextView) v.findViewById(R.id.start_temp);
        endTemp = (TextView) v.findViewById(R.id.end_temp);
        time = (TextView) v.findViewById(R.id.time);
        translate = (TextView) v.findViewById(R.id.translate);
        account = (TextView) v.findViewById(R.id.account);
        map = (TextView) v.findViewById(R.id.map);
        money = (TextView) v.findViewById(R.id.money);
        openTool = (TextView) v.findViewById(R.id.openTool);
        country = (TextView) v.findViewById(R.id.country);
    }
}
