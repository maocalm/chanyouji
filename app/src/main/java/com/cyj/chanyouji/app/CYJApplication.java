package com.cyj.chanyouji.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import org.xutils.x;

/**
 * Created by Biu丶 on 2016/10/31.
 */
public class CYJApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        x.Ext.init(this);
        x.Ext.setDebug(true);
    }


}
