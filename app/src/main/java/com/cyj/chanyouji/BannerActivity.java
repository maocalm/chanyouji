package com.cyj.chanyouji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cyj.chanyouji.adapter.AticleAdapter;
import com.cyj.chanyouji.bean.Article;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class BannerActivity extends AppCompatActivity {

    private ProgressBar pb;
    private ListView mListView;
    private AticleAdapter mAticleAdapter;
    private List<Article.Section> mSection; // = new ArrayList<>();
    private int page ;

    ImageView  pic;
    TextView  name;
    TextView  title  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        page  = getIntent().getIntExtra("id" ,625) ;

        pb = (ProgressBar) findViewById(R.id.pb);
        mListView = (ListView) findViewById(R.id.lv);
        getHead();

        RequestParams params = new RequestParams(JsonUrl.BANNER_TWO_START + page + JsonUrl.BANNER_TWO_END);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Article artical = new Gson().fromJson(result, Article.class);
                mSection = artical.getArticle_sections();
                mAticleAdapter = new AticleAdapter(mSection,BannerActivity.this);
                mListView.setAdapter(mAticleAdapter);

                x.image().bind( pic ,artical.getImage_url() );
                title.setText(artical.getTitle());
                name.setText(artical.getName());

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

    private  void getHead() {

        View  view  =  getLayoutInflater().inflate(R.layout.artical_lv_head ,null) ;
           pic  = (ImageView) view.findViewById(R.id.image_url);
          name=  (TextView)view.findViewById(R.id.name);
          title=  (TextView)view.findViewById(R.id.title);
          mListView.addHeaderView(view);

    }

    public void back(View view) {
        finish();
    }
}
