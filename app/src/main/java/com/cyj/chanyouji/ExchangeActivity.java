package com.cyj.chanyouji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeActivity extends AppCompatActivity {
    private GridView gv;
    private List<Map<String,Object>>data_list;
    private SimpleAdapter adapter;

    private String[] count = {"1","2","3","4","5","6","7","8","9",".","0","DEL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);

        gv = (GridView) findViewById(R.id.pull_gv);
        data_list = new ArrayList<>();
        getData();

        adapter = new SimpleAdapter(this,data_list,R.layout.exchange_gv,new String[]{"text"},new int[]{R.id.count});

        gv.setAdapter(adapter);


    }

    public List<Map<String, Object>> getData(){
        for(int i=0;i<count.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", count[i]);
            data_list.add(map);
        }
        return data_list;
    }

}
