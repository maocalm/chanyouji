package com.cyj.chanyouji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.TripDetail;

import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Biu丶 on 2016/11/1.
 */
public class TripDetailTestAdapter extends BaseAdapter {

    private TripDetail td;
    private LayoutInflater inflater;

    private final int DAY = 0;
    private final int POSITION = 1;
    private final int CONTENT = 2;

    private List<TripDetail.Trip_Day> days;
    private int count;
    private List<Integer> types;
    private List<Integer> day;
    private List<Integer> position;
    private List<Integer> content;
    private Map<String,TripDetail.Notes_Likes_Comment> comments;

    private Map<Integer,List<Integer>> slidIndex;
    private List<Integer> lvPosition;

    public TripDetailTestAdapter(Context context, TripDetail td) {
        this.td = td;
        inflater = LayoutInflater.from(context);
        days = td.getTrip_days();
        initCount();
        initComments();
    }

    private void initComments() {
        comments = new HashMap<String,TripDetail.Notes_Likes_Comment>();
        List<TripDetail.Notes_Likes_Comment> list = td.getNotes_likes_comments();
        for(TripDetail.Notes_Likes_Comment nlc:list)
        {
            comments.put(nlc.getId(),nlc);
        }
        //Log.d("test","comments..size:"+comments.size()+"");
    }


    public Map<Integer,List<Integer>> getSlidIndex()
    {
        return slidIndex;
    }
    public List<Integer> getLvPosition(){ return lvPosition;}

