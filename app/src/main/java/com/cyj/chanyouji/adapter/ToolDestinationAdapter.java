package com.cyj.chanyouji.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.cyj.chanyouji.R;
import com.cyj.chanyouji.bean.ToolDestination;
import com.cyj.chanyouji.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandy on 2016/11/3.
 */
public class ToolDestinationAdapter implements ExpandableListAdapter {
    private Context context;
    private List<ToolDestination.Destination> list;
    private LayoutInflater inflater;

    private String type;
    private List<ToolDestination.Destination.Destinations> out;

    public ToolDestinationAdapter(Context context, List<ToolDestination.Destination> list, String type) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);

        this.type = type;
        if(type.equals("out"))
        {
            initOut();
        }
        else
            initIn();
    }

    private void initOut() {

        out = new ArrayList<ToolDestination.Destination.Destinations>();
        for(int i=0;i<list.size();i++)
        {
            if(!list.get(i).getCategory().equals("99")&&!list.get(i).getCategory().equals("999"))
            {
                out.addAll(list.get(i).getDestinations());
            }
        }
    }
    private void initIn() {

        out = new ArrayList<ToolDestination.Destination.Destinations>();
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getCategory().equals("99")||list.get(i).getCategory().equals("999"))
            {
                out.addAll(list.get(i).getDestinations());
            }
        }
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return out.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return out.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return out.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return out.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ToolDestination.Destination.Destinations d = out.get(groupPosition);
        GroupHoler holder = null;
        if(convertView == null)
        {
            holder = new GroupHoler();
            convertView = inflater.inflate(R.layout.item_group_tool,parent,false);
            holder.name_zh_cn = (TextView) convertView.findViewById(R.id.name_zh_cn);
            convertView.setTag(holder);
        }
        else
            holder = (GroupHoler) convertView.getTag();
        holder.name_zh_cn.setText(d.getName_zh_cn());

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.item_child_item_tool,parent,false);
        }
        TextView tv = (TextView) convertView;
        tv.setText(out.get(groupPosition).getChildren().get(childPosition).getName_zh_cn());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToolUtils.setDestination(context,out.get(groupPosition).getChildren().get(childPosition).getName_zh_cn());
                ToolUtils.setDestinationId(context,out.get(groupPosition).getChildren().get(childPosition).getId());
                ((Activity)context).finish();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    class GroupHoler{
        TextView name_zh_cn;
    }
}
