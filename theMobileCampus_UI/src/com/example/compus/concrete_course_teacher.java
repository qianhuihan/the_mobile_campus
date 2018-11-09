package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.bean.Courses;
import com.example.bean.RollCallTable;
import com.example.bean.SignInTable;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class concrete_course_teacher extends Activity{
	private String teacherId,courseId;
	private double lj,lw;
	private int stime;
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.concretecourseteacher);
    
        String coss=getIntent().getStringExtra("course"); 
        Courses course=new Gson().fromJson(coss,Courses.class);
        courseId=course.getCID();
    
    	TextView tv=(TextView)findViewById(R.id.coc_cname);
    	tv.setText(course.getCname());
    	
    	tv=(TextView)findViewById(R.id.coc_cplace);
    	tv.setText(course.getPlace());
    	
    	tv=(TextView)findViewById(R.id.coc_ctime);
    	tv.setText(course.getWday()+"  "+course.getTime());
    	
    	tv=(TextView)findViewById(R.id.coc_class);
    	tv.setText(course.getCID());
    	
    	ImageButton button1 = (ImageButton)findViewById(R.id.coc_back);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  
        //班级名单按钮：点名
        ImageButton signButton = (ImageButton)findViewById(R.id.sign);
        signButton.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v)
            {   	
            	Intent it = new Intent(concrete_course_teacher.this,AllClass.class);
            	Bundle bd= new Bundle();
            	bd.putString("courseNo",courseId);
                it.putExtras(bd);
                startActivity(it);
            }
		});
        //查看点名结果按钮
        ImageButton signrButton = (ImageButton)findViewById(R.id.signresult);
        signrButton.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v)
            { 
            	Intent it = new Intent(concrete_course_teacher.this,RollResult.class);
            	HttpClient client = new DefaultHttpClient();
        	    HttpPost httpPost = new HttpPost(http_url+"findRollcall?CID="+courseId); 
        	    try {
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
                    int classNum=json.getInt("classNum");
                    JSONArray rollObject = JSONArray.fromObject(json.get("rollcallresult").toString());
                    List<HashMap<String,RollCallTable>> rollcall= (List<HashMap<String,RollCallTable>>) JSONArray.toCollection(rollObject,HashMap.class);
                    it.putExtra("rollcallresult", (Serializable)rollcall);
                    Bundle bd = new Bundle();
                    bd.putInt("classNum",classNum);
                    it.putExtras(bd);
                    startActivity(it);
        	    }
        	    catch(Exception e){
        	    	e.printStackTrace();
        	    }
            }
		});
        //开启签到按钮
        ImageButton beginButton = (ImageButton)findViewById(R.id.begin);
        beginButton.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v)
            {   	
            	new AlertDialog.Builder(concrete_course_teacher.this).setTitle("签到持续时间").setIcon(
            			android.R.drawable.ic_dialog_info).setSingleChoiceItems(
            					new String[] { "5分钟", "10分钟","30分钟","60分钟" }, 0,
            					new DialogInterface.OnClickListener() 
            					{
            						public void onClick(DialogInterface dialog, int which) {
            							switch(which)
            							{
            							case 0:
            								stime=5;
            								break;
            							case 1:
            								stime=10;
            								break;
            							case 2:
            								stime=30;
            								break;
            							case 3:
            								stime=60;
            								break;
            							default:
            								stime=120;
            								break;
            							}
            			        		HttpClient client = new DefaultHttpClient();
            			        	    HttpPost httpPost = new HttpPost(http_url+"signinAndroid?CID="+courseId+"&stime="+stime);
            			        	    try {
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
            			                    String message=json.getString("signinmessage");
            			                    
            			                    Toast toast = Toast.makeText(concrete_course_teacher.this, message ,Toast.LENGTH_SHORT);
            			                    toast.show();
            			        	    }
            			        	    catch(Exception e)
            			        	    {
            			        	    	e.printStackTrace();
            			        	    }
            							dialog.dismiss();
            						}
            					}).setNegativeButton("取消", null).show();
            	
            }
		});
        //查看签到结果按钮
        ImageButton beginrButton = (ImageButton)findViewById(R.id.beginresult);
        beginrButton.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v)
            { 
            	Intent it = new Intent(concrete_course_teacher.this,SignResult.class);
            	HttpClient client = new DefaultHttpClient();
        	    HttpPost httpPost = new HttpPost(http_url+"findSignin?CID="+courseId); 
        	    try {
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
                    int classNum=json.getInt("classNum");
                    JSONArray rollObject = JSONArray.fromObject(json.get("signinresult").toString());
                    List<HashMap<String,SignInTable>> rollcall= (List<HashMap<String,SignInTable>>) JSONArray.toCollection(rollObject,HashMap.class);
                    it.putExtra("signinresult", (Serializable)rollcall);
                    Bundle bd = new Bundle();
                    bd.putInt("classNum",classNum);
                    it.putExtras(bd);
                    startActivity(it);
        	    }
        	    catch(Exception e)
        	    {
        	    	e.printStackTrace();
        	    }
            }
		});
	}
}