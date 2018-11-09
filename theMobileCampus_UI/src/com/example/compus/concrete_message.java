package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import com.example.bean.InformationName;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class concrete_message extends Activity{
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	private LinearLayout alllayout;
	private Button sendButton;
	private EditText sendText;
	private InformationName cme=null;
	private String jobname=null;
	String studentid=null;
	String teacherid=null;
	String job=null;
	private String zero;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.concretemessage);
		
		alllayout=(LinearLayout)findViewById(R.id.cm_mie);
		Bundle bd=getIntent().getExtras();
    	studentid=bd.getString("SID");
    	teacherid=bd.getString("TID");
    	job=bd.getString("job");
    	zero=bd.getString("teacherName");
    	freshMessage();
    	
		ImageButton button1 = (ImageButton)findViewById(R.id.cm_person);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  
        //刷新操作
        final Handler handler = new Handler(); 
        Runnable runnable = new Runnable(){ 
        @Override 
        public void run() { 
        	alllayout.removeAllViews();
    		freshMessage();
    		ScrollView scrollView=(ScrollView)findViewById(R.id.sc);
    	    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
        handler.postDelayed(this, 50);// 50是延时时长 
        } 
        }; 
        handler.postDelayed(runnable, 50);// 打开定时器，执行操作 
        
        sendText = (EditText)findViewById(R.id.send);
		sendButton = (Button)findViewById(R.id.sButton);
		sendButton.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v) 
            {
            	InformationName exa=new InformationName();
            	String sendBody = sendText.getText().toString();
            	String sendSID = cme.getSID();
            	String sendTID = cme.getTID();
            	String sender=null;
            	if(job.equals("student"))
            		sender = "S";
            	else if(job.equals("teacher"))
            		sender = "T";
            	if(sendBody==null || sendBody.equals(""))
            	{
            		Toast toast = Toast.makeText(concrete_message.this, "发送内容不能为空！" ,Toast.LENGTH_SHORT);
                    toast.show();
            	}
            	else {
            		exa.setBody(sendBody);
            		exa.setSID(sendSID);
            		exa.setTID(sendTID);
            		exa.setSender(sender);
            		addMessage(exa);
            		sendMessage(sendBody,sendSID,sendTID,sender);
            	}
            	sendText.setText("");
            }
		});
		
	}
	private void sendMessage(String sendBody,String sendSID,String sendTID,String sender)
	{
		HttpClient client = new DefaultHttpClient();
	    HttpPost httpPost = new HttpPost(http_url+"sendInformation");
	    List<NameValuePair> params = new ArrayList<NameValuePair>(); 
	    params.add(new BasicNameValuePair("SID", sendSID));
	    params.add(new BasicNameValuePair("TID", sendTID)); 
	    params.add(new BasicNameValuePair("body", sendBody)); 
	    params.add(new BasicNameValuePair("sender", sender)); 
	    try {

            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
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
            if(json.getString("sendResult")==null){
            	Toast toast = Toast.makeText(concrete_message.this, "信息发送失败！",Toast.LENGTH_SHORT);
                toast.show();
            }
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	private void freshMessage(){
		try{
    		HttpClient client = new DefaultHttpClient();
    	    HttpPost httpPost = new HttpPost(http_url+"showSomeInformation?SID="+studentid+"&TID="+teacherid);
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
            JSONArray messageObject = JSONArray.fromObject(json.get("someInformation").toString());
            
            //特定联系人聊天记录
            List<HashMap<String,String>> allmessage= (List<HashMap<String,String>>) JSONArray.toCollection(messageObject,HashMap.class);
	        List<Object> mData=new ArrayList<Object>();
	        TextView person=(TextView)findViewById(R.id.cm_coperson);
	        if(allmessage.size()==0)
    		{
    			jobname=zero;
    		}
	        for(int i=0;i<allmessage.size();i++)  {  
	        	cme=new InformationName();
		    	Map map=(Map)allmessage.get(i);
		    	cme.setBody((String) map.get("body"));
		    	cme.setSender((String) map.get("sender"));
		    	cme.setSID((String) map.get("SID"));
		    	cme.setSname((String) map.get("Sname"));
		    	cme.setTID((String) map.get("TID"));
		    	cme.setTime((String) map.get("time"));
		    	cme.setTname((String) map.get("Tname"));
		    	person.setText(jobname);
		    	addMessage(cme);
	        }
    	}
    	catch(Exception e)
        {
        	e.printStackTrace();
        }
		
	}
	private void addMessage(InformationName cme){
		if(job.equals("student")){
    		if(cme.getSender().equals("T")){
	    		LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	    		LinearLayout bigLayout=new LinearLayout(this);
		    	bigLayout.setPadding(DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5));
		    	t_all.setMargins(DensityUtil.dip2px(this,5), 0, 0, 0);
		    	bigLayout.setOrientation(0);
		    	alllayout.addView(bigLayout,t_all);
		    	
		    	LinearLayout.LayoutParams im1_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,30),DensityUtil.dip2px(this,30));
		    	ImageView im1=new ImageView(this);
		    	im1.setImageResource(R.drawable.nteacher);
		    	im1.setScaleType(ScaleType.FIT_CENTER);
		    	bigLayout.addView(im1,im1_big);
		    	
		    	LinearLayout.LayoutParams text_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,200),LinearLayout.LayoutParams.WRAP_CONTENT);
		    	TextView text=new TextView(this);
		    	text_big.setMargins(DensityUtil.dip2px(this,10), 0, 0, 0);
		    	text.setTextSize(17);
		    	text.setTextColor(Color.parseColor("#000000"));
		    	text.setText(cme.getBody());
		    	bigLayout.addView(text,text_big);	
	    	}
	    	else{
	    		LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	    		LinearLayout bigLayout=new LinearLayout(this);
		    	bigLayout.setPadding(DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5));
		    	t_all.setMargins(0, 0, DensityUtil.dip2px(this,5), 0);
		    	bigLayout.setOrientation(0);
		    	alllayout.addView(bigLayout,t_all);
		    	
		    	LinearLayout.LayoutParams text_big=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
		    	TextView text=new TextView(this);
		    	bigLayout.addView(text,text_big);
		    	
		    	LinearLayout.LayoutParams text2_big=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,2.2f);
		    	TextView text2=new TextView(this);
		    	text2_big.setMargins(0, 0,DensityUtil.dip2px(this,10), 0);
		    	text2.setGravity(Gravity.RIGHT);
		    	text2.setTextSize(17);
		    	text2.setTextColor(Color.parseColor("#000000"));
		    	text2.setText(cme.getBody());
		    	bigLayout.addView(text2,text2_big);		
		    	
		    	LinearLayout.LayoutParams im1_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,30),DensityUtil.dip2px(this,30));
		    	ImageView im1=new ImageView(this);
		    	im1.setImageResource(R.drawable.nstudent);
		    	im1.setScaleType(ScaleType.FIT_CENTER);
		    	bigLayout.addView(im1,im1_big);	
	    	}
    		jobname=cme.getTname();
    	}
    	else{
    		if(cme.getSender().equals("S")){
	    		LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	    		LinearLayout bigLayout=new LinearLayout(this);
		    	bigLayout.setPadding(DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5));
		    	t_all.setMargins(DensityUtil.dip2px(this,5), 0, 0, 0);
		    	bigLayout.setOrientation(0);
		    	alllayout.addView(bigLayout,t_all);
		    	
		    	LinearLayout.LayoutParams im1_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,30),DensityUtil.dip2px(this,30));
		    	ImageView im1=new ImageView(this);
		    	im1.setImageResource(R.drawable.nstudent);
		    	im1.setScaleType(ScaleType.FIT_CENTER);
		    	bigLayout.addView(im1,im1_big);
		    	
		    	LinearLayout.LayoutParams text_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,200),LinearLayout.LayoutParams.WRAP_CONTENT);
		    	TextView text=new TextView(this);
		    	text_big.setMargins(DensityUtil.dip2px(this,10), 0, 0, 0);
		    	text.setTextSize(17);
		    	text.setTextColor(Color.parseColor("#000000"));
		    	text.setText(cme.getBody());
		    	bigLayout.addView(text,text_big);	
	    	}
	    	else{
	    		LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
	    		LinearLayout bigLayout=new LinearLayout(this);
		    	bigLayout.setPadding(DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5), DensityUtil.dip2px(this,5));
		    	t_all.setMargins(0, 0, DensityUtil.dip2px(this,5), 0);
		    	bigLayout.setOrientation(0);
		    	alllayout.addView(bigLayout,t_all);
		    	
		    	LinearLayout.LayoutParams text_big=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
		    	TextView text=new TextView(this);
		    	bigLayout.addView(text,text_big);
		    	
		    	LinearLayout.LayoutParams text2_big=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,2.2f);
		    	TextView text2=new TextView(this);
		    	text2_big.setMargins(0, 0,DensityUtil.dip2px(this,10), 0);
		    	text2.setGravity(Gravity.RIGHT);
		    	text2.setTextSize(17);
		    	text2.setTextColor(Color.parseColor("#000000"));
		    	text2.setText(cme.getBody());
		    	bigLayout.addView(text2,text2_big);		
		    	
		    	LinearLayout.LayoutParams im1_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,30),DensityUtil.dip2px(this,30));
		    	ImageView im1=new ImageView(this);
		    	im1.setImageResource(R.drawable.nteacher);
		    	im1.setScaleType(ScaleType.FIT_CENTER);
		    	bigLayout.addView(im1,im1_big);	
	    	}
    		jobname=cme.getSname();	
    	}
	}

}
