package com.example.compus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class importantMessage extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.important_message);
    
    	ImageButton button1 = (ImageButton)findViewById(R.id.imm_back);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  

	}

}
