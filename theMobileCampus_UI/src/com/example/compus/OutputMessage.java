package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OutputMessage extends Activity{
	String tid,title,body;
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.outputmessage);
        
        Bundle bd=this.getIntent().getExtras();
    	tid=bd.getString("TID");
    	String afclass=bd.getString("afClass");

    	TextView opmclass=(TextView) findViewById(R.id.opm_class);
    	opmclass.setText("管理班级： "+afclass);
    	
    	ImageButton button1 = (ImageButton)findViewById(R.id.imm_back);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  

    	ImageButton button2 = (ImageButton)findViewById(R.id.opm_fa);  
    	button2.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	EditText titleText = (EditText)findViewById(R.id.opm_title);
            	EditText bodyText = (EditText)findViewById(R.id.opm_text);
            	
            	title=titleText.getText().toString();
            	body=bodyText.getText().toString();
            	
            	if(title==null || title.equals("") || body==null || body.equals(""))
            	{
            		Toast toast = Toast.makeText(OutputMessage.this, "标题和内容不能为空！" ,Toast.LENGTH_SHORT);
                    toast.show();
            	}
            	else
            	{
	            	HttpClient client = new DefaultHttpClient();
		    	    HttpPost httpPost = new HttpPost(http_url+"classInform");
		    	    List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		    	    params.add(new BasicNameValuePair("TID", tid));
		    	    params.add(new BasicNameValuePair("title", title)); 
		    	    params.add(new BasicNameValuePair("body", body)); 
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
		                String message=json.getString("informclass");
		                
		                Toast toast = Toast.makeText(OutputMessage.this, message ,Toast.LENGTH_SHORT);
	                    toast.show();
	                    if(message.equals("通知发送成功！"))
	                    	finish();
		    	    }
		    	    catch(Exception e)
		    	    {
		    	    	e.printStackTrace();
		    	    }
            	}
            }    
        });  
	}

}