    private void initCount() {
        count = 0;

        slidIndex = new HashMap<Integer,List<Integer>>();

        types = new ArrayList<Integer>();
        day = new ArrayList<Integer>();
        position = new ArrayList<Integer>();
        content = new ArrayList<Integer>();
        count+=days.size();

        for(int i=0;i<days.size();i++)
        {
            List<Integer> l = new ArrayList<Integer>();
            slidIndex.put(i,l);

            types.add(DAY);
            day.add(i);
            position.add(-1);
            content.add(-1);
            List<TripDetail.Trip_Day.Node> nodes = days.get(i).getNodes();
            for(int j=0;j<nodes.size();j++)
            {
                if(nodes.get(j).getEntry_name()!=null && !nodes.get(j).getEntry_name().equals(""))
                {
                    slidIndex.get(i).add(j);
                    types.add(POSITION);
                    day.add(i);
                    position.add(j);
                    content.add(-1);
                    count++;
                }
                int noteCount = nodes.get(j).getNotes().size();
                for(int k = 0;k<noteCount;k++) {
                    types.add(CONTENT);
                    day.add(i);
                    position.add(j);
                    content.add(k);
                }
                count+=noteCount;
            }

        }

        lvPosition = new ArrayList<Integer>();
        for(int i=0;i<count;i++)
        {
            if(types.get(i) == DAY||types.get(i) == POSITION)
                lvPosition.add(i+1);
        }
        lvPosition.add(count+1);
        lvPosition.add(count+1);

       // Log.d("test","slid..size:"+slidIndex.size()+",slid:"+slidIndex);
//        Log.d("test","d...:"+day);
//        Log.d("test","p...:"+position);
//        Log.d("test","c...:"+content);
//        Log.d("test","count:"+count+",day..size:"+day.size());

    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {

        Object result = null;

        int type = types.get(position);
        int day = this.day.get(position);
        int pos = this.position.get(position);
        int con = content.get(position);
        switch (type)
        {
            case DAY:
                result = days.get(day);
                break;
            case POSITION:
                result = days.get(day).getNodes().get(pos);
                break;
            case CONTENT:
                result = days.get(day).getNodes().get(pos).getNotes().get(con);
                break;
        }

        return result;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);
        int dayIndex = day.get(position);
        int posIndex = this.position.get(position);
        int noteIndex = content.get(position);

        switch (type) {
            case DAY:
                DayHolder holder = null;
                if (convertView == null) {
                    holder = new DayHolder();
                    convertView = inflater.inflate(R.layout.group_tripdetail, parent, false);
                    holder.day = (TextView) convertView.findViewById(R.id.day);
                    holder.trip_date = (TextView) convertView.findViewById(R.id.trip_date);
                    holder.weekDay = (TextView) convertView.findViewById(R.id.weekday);
                    convertView.setTag(holder);
                } else
                    holder = (DayHolder) convertView.getTag();

                TripDetail.Trip_Day trip_day = days.get(dayIndex);
                holder.day.setText("DAY" + trip_day.getDay());
                holder.trip_date.setText(getFormatDate(trip_day.getTrip_date()));
                holder.weekDay.setText(getWeekDay(trip_day.getTrip_date()));
                break;
            case POSITION:
                PositionHolder pholder = null;
                if (convertView == null) {
                    pholder = new PositionHolder();
                    convertView = inflater.inflate(R.layout.header_tripdetail_child_lv, parent, false);
                    pholder.entry_name = (TextView) convertView.findViewById(R.id.entry_name);
                    pholder.price_amount = (TextView) convertView.findViewById(R.id.price_amount);
                    pholder.comment = (TextView) convertView.findViewById(R.id.comment);
                    pholder.score = (RatingBar) convertView.findViewById(R.id.score);
                    convertView.setTag(pholder);
                } else
                    pholder = (PositionHolder) convertView.getTag();

                TripDetail.Trip_Day.Node n = days.get(dayIndex).getNodes().get(posIndex);
                String entryName = n.getEntry_name();
                String comm = n.getComment();
                String sco = n.getScore();

                if(entryName!=null&&!entryName.equals("")) {
                    pholder.entry_name.setText(entryName);
                    pholder.entry_name.setVisibility(View.VISIBLE);
                }else
                    pholder.entry_name.setVisibility(View.GONE);
                if(comm!=null&&!comm.equals("")) {
                    pholder.comment.setText(comm);
                    pholder.comment.setVisibility(View.VISIBLE);
                }else
                    pholder.comment.setVisibility(View.GONE);
                if(sco!=null&&!sco.equals("0")&&!sco.equals(""))
                {
                    int s = Integer.parseInt(sco);
                    pholder.score.setRating(s);
                    pholder.score.setVisibility(View.VISIBLE);
                }
                else
                    pholder.score.setVisibility(View.GONE);
                if(n.getMemo()!=null&&n.getMemo().getPrice_amount()!=null&&!n.getMemo().getPrice_amount().equals(""))
                {
                    pholder.price_amount.setText("单间价格："+n.getMemo().getPrice_amount()+"元");
                    pholder.price_amount.setVisibility(View.VISIBLE);
                }
                else
                    pholder.price_amount.setVisibility(View.GONE);

                break;
            case CONTENT:
                ContentHolder cholder = null;
                if (convertView == null) {
                    cholder = new ContentHolder();
                    convertView = inflater.inflate(R.layout.item_tripdetail_child_lv, parent, false);
                    cholder.url = (ImageView) convertView.findViewById(R.id.url);
                    cholder.description = (TextView) convertView.findViewById(R.id.description);
                    cholder.entry_name = (TextView) convertView.findViewById(R.id.entry_name);
                    cholder.likes_count = (TextView) convertView.findViewById(R.id.likes_count);
                    cholder.comments_count = (TextView) convertView.findViewById(R.id.comments_count);
                    cholder.poi = (LinearLayout) convertView.findViewById(R.id.poi);
                    convertView.setTag(cholder);
                } else
                    cholder = (ContentHolder) convertView.getTag();

               // Log.d("test","dayindex:"+dayIndex+",posindex:"+posIndex+"noteIndex:"+noteIndex);
                TripDetail.Trip_Day.Node.Note note = days.get(dayIndex).getNodes().get(posIndex).getNotes().get(noteIndex);

                if (note.getPhoto()!=null&&note.getPhoto().getUrl()!=null&&!note.getPhoto().getUrl().equals("")) {
                    cholder.url.setVisibility(View.VISIBLE);
                    x.image().bind(cholder.url,note.getPhoto().getUrl());
                }
                else
                    cholder.url.setVisibility(View.GONE);
                String des = note.getDescription();
                String entryName2 = days.get(dayIndex).getNodes().get(posIndex).getEntry_name();
                if(des!=null&&!des.equals("")) {
                    cholder.description.setText(des);
                    cholder.description.setVisibility(View.VISIBLE);
                }else
                    cholder.description.setVisibility(View.GONE);
                if(entryName2!=null&&!entryName2.equals("")){
                    cholder.entry_name.setText(entryName2);
                    cholder.poi.setVisibility(View.VISIBLE);
                }
                else {
                    cholder.poi.setVisibility(View.GONE);
                }
                //Log.d("test","note--id"+note.getId());
                TripDetail.Notes_Likes_Comment nlc = comments.get(note.getId());
                if(nlc != null)
                {
                    if(nlc.getLikes_count()==null||nlc.getLikes_count().equals("0"))
                        cholder.likes_count.setText("");
                    else
                        cholder.likes_count.setText(nlc.getLikes_count());
                    if(nlc.getComments_count()==null || nlc.getComments_count().equals("0"))
                        cholder.comments_count.setText("");
                    else
                        cholder.comments_count.setText(nlc.getComments_count());
                }
                else
                {
                    cholder.likes_count.setText("");
                    cholder.comments_count.setText("");
                }
                break;
        }

        return convertView;
    }

    private String getWeekDay(String trip_date) {

        String weekday = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(trip_date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int week = calendar.get(Calendar.DAY_OF_WEEK);
            switch (week)
            {
                case 1:
                    weekday = "周日";
                    break;
                case 2:
                    weekday = "周一";
                    break;
                case 3:
                    weekday = "周二";
                    break;
                case 4:
                    weekday = "周三";
                    break;
                case 5:
                    weekday = "周四";
                    break;
                case 6:
                    weekday = "周五";
                    break;
                case 7:
                    weekday = "周六";
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weekday;
    }

    private String getFormatDate(String trip_date)
    {
        String[] ds = trip_date.split("-");
        StringBuilder builder = new StringBuilder();
        builder.append(ds[0]+"年"+ds[1]+"月"+ds[2]+"日");
        return builder.toString();
    }

    class DayHolder{
        TextView day,trip_date,weekDay;
    }
    class PositionHolder{
        TextView entry_name,price_amount,comment;
        RatingBar score;
    }
    class ContentHolder{
        ImageView url;
        TextView description,entry_name,likes_count,comments_count;
        LinearLayout poi;
    }
}
