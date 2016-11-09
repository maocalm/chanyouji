package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.cyj.chanyouji.db.DBUtils;
import com.cyj.chanyouji.db.RecordTable;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

public class AddMingxiActivity extends AppCompatActivity {
    private Spinner sp_danwei;
    private ImageView save,back;
    private EditText edit,edit_xiaofei;
    private RadioGroup rg;
    private String select;
    private RadioButton rb;
    private String tiaojian;
    private DbManager dbManager;
    private DbManager.DaoConfig config;

    private static final String[] danwei = {"日元","人民币","美元","欧元"};
    private ArrayAdapter<String> dan_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mingxi);
        sp_danwei = (Spinner) findViewById(R.id.sp_danwei);
        save = (ImageView) findViewById(R.id.add);
        edit = (EditText) findViewById(R.id.edit);
        edit_xiaofei = (EditText) findViewById(R.id.edit_xiaofei);
        back = (ImageView) findViewById(R.id.back);
        rg = (RadioGroup) findViewById(R.id.rg);
        select = (String) sp_danwei.getSelectedItem();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        config = DBUtils.getDaofig(this);
        dbManager = x.getDb(config);

        setRg();
        dan_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,danwei);
        dan_adapter.setDropDownViewResource(R.layout.drop_down_item);

        sp_danwei.setAdapter(dan_adapter);

        sp_danwei.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = (String) sp_danwei.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = edit.getText().toString().trim();
                String mingxi = edit_xiaofei.getText().toString().trim();
                String huobi = select;
                String tiaojian1 = tiaojian;
                insertData(new RecordTable(money,mingxi,huobi,tiaojian1));
                Intent intent = new Intent(AddMingxiActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }

    public void insertData(RecordTable record) {
        try {
            dbManager.save(record);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void setRg() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectBtn();
            }
        });
    }

    private void selectBtn() {
        rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
        tiaojian = rb.getText().toString();
        Log.i("tag","tj"+tiaojian);
    }
}
