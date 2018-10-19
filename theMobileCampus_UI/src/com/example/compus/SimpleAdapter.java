package com.example.compus;

import java.util.List;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleAdapter extends BaseAdapter{
	 private List<message> mData;
	 private Context mContext;
	 private String sid;
	 private String tid;
	 public SimpleAdapter(){}
	    public SimpleAdapter(Context mContext,List<message> mData,String sid,String tid) {
	        this.mData = mData;
	        this.mContext=mContext;
	        this.sid=sid;
	        this.tid=tid;

	    }

	    @Override
	    public int getCount() {
	        return mData.size();
	    }

	    @Override
	    public Object getItem(int position) {
	        return null;
	    }

	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item,parent,false);
	        ImageView img_icon = (ImageView) convertView.findViewById(R.id.ItemPic);
	        TextView txt_aName = (TextView) convertView.findViewById(R.id.ItemTitle);
	        TextView txt_aSpeak = (TextView) convertView.findViewById(R.id.ItemText);
	        img_icon.setBackgroundResource(mData.get(position).getaIcon());
	        txt_aName.setText(mData.get(position).getaName());
	        txt_aSpeak.setText(mData.get(position).getaSpeak());
	        convertView.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	 Intent it = new Intent(mContext,concrete_message.class);
	            	 Bundle bd = new Bundle();
	                 bd.putString("SID",sid);
	                 bd.putString("TID",tid);  
	                 it.putExtras(bd);
	                 mContext.startActivity(it);   
    
	            }
	            });
	        return convertView;
	    }
		public String getSid() {
			return sid;
		}
		public void setSid(String sid) {
			this.sid = sid;
		}
		public String getTid() {
			return tid;
		}
		public void setTid(String tid) {
			this.tid = tid;
		}

}
