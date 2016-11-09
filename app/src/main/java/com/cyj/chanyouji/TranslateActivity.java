package com.cyj.chanyouji;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.TransItemAdapter;
import com.cyj.chanyouji.bean.Tabletrans;
import com.cyj.chanyouji.bean.Translate;
import com.cyj.chanyouji.translate.MD5;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TranslateActivity extends AppCompatActivity {
    private static final String[] left = {"自动检查", "中文", "粤语", "英语", "日语", "韩语", "西班牙语", "法语", "泰语", "阿拉伯语", "俄罗斯语", "葡萄牙语", "德语", "意大利语"};
    private static final String[] right = {"中文", "粤语", "英语", "日语", "韩语", "西班牙语", "法语", "泰语", "阿拉伯语", "俄罗斯语", "葡萄牙语", "德语", "意大利语"};
    private List<Map<String, String>> datasource;
    private List<Map<String, String>> datasource2;

    private static String[] leftE = {"auto", "zh", "yue", "en", "jp", "kor", "spa", "fra", "th", "ara", "ru", "pt", "de", "it"};
    private static String[] rightE = {"zh", "yue", "en", "jp", "kor", "spa", "fra", "th", "ara", "ru", "pt", "de", "it"};
    private Spinner left_spinner;
    private Spinner right_spinner;
    private ArrayAdapter<String> left_adapter;
    private ArrayAdapter<String> right_adapter;
    String ruquest ,  from  , to  ;

    private EditText edit;
    private TextView fanyi;

    private ListView mListView;
    // List<Translate.Trans_result>   data;
    List<Tabletrans> data;
    TransItemAdapter adapter;

    DbManager dbManager;
    DbManager.DaoConfig config;

    private RelativeLayout content;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        left_spinner = (Spinner) findViewById(R.id.left_spinner);
        right_spinner = (Spinner) findViewById(R.id.right_spinner);
        mListView = (ListView) findViewById(R.id.lv);
        content = (RelativeLayout) findViewById(R.id.content);
        pb = (ProgressBar) findViewById(R.id.pb);

        left_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, left);
        right_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, right);

        config = new DbManager.DaoConfig();
        dbManager = x.getDb(config);
        data = new ArrayList<>();
        adapter = new TransItemAdapter(this, data, dbManager, mListView);
        mListView.setAdapter(adapter);
        if (queryAll() != null) {
            data.addAll(queryAll());
            adapter.notifyDataSetChanged();
        }


        pb.setVisibility(View.GONE);
        left_adapter.setDropDownViewResource(R.layout.drop_down_item);
        right_adapter.setDropDownViewResource(R.layout.drop_down_item);

        left_spinner.setAdapter(left_adapter);
        right_spinner.setAdapter(right_adapter);

        edit = (EditText) findViewById(R.id.edit);
        fanyi = (TextView) findViewById(R.id.fanyi);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edit.getText().toString() != null && !edit.getText().toString().equals("")) {
                    fanyi.setTextColor(Color.parseColor("#ffffff"));
                    fanyi.setBackgroundResource(R.drawable.translate_input);
                } else {
                    fanyi.setTextColor(Color.parseColor("#999999"));
                    fanyi.setBackgroundResource(R.drawable.translate_normal);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        left_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = leftE[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        right_spinner.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = rightE[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fanyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String query = edit.getText().toString().trim();
                if(query == null || query.matches("\\s"))
                    return;

                content.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);

                getRequest() ;
                RequestParams requestParams = new RequestParams(ruquest);

                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Translate t = new Gson().fromJson(result, Translate.class);
                        String src = t.getTrans_result().get(0).getSrc();
                        String dst = t.getTrans_result().get(0).getDst();

                        insertDat(new Tabletrans(src, dst));
                        data.clear();
                        data.addAll(queryAll());
                        adapter.notifyDataSetChanged();
                        content.setVisibility(View.VISIBLE);
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
        });


    }

    public void back(View view) {
        finish();
    }

    public void insertDat(Tabletrans tabletrans) {
        try {

            dbManager.save(tabletrans);

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public List<Tabletrans> queryAll() {
        try {
            List<Tabletrans> list = dbManager.findAll(Tabletrans.class);
            List<Tabletrans> resList = new ArrayList<Tabletrans>();
            // Log.i("list" , "===="+list )  ;
            if (list!=null){

                for(int i = (list.size()-1);i>=0;i--)
                    resList.add(list.get(i));
                return resList;
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void intit() {
        datasource = new ArrayList<>();
        datasource2 = new ArrayList<>();
        for (int i = 0; i < left.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(left[i], leftE[i]);
            datasource.add(map);

        }

        for (int i = 0; i < right.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put(left[i], rightE[i]);
            datasource2.add(map);

        }


    }

    private   void   getRequest () {

        String query = edit.getText().toString();
        edit.setText("");
        String salt = String.valueOf(System.currentTimeMillis());
        String s = MD5.md5(JsonUrl.APP_ID + query + salt + JsonUrl.SECURITY_KEY);
        ruquest = "http://api.fanyi.baidu.com/api/trans/vip/translate?" +
                "q=" +
                query +
                "&from=" +
                from +
                "&to=" +
                to +
                "&appid=" +
                JsonUrl.APP_ID +
                "&salt=" +
                salt +
                "&sign=" +
                s;

    }
}
