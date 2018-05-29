package com.ahmadia.jamaat.elsagradocoran2.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.ahmadia.jamaat.elsagradocoran2.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by CrusherEAGLE on 9/25/2017.
 */

class CustomAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    int j = 120;
    private int emblem;

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.textrow, null);
        }

        TextView txtListChild =convertView
                .findViewById(R.id.surahText);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition > 1 && groupPosition < 5){
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();                }
        else return 0;
    }
/*    @Override
    public int getGroupTypeCount (){
        return 6;
    }

    public int getGroupType (int groupPosition){
        return getGroupType(_listDataHeader.indexOf(groupPosition));

    }*/

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

            if (groupPosition == 0)
            {

                    LayoutInflater infalInflater = (LayoutInflater) this._context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.image, null);
            }
            else
            if (groupPosition > 1 && groupPosition < 5) {
                    LayoutInflater infalInflater = (LayoutInflater) this._context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.group_items2, null);
            }
            ////////////////////hacked together code for showing the little expandable indicator.
            else{
                        LayoutInflater infalInflater = (LayoutInflater) this._context
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.group_items, null);
        }

        TextView lblListHeader = convertView
                .findViewById(R.id.heading);
        //lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public CustomAdapter(Context context, List<String> listDataHeader,
                         HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;

    }
}

