package com.example.compus;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.bean.InformationName;
import com.example.bean.Students;
import com.example.bean.Teachers;
import com.google.gson.Gson;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class send_message extends Fragment{
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	private LinearLayout allLeanerlayout;
	private String idarray[]=new String[20];
	private String hql,job;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   	
		View view = inflater.inflate(R.layout.sendmessage,container, false); 
		allLeanerlayout=(LinearLayout) view.findViewById(R.id.all_ll);
        Bundle bd=getActivity().getIntent().getExtras();
    	job=bd.getString("job");
    	String userJson=getActivity().getIntent().getStringExtra("loginUser");
    	hql = null;
    	if(job.equals("student")){
    		Students student=new Gson().fromJson(userJson,Students.class);
    		hql="SID="+student.getSID();
    		}
    	else if(job.equals("teacher")){
    		Teachers teacher=new Gson().fromJson(userJson,Teachers.class);
    		hql="TID="+teacher.getTID();
    		}
    	try{
        	HttpClient client = new DefaultHttpClient();
		    HttpPost httpPost = new HttpPost(http_url+"showAllInformation?"+hql);
		    HttpResponse httpResponse = client.execute(httpPost);
	        HttpEntity entity = httpResponse.getEntity();
	        StringBuilder builder = new StringBuilder();  
	        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));  
	          
	        String s = null;  
	        while ((s = bufferedReader.readLine()) != null)  
	        {  
	            builder.append(s);  
	        }   
	        JSONObject json = new JSONObject(builder.toString());
	        
	        JSONArray messageObject = JSONArray.fromObject(json.get("information").toString());
            
	        //消息列表数据
	        List<HashMap<String,String>> allmessage= (List<HashMap<String,String>>) JSONArray.toCollection(messageObject,HashMap.class);
	        InformationName cme=null;
	        for(int i=0;i<allmessage.size();i++)  
            {  
	        	cme=new InformationName();
		    	Map map=(Map)allmessage.get(i);
		    	cme.setBody((String) map.get("body"));
		    	cme.setSender((String) map.get("sender"));
		    	cme.setSID((String) map.get("SID"));
		    	cme.setSname((String) map.get("Sname"));
		    	cme.setTID((String) map.get("TID"));
		    	cme.setTime((String) map.get("time"));
		    	cme.setTname((String) map.get("Tname"));
		    	if(job.equals("student"))
		    	{
		    		idarray[i]=cme.getTID();
			    	if(cme.getSender().equals("T"))
			    	{
			    		setMessageView(cme.getTname(),cme.getTname()+":"+cme.getBody(),R.drawable.steacher,i,"student",cme.getSID());
			    	}
			    	else if(cme.getSender().equals("S"))
			    	{
			    		setMessageView(cme.getTname(),"我:"+cme.getBody(),R.drawable.steacher,i,"student",cme.getSID());
			    	}
		    	}
		    	else
		    	{
		    		idarray[i]=cme.getSID();
		    		if(cme.getSender().equals("T"))
			    	{
		    			setMessageView(cme.getSname(),"我:"+cme.getBody(),R.drawable.student,i,"teacher",cme.getTID());
			    	}
			    	else if(cme.getSender().equals("S"))
			    	{
			    		setMessageView(cme.getSname(),cme.getSname()+":"+cme.getBody(),R.drawable.student,i,"teacher",cme.getTID());
			    	}
		    	}
            }  
           
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    	
        return view; 
	}
	 
	private void setMessageView(String name,String recentMessage,int icon,int buttonTag,String st_te,String pid){
		
		LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout bigLinearLayout=new LinearLayout(getActivity());
    	bigLinearLayout.setOrientation(0);
    	bigLinearLayout.setPadding(DensityUtil.dip2px(getActivity(),10), DensityUtil.dip2px(getActivity(),5), DensityUtil.dip2px(getActivity(),5), DensityUtil.dip2px(getActivity(),5));
    	
    	LinearLayout.LayoutParams im_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(getActivity(),45), DensityUtil.dip2px(getActivity(),40));
    	ImageView im=new ImageView(getActivity());
    	im.setImageResource(icon);
    	bigLinearLayout.addView(im,im_big);
    	
    	LinearLayout.LayoutParams small_big=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout smallLinearLayout=new LinearLayout(getActivity());
    	smallLinearLayout.setOrientation(1);
    	smallLinearLayout.setPadding(DensityUtil.dip2px(getActivity(),20), 0, 0, 0);
    	
    	LinearLayout.LayoutParams te1_small=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	TextView te1=new TextView(getActivity());
    	te1.setTextColor(Color.parseColor("#40454e"));
    	te1.setTextSize(17);
    	te1.setText(name);
    	smallLinearLayout.addView(te1,te1_small);
    	
    	LinearLayout.LayoutParams te2_small=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	TextView te2=new TextView(getActivity());
    	te2.setTextColor(Color.parseColor("#868c97"));
    	te2.setTextSize(12);
    	te2.setText(recentMessage);
    	smallLinearLayout.addView(te2,te2_small);
    	bigLinearLayout.addView(smallLinearLayout,small_big);
    	
    	bigLinearLayout.setTag(R.id.tag_int,buttonTag); 
    	bigLinearLayout.setTag(R.id.tag_ifstudent,st_te); 
    	bigLinearLayout.setTag(R.id.tag_pid,pid); 
    	bigLinearLayout.setOnClickListener(new OnClickListener() { 
	    	@Override
	        	public void onClick(View v) {
	        		int j = (Integer) v.getTag(R.id.tag_int);
	        		String coid=idarray[j];
	        		Intent it = new Intent(getActivity(),concrete_message.class);
	        		Bundle bd = new Bundle();
	        		
	        		String tag_second=v.getTag(R.id.tag_ifstudent).toString();
	        		String tag_third=v.getTag(R.id.tag_pid).toString();
	        		if(tag_second.equals("student"))
	        		{
	        			bd.putString("SID",tag_third);
	        			bd.putString("TID",coid);
	        			bd.putString("job", "student");
		                it.putExtras(bd);          
		                startActivity(it);
	        		}
	        		else if(tag_second.equals("teacher")){
	        			bd.putString("TID",tag_third);
	        			bd.putString("SID",coid);
	        			bd.putString("job", "teacher");
		                it.putExtras(bd);          
		                startActivity(it);
		                } 
	        		
	        		}
	    	
	    	});
    	allLeanerlayout.addView(bigLinearLayout,t_all);
	}
}
