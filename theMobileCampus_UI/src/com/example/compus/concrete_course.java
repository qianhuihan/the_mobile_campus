package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.bean.Courses;
import com.example.bean.Teachers;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class concrete_course extends Activity{
	private String teacherId,studentId,tn,CID;
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.concretecouese);
    
        String coss=getIntent().getStringExtra("course"); 
        Courses course=new Gson().fromJson(coss,Courses.class);
        CID=course.getCID();
        String tess=getIntent().getStringExtra("teacher"); 
        Teachers teacher=new Gson().fromJson(tess,Teachers.class);       
        teacherId=teacher.getTID();
        Bundle bd=getIntent().getExtras();
    	studentId=bd.getString("SID");
    	tn=teacher.getName();
        
    	TextView tv=(TextView)findViewById(R.id.coc_cname);
    	tv.setText(course.getCname());
    	
    	tv=(TextView)findViewById(R.id.coc_cplace);
    	tv.setText(course.getPlace());
    	
    	tv=(TextView)findViewById(R.id.coc_ctime);
    	tv.setText(course.getWday()+"  "+course.getTime());
    	
    	tv=(TextView)findViewById(R.id.coc_cteacher);
    	tv.setText(teacher.getName());
    	
    	tv=(TextView)findViewById(R.id.coc_cphone);
    	tv.setText(teacher.getPhone());
    	
    	tv=(TextView)findViewById(R.id.coc_ctitle);
    	tv.setText(teacher.getTitle());
    	
    	tv=(TextView)findViewById(R.id.coc_cemail);
    	tv.setText(teacher.getEmail());
    	ImageButton button1 = (ImageButton)findViewById(R.id.coc_back);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  
        ImageButton sendButton = (ImageButton)findViewById(R.id.coc_send);
        sendButton.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View v)
            {   	
            	Intent it = new Intent(concrete_course.this,concrete_message.class);
            	Bundle bd= new Bundle();
                bd.putString("job","student");
                bd.putString("SID",studentId);
                bd.putString("TID",teacherId);
                bd.putString("teacherName", tn);
                it.putExtras(bd);
                startActivity(it);
            }
		});
        ImageButton signButton = (ImageButton)findViewById(R.id.sign);
        signButton.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v)
            { 
        		HttpClient client = new DefaultHttpClient();
        	    HttpPost httpPost = new HttpPost(http_url+"studentSignIn?SID="+studentId+"&CID="+CID); 
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
                    

                    String mess=json.getString("StuSigninmessage");
                    Toast toast = Toast.makeText(concrete_course.this, mess ,Toast.LENGTH_SHORT);
                    toast.show();
        	    }
        	    catch(Exception e){
        	    	e.printStackTrace();
        	    }
            }
        });
	}
}