package com.example.compus;

import com.example.bean.News;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class concrete_news extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.concretenews);
        
        String con=getIntent().getStringExtra("conews");
        News conews=new Gson().fromJson(con, News.class);

    	TextView tv=(TextView)findViewById(R.id.news_name);
    	tv.setText(conews.getTitle());
    	
    	tv=(TextView)findViewById(R.id.news_date);
    	tv.setText(conews.getDate());
    	
    	tv=(TextView)findViewById(R.id.news_editor);
    	tv.setText(conews.getEditor());

    	
    	tv=(TextView)findViewById(R.id.news_body);
    	tv.setText(conews.getBody());
    	
    	ImageButton button1 = (ImageButton)findViewById(R.id.news_back);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  
    	
	}
}
