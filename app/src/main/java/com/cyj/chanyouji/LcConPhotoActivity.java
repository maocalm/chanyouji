package com.cyj.chanyouji;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.cyj.chanyouji.adapter.LxConPhotAdapter;
import com.cyj.chanyouji.bean.LxConPhot;
import com.cyj.chanyouji.url.JsonUrl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LcConPhotoActivity extends AppCompatActivity {

    private RecyclerView  gradview ;
    private LxConPhotAdapter adapter ;
    private  List<LxConPhot> data  ;
    private SwipeRefreshLayout  swip;
    private String ids ;
    private  int page =1 ;
    String  url;
    StaggeredGridLayoutManager manager ;
    //LinearLayoutManager   manager ;
    private  boolean isLoading = false ;
    private int[] lastPositions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lc_con_photo);
        ids= getIntent().getStringExtra("id") ;
        Log.i("id" , "======ids======"+ids)  ;
         url = JsonUrl.SUB_LX_THREE_PHOTO_START +ids + JsonUrl.SUB_LX_THREE_PHOTO_END;
         // url+page ;

        gradview = (RecyclerView) findViewById(R.id.gridview);
        swip= (SwipeRefreshLayout) findViewById(R.id.swip);

        data = new ArrayList<>() ;
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        gradview.setLayoutManager(manager);
        loadData(url ,page);

        gradview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (lastPositions == null) {
                    lastPositions = new int[manager.getSpanCount()];
                }
                manager.findLastVisibleItemPositions(lastPositions);
               int lastPos = findMax(lastPositions);
                if (lastPos+1==adapter.getItemCount()){
                    if (swip.isRefreshing()) {
                        return;
                    }
                    if (!isLoading){
                        isLoading = true;
                        page++ ;
                        loadData(url,page);
                    }
                }
            }
        });


    }


    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public  void loadData(String url , int page ) {
        RequestParams  params= new RequestParams(url +page) ;
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("log" , "result"+result) ;
                Type type  =  new TypeToken<List<LxConPhot>>(){}.getType() ;
                List<LxConPhot>  l = new Gson().fromJson( result, type ) ;
                data.addAll(l) ;
                Log.i("log" , "resultsize"+data.size()) ;
                adapter = new LxConPhotAdapter(LcConPhotoActivity.this  , data ) ;
                gradview.setAdapter(adapter);
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
        })  ;
    }
}
