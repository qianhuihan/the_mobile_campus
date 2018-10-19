package com.example.compus;

import com.example.bean.News;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
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
    	
    	tv=(TextView)findViewById(R.id.news_source);
    	tv.setText(conews.getSource());
    	
    	tv=(TextView)findViewById(R.id.news_body);
    	tv.setText(conews.getBody());
    	
	}
}
