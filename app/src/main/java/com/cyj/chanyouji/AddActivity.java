package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cyj.chanyouji.db.DBUtils;
import com.cyj.chanyouji.db.DataBaseAdapter;
import com.cyj.chanyouji.db.RecordTable;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    private TextView total_riyuan,total_meiyuan,total_ouyuan,total_renminbi,clear;
    private ImageView add;

    private ListView lv;

    private List<RecordTable> list;
    private DataBaseAdapter adapter;
    private DbManager dbManager;
    private DbManager.DaoConfig config;

    private String ouyuan = "0.0";
    private String meiyaun = "0.0";
    private String riyuan = "0.0";
    private String renminbi = "0.0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        total_riyuan = (TextView) findViewById(R.id.total_riyuan);
        total_meiyuan = (TextView) findViewById(R.id.total_meiyuan);
        total_ouyuan = (TextView) findViewById(R.id.total_ouyuan);
        total_renminbi = (TextView) findViewById(R.id.total_renminbi);

        clear = (TextView) findViewById(R.id.clear);
        add = (ImageView) findViewById(R.id.add);
        lv = (ListView) findViewById(R.id.miangxi);

        config = DBUtils.getDaofig(this);
        dbManager = x.getDb(config);
        list = new ArrayList<>();
        if(queryAll()!=null){
            list.addAll(queryAll());
        }

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getHuobi().equals("欧元")){
                ouyuan = list.get(i).getMoney();
            }else if(list.get(i).getHuobi().equals("日元")){
                riyuan = list.get(i).getMoney();
            }else if (list.get(i).getHuobi().equals("美元")){
                meiyaun = list.get(i).getMoney();
            }else if (list.get(i).getHuobi().equals("人民币")){
                renminbi = list.get(i).getMoney();
            }
        }
        total_riyuan.setText(riyuan+"日元");
        total_meiyuan.setText(meiyaun+"美元");
        total_ouyuan.setText(ouyuan+"欧元");
        total_renminbi.setText(renminbi+"人民币");

        adapter = new DataBaseAdapter(this,list);
        lv.setAdapter(adapter);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll(list);
                riyuan = "0";
                meiyaun = "0";
                ouyuan = "0";
                renminbi = "0";
                total_riyuan.setText(riyuan+"日元");
                total_meiyuan.setText(meiyaun+"美元");
                total_ouyuan.setText(ouyuan+"欧元");
                total_renminbi.setText(renminbi+"人民币");
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,AddMingxiActivity.class);
                startActivity(intent);
            }
        });

    }

    public synchronized boolean deleteAll(Object entity) {
        try {
            dbManager.delete(entity);
        } catch (Exception e) {
            if (e != null)
                e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<RecordTable> queryAll() {
        try {
            List<RecordTable> datas = dbManager.findAll(RecordTable.class);
            return datas;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
