package com.example.compus;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.bean.Courses;
import com.example.bean.InformationName;
import com.example.bean.Students;
import com.example.bean.Teachers;
import com.google.gson.Gson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.LinkedList;

public class send_message extends Fragment{
	 private SimpleAdapter mAdapter = null;
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {   	
		 View view = inflater.inflate(R.layout.sendmessage,container, false);
    	//绑定XML中的ListView，作为Item的容器  
        ListView list = (ListView) view.findViewById(R.id.sm_MyListView);  
        
        Bundle bd=getActivity().getIntent().getExtras();
    	String job=bd.getString("job");
    	String userJson=getActivity().getIntent().getStringExtra("loginUser");
    	String hql = null;
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
        	//192.168.1.102:
		    HttpPost httpPost = new HttpPost("http://192.168.1.102:8080/theMobileCampusSystem/showAllInformation?"+hql);
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
	        List<message> mData=new ArrayList<message>();
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
			    	if(cme.getSender().equals("T"))
			    	{
			    		mData.add(new message(cme.getTname(),cme.getTname()+":"+cme.getBody(),R.drawable.steacher));
			    	}
			    	else if(cme.getSender().equals("S"))
			    	{
			    		mData.add(new message(cme.getTname(),"我:"+cme.getBody(),R.drawable.steacher));
			    	}	
		    	}
		    	else
		    	{
		    		if(cme.getSender().equals("T"))
			    	{
			    		mData.add(new message(cme.getSname(),"我:"+cme.getBody(),R.drawable.student));
			    	}
			    	else if(cme.getSender().equals("S"))
			    	{
			    		mData.add(new message(cme.getSname(),cme.getSname()+":"+cme.getBody(),R.drawable.student));
			    	}	
		    	}
            }  
            mAdapter = new SimpleAdapter(getActivity(),mData,cme.getSID(),cme.getTID());
            list.setAdapter(mAdapter);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }

		return view;  
	}

}
