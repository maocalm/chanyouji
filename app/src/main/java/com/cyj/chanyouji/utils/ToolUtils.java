package com.cyj.chanyouji.utils;

import android.content.Context;

/**
 * Created by Biu丶 on 2016/11/5.
 */
public class ToolUtils {

    public static  String getDestination(Context context)
    {
        String dest = context.getSharedPreferences("tool", Context.MODE_PRIVATE)
                    .getString("dest",null);
        return dest;
    }
    public static void setDestination(Context context,String dest){
        context.getSharedPreferences("tool", Context.MODE_PRIVATE)
        .edit()
        .putString("dest",dest).commit();
    }
    public static  String getDestinationId(Context context)
    {
        String dest = context.getSharedPreferences("tool", Context.MODE_PRIVATE)
                .getString("destId",null);
        return dest;
    }
    public static void setDestinationId(Context context,String dest){
        context.getSharedPreferences("tool", Context.MODE_PRIVATE)
                .edit()
                .putString("destId",dest).commit();
    }
}
