package com.cyj.chanyouji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.model.LatLng;
import com.cyj.chanyouji.bean.Map;
import com.cyj.chanyouji.url.JsonUrl;
import com.cyj.chanyouji.utils.ToolUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class MapActivity extends AppCompatActivity {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private InfoWindow mInfoWindow;
    private int height;
    private Marker mMarker;

    private TextView backName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        backName = (TextView) findViewById(R.id.backname);
        backName.setText(ToolUtils.getDestination(this)+"旅行地图");

        String id = ToolUtils.getDestinationId(this);
        String path = JsonUrl.MAP_START+id+JsonUrl.MAP_END;
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                List<Map> list = new Gson().fromJson(result,new TypeToken<List<Map>>(){}.getType());
                BitmapDescriptor[] icons = {
                        BitmapDescriptorFactory.fromResource(R.drawable.scenery_pingreen),
                        BitmapDescriptorFactory.fromResource(R.drawable.food_pingreen),
                        BitmapDescriptorFactory.fromResource(R.drawable.hotel_pingreen),
                        BitmapDescriptorFactory.fromResource(R.drawable.shopping_pingreen),
                        BitmapDescriptorFactory.fromResource(R.drawable.traffic_pingreen)
                };
                BitmapDescriptor icon = icons[0];
                height = icon.getBitmap().getHeight();

                int size = list.size();
                for(int i=0;i<size;i++)
                {
                    double lat = Double.parseDouble(list.get(i).getLat());
                    double lng = Double.parseDouble(list.get(i).getLng());
                    LatLng point = new LatLng(lat, lng);

                    String type = list.get(i).getAttraction_type();
                    if(type == null) {
                        icon = icons[0];
                    }
                    else if(type.equals("restaurant"))
                    {
                        icon = icons[1];
                    }else if(type.equals("shopping"))
                    {
                        icon = icons[3];
                    }
                    else if(type.equals("sight"))
                    {
                        icon = icons[0];
                    }
                    else if(type.equals("transport"))
                    {
                        icon = icons[4];
                    }
                    else if(type.equals("hotel"))
                    {
                        icon = icons[2];
                    }
                    else
                    {
                        icon = icons[0];
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("id",list.get(i).getId());
                    OverlayOptions options = new MarkerOptions().icon(icon).position(point).title(list.get(i).getName()).extraInfo(bundle);
                    mBaiduMap.addOverlay(options);
                    if(i == 0)
                    {
                        MapStatus mMapStatus = new MapStatus.Builder()
                                .target(point)
                                .zoom(12)
                                .build();
                        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

                        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                        //改变地图状态
                        mBaiduMap.setMapStatus(mMapStatusUpdate);
                    }
                }
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

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showLocation(marker);
                mMarker = marker;
                return false;
            }
        });

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mBaiduMap.hideInfoWindow();
                mMarker = null;
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });

        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {

            float start = 0;
            boolean change = false;
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
               start = mapStatus.zoom;
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                if(!change) {
                    if (start != mapStatus.zoom) {
                        mBaiduMap.hideInfoWindow();
                        change = true;
                    }
                }
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                if(change)
                    showLocation(mMarker);
                start = 0;
                change = false;
            }
        });

    }
    private void showLocation(final Marker marker) {  //显示气泡
        // 创建InfoWindow展示的view

        if(marker == null)return;

        LatLng pt = null;

        View view = LayoutInflater.from(this).inflate(R.layout.map_item, null); //自定义气泡形状
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(marker.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this,LxConActivity.class);
                intent.putExtra("id",marker.getExtraInfo().getString("id"));
                //Log.d("test","map..click..id:"+marker.getExtraInfo().getString("id"));
                startActivity(intent);
            }
        });


        Projection pj = mBaiduMap.getProjection();
        android.graphics.Point point = pj.toScreenLocation(marker.getPosition());
        //Log.d("test","point:"+pj.toScreenLocation(marker.getPosition()).toString());
        //Log.d("test","height:"+height);
        point.y-=height;
        pt = pj.fromScreenLocation(point);

        //pt = new LatLng(latitude + 0.0004, longitude + 0.00005);

        // 创建InfoWindow
        mInfoWindow = new InfoWindow(view,pt,0);
        mBaiduMap.showInfoWindow(mInfoWindow); //显示气泡

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    public void back(View view) {
        finish();
    }
}
