package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.RollCallTable;
import com.example.bean.Students;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AllClass extends Activity{
	private LinearLayout all;
	private RollCallTable rt;
	private RollCallTable[] studentTag=new RollCallTable[20];
	private String tagsid;
	private String courseId;
	private JSONObject jb;
	private org.json.JSONArray ja;  
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.all_class);
        Bundle bd=this.getIntent().getExtras();
    	courseId=bd.getString("courseNo");
    	all=(LinearLayout)findViewById(R.id.all);
    	
    	HttpClient client = new DefaultHttpClient();
	    HttpPost httpPost = new HttpPost(http_url+"showTable?CID="+courseId);
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
            
            JSONArray studentTable = JSONArray.fromObject(json.get("rollcallTable").toString());
				List<Map<String,Students>> costudent= (List<Map<String,Students>>) JSONArray.toCollection(studentTable,Map.class);
				for(int i=0;i<costudent.size();i++)
		        {
			    	Students student=new Students();
			    	Map map=(Map)costudent.get(i);
			    	student.setName((String) map.get("name"));
			    	student.setSID((String) map.get("SID"));
			    	addFirstLayout(student,i);
		        }
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    ImageButton put=new ImageButton(this);
    	LinearLayout.LayoutParams b_big=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,70),DensityUtil.dip2px(this,70));
    	b_big.gravity=Gravity.CENTER;
    	b_big.setMargins(0, DensityUtil.dip2px(this,20), 0, 0);
    	put.setBackgroundColor(Color.parseColor("#ffffff"));
    	put.setBackgroundResource(R.drawable.put);
    	put.setScaleType(ScaleType.FIT_CENTER);
    	all.addView(put,b_big);
    	
    	put.setOnClickListener(new OnClickListener() {
    		public void onClick(View v) {
    			ja=new org.json.JSONArray();  
    			for(int j=0;j<studentTag.length;j++){
    				if(studentTag[j]==null){
    					break;
    				}
    				jb=new JSONObject();
    				try {
						jb.put("CID", studentTag[j].getCID());
						jb.put("SID", studentTag[j].getSID());
						jb.put("state", studentTag[j].getState());
						ja.put(jb);
					} 
    				catch (Exception e) {
						e.printStackTrace();
					} 	
    			}
    			
				String rolltable = ja.toString();
				
				HttpClient client2 = new DefaultHttpClient();
				HttpPost httpPost2 = new HttpPost(http_url+"rollcall");
			    List<NameValuePair> params = new ArrayList<NameValuePair>(); 
			    params.add(new BasicNameValuePair("rolltable", rolltable));
			    try {

		            httpPost2.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		            HttpResponse httpResponse = client2.execute(httpPost2);
		            HttpEntity entity = httpResponse.getEntity(); 
		            
		            StringBuilder builder = new StringBuilder();  
		            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));  
		              
		            String s = null;  
		            while ((s = bufferedReader.readLine()) != null)  
		            {  
		                builder.append(s);  
		            }  
		              
		            JSONObject json = new JSONObject(builder.toString());
		            
		            String message=json.getString("rollcallmessage");
		            
    			//创建一个Toast提示信息                  
                Toast toast = Toast.makeText(AllClass.this,message,Toast.LENGTH_SHORT);                  
                toast.show();
			    }
			    catch(Exception e)
			    {
			    	e.printStackTrace();
			    }
                finish();
    		}

    	});
    	
    	ImageButton button1 = (ImageButton)findViewById(R.id.imm_back);  
        button1.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) {
            	finish();
            }    
        });  

	}
	private void addFirstLayout(Students student,int i){
		rt=new RollCallTable();
		rt.setCID(courseId);
		rt.setSID(student.getSID());
		rt.setState(true);
		//学生名字
    	LinearLayout.LayoutParams t_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),1.0f);
    	TextView textView1=new TextView(this);
    	textView1.setTextSize(17);
    	textView1.setGravity(Gravity.CENTER);
    	textView1.setText(student.getName());
    	textView1.setTextColor(Color.parseColor("#515151"));
    	
    	//学生学号
    	LinearLayout.LayoutParams t2_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),2.0f);
    	TextView textView2=new TextView(this);
    	textView2.setTextSize(17);
    	textView2.setText(student.getSID());
    	textView2.setTextColor(Color.parseColor("#515151"));
    	
    	//打叉按钮
    	LinearLayout.LayoutParams ib_l22=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,25),DensityUtil.dip2px(this,25));
    	CheckBox ib1=new CheckBox(this);
    	ib_l22.setMargins(0,DensityUtil.dip2px(this,2),0,0);
    	ib1.setBackgroundResource(R.drawable.checkbox);
    	ib1.setButtonDrawable(null);
    	LinearLayout.LayoutParams l22_l1=new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
    	LinearLayout ln22=new LinearLayout(this);
    	ln22.setGravity(Gravity.CENTER);
    	ln22.addView(ib1,ib_l22);
    	tagsid=student.getSID();
    	ib1.setTag(i);
    	studentTag[i]=rt;
    	ib1.setOnCheckedChangeListener(new OnCheckedChangeListener(){ 
			@Override
	        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
	    		if(isChecked){ 
	    			int tag=(Integer)buttonView.getTag();
	    			studentTag[tag].setState(false);
	    		}
	    		else{
	    			int tag=(Integer)buttonView.getTag();
	    			studentTag[tag].setState(true);
	    		}
	    	}
    	});
    	LinearLayout.LayoutParams big_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout bigLinearLayout=new LinearLayout(this);
    	bigLinearLayout.setOrientation(0);
    	bigLinearLayout.setPadding(DensityUtil.dip2px(this,15),DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,10));      
    	bigLinearLayout.addView(textView2,t2_l1);
    	bigLinearLayout.addView(textView1,t_l1);
    	bigLinearLayout.addView(ln22,l22_l1);
    	all.addView(bigLinearLayout, big_all);
    	
    	LinearLayout.LayoutParams line_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1);
    	View line=new View(this);
    	line.setBackgroundColor(Color.parseColor("#b5becd"));
    	all.addView(line, line_all);	
	}

}
