package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class WelcomActivity extends AppCompatActivity {

    ImageView image;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 2) {
                change();
            }
            if (msg.what == 1) {

                Intent intent = new Intent(WelcomActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcom);

        image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.drawable.a1);
        handler.sendEmptyMessageDelayed(2,1000) ;

    }

    private void change() {
        List<Integer> data = new ArrayList<>();
        data.add(R.drawable.a2);
        data.add(R.drawable.a3);
        data.add(R.drawable.a4);

        int a = (int) (Math.random() * 3);
        image.setImageResource(data.get(a));

        handler.sendEmptyMessageDelayed(1, 2000);
    }
}
