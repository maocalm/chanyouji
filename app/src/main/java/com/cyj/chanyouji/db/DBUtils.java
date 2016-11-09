package com.cyj.chanyouji.db;

import android.content.Context;

import org.xutils.DbManager;

/**
 * Created by mandy on 2016/11/5.
 */
public class DBUtils {
    static DbManager.DaoConfig config;
    public static DbManager.DaoConfig getDaofig(Context context){
        config = new DbManager.DaoConfig();
        config.setDbName("country.db")
                .setDbDir(context.getExternalCacheDir())
                .setDbVersion(1)
                .setAllowTransaction(true)
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
        return config;
    }
}
