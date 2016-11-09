package com.cyj.chanyouji.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Biu丶 on 2016/11/5.
 */
public class GLRecordUtil {

    public static final String RECORD = "glrecord";
    public static final String ID = "glrecordId";

    public static void setRecord(Context context, List<String> list, List<String> idList){

        SharedPreferences share = context.getSharedPreferences(RECORD,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();

        SharedPreferences share2 = context.getSharedPreferences(ID,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = share2.edit();
        for(int i=0;i<list.size();i++)
        {
            editor.putString(i+"",list.get(i));
            editor2.putString(i+"",idList.get(i));
        }
        editor.commit();
        editor2.commit();

    }
    public static List<String> getRecord(Context context, String position){
        List<String> list = new ArrayList<String>();
        SharedPreferences share = context.getSharedPreferences(position,Context.MODE_PRIVATE);
        Map<String,String> map = (Map<String, String>) share.getAll();
        for(int i=0;i<map.size();i++)
        {
            list.add(map.get(i+""));
        }
        //Log.d("test","map..list:"+list);
        return list;
    }

    public static List<String> getDescRecord(Context context, String position)
    {
        List<String> list = new ArrayList<String>();
        SharedPreferences share = context.getSharedPreferences(position,Context.MODE_PRIVATE);
        Map<String,String> map = (Map<String, String>) share.getAll();
        for(int i=map.size()-1;i>=0;i--)
        {
            list.add(map.get(i+""));
        }
        //Log.d("test","Descmap..list:"+list);
        return list;
    }
}
