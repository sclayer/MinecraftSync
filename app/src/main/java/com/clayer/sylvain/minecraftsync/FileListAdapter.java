package com.clayer.sylvain.minecraftsync;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sylvain on 12/11/15.
 */
public class FileListAdapter extends BaseAdapter {

    private List<String> files;

    private LayoutInflater myInflater;


    FileListAdapter(Context context,List<String> files){
        this.files=files;
        myInflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return files.size();
    }

    @Override
    public Object getItem(int position) {
        return files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class ViewHolder{
        TextView txt01;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if (convertView == null){
            convertView=myInflater.inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            holder.txt01 = (TextView) convertView.findViewById(R.id.txtName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt01.setText(files.get(position));

        return convertView;
    }
}
