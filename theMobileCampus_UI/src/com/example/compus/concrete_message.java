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
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.bean.InformationName;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class concrete_message extends Activity{
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.concretemessage);
		
		Bundle bd=getIntent().getExtras();
    	String studentid=bd.getString("SID");
    	String teacherid=bd.getString("TID");
    	try{
    		HttpClient client = new DefaultHttpClient();
    	    HttpPost httpPost = new HttpPost("http://192.168.43.112:8080/theMobileCampusSystem/showSomeInformation?SID="+studentid+"&TID="+teacherid);
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
            JSONArray messageObject = JSONArray.fromObject(json.get("someinformation").toString());
            
            //特定联系人聊天记录
            List<HashMap<String,String>> allmessage= (List<HashMap<String,String>>) JSONArray.toCollection(messageObject,HashMap.class);
	        List<Object> mData=new ArrayList<Object>();
	        InformationName cme=null;
	        TextView person=(TextView)findViewById(R.id.cm_coperson);
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
	        }
	        person.setText(cme.getTname());
    	}
    
    	catch(Exception e)
        {
        	e.printStackTrace();
        }
		ImageButton button1 = (ImageButton)findViewById(R.id.cm_person);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  
	}

}
