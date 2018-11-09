package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import com.example.bean.ClassInform;
import com.example.compus.R.drawable;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class importantMessage extends Activity{
	private LinearLayout all;
	private String sid;
	private String http_url="http://192.168.31.202:8080/theMobileCampusSystem/";
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.important_message);
        all=(LinearLayout)findViewById(R.id.imm_all);
        Bundle bd=getIntent().getExtras();
    	sid=bd.getString("SID");

		try{
        	HttpClient client = new DefaultHttpClient();
		    HttpPost httpPost = new HttpPost(http_url+"getClassInform?SID="+sid);
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
	        JSONArray classinform = JSONArray.fromObject(json.get("classInform").toString());
	        List<Map<String,ClassInform>> immessage= (List<Map<String,ClassInform>>) JSONArray.toCollection(classinform,Map.class);
	        for(int i=0;i<immessage.size();i++)  {  
	        	ClassInform ci=new ClassInform();
		    	Map map=(Map)immessage.get(i);
		    	ci.setTime((String) map.get("time"));
		    	ci.setBody((String) map.get("body"));
		    	ci.setTitle((String) map.get("title"));
		    	setMessage(ci);
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        
    	ImageButton button1 = (ImageButton)findViewById(R.id.imm_back);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  

	}
	private void setMessage(ClassInform ci){
		//发布时间
		LinearLayout.LayoutParams t_big=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	TextView textView=new TextView(this);
    	textView.setPadding(DensityUtil.dip2px(this,10),0, 0, 0);
    	textView.setText(ci.getTime());
    	textView.setTextSize(16);
    	textView.setTextColor(Color.parseColor("#2d3f58"));
    	
    	LinearLayout.LayoutParams l_big=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout ln=new LinearLayout(this);
    	ln.setOrientation(0);
    	ln.setPadding(DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10));       
    	
    	//标题图标
    	LinearLayout.LayoutParams image_ln=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,25),DensityUtil.dip2px(this,25));
    	ImageView image=new ImageView(this);
    	image.setBackgroundResource(R.drawable.immessage);
    	image.setScaleType(ScaleType.FIT_CENTER);
    	//重要通知标题
    	LinearLayout.LayoutParams title_ln=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,DensityUtil.dip2px(this,20));
    	TextView title=new TextView(this);
    	title.setGravity(Gravity.CENTER);
    	title.setText(ci.getTitle());
    	title.setTextSize(17);
    	title.setTextColor(Color.parseColor("#6496dd"));
    	title.setPadding(DensityUtil.dip2px(this,10),0,0,0);
    	ln.addView(image, image_ln);
    	ln.addView(title, title_ln);
    	//大布局
    	LinearLayout.LayoutParams big_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout bigLinearLayout=new LinearLayout(this);
    	bigLinearLayout.setPadding(DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,10));
    	bigLinearLayout.setOrientation(1);
    	bigLinearLayout.addView(textView,t_big);	
    	bigLinearLayout.addView(ln, l_big);
    	
    	LinearLayout.LayoutParams n_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
    	TextView n=new TextView(this);
    	n.setText(ci.getBody());
    	n.setTextSize(14);
    	n.setTextColor(Color.parseColor("#595b5d"));
    	n.setPadding(DensityUtil.dip2px(this,10),0,0,0);
    	bigLinearLayout.addView(n, n_all);
    	
    	LinearLayout.LayoutParams t_all3=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,DensityUtil.dip2px(this,2));
    	t_all3.setMargins(0,DensityUtil.dip2px(this,10),0,0);
    	View dasnline=new View(this);
    	dasnline.setBackgroundResource(drawable.dash_view);
    	dasnline.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    	bigLinearLayout.addView(dasnline,t_all3);
    	all.addView(bigLinearLayout, big_all);
	}
}
