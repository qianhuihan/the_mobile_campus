package com.example.compus;

import com.example.bean.Courses;
import com.example.bean.Teachers;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class concrete_course extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.concretecouese);
    
        String coss=getIntent().getStringExtra("course"); 
        Courses course=new Gson().fromJson(coss,Courses.class);
        
        String tess=getIntent().getStringExtra("teacher"); 
        Teachers teacher=new Gson().fromJson(tess,Teachers.class);

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
	}
}