package com.example.compus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.example.bean.Courses;
import com.example.bean.News;
import com.example.bean.Teachers;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class class_fragment extends Fragment{
	private GridLayout gridLayout;
	private View view;
	private Button[] rbs=new Button[20];
	private Courses[] cos=new Courses[20];
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	view = inflater.inflate(R.layout.courselayout, container, false);
    	gridLayout = (GridLayout) view.findViewById(R.id.course_gridlayout);
    	setcourseGridLayout();
		return view;  
	}
	private void setcourseGridLayout() {
		
		List<Map<String,Courses>> cours=(List<Map<String, Courses>>) getActivity().getIntent().getSerializableExtra("courses");
	    for(int i=0;i<cours.size();i++)
        {
	    	Courses cocourse=new Courses();
	    	Map map=(Map)cours.get(i);
	    	cocourse.setCID((String) map.get("CID"));
	    	cocourse.setCname((String) map.get("Cname"));
	    	cocourse.setPlace((String) map.get("place"));
	    	cocourse.setTID((String) map.get("TID"));
	    	cocourse.setTime((String) map.get("time"));
	    	cocourse.setWday((String) map.get("wday"));
	    	
	    	GridLayout.LayoutParams first = new GridLayout.LayoutParams();
	    	setTime(first,cocourse);
	    	setDay(first,cocourse);
	        first.setGravity(Gravity.FILL);

	        Button button = new Button(getActivity());
	        button.setText(cocourse.getCname()+"@"+cocourse.getPlace());
	        button.setTextSize(10);
	        button.setWidth(50);
	        button.setAlpha((float) 0.5);
	        button.setPadding(DensityUtil.dip2px(getActivity(),5),0,DensityUtil.dip2px(getActivity(),5),0);
			button.setHeight(50);
	        button.setTextColor(Color.parseColor("#ffffff"));
	        if(i%5==0)
	        	button.setBackgroundColor(Color.parseColor("#008000"));
	        else if(i%5==1)
	        	button.setBackgroundColor(Color.parseColor("#800080"));
	        else if(i%5==2)
	        	button.setBackgroundColor(Color.parseColor("#FFA500"));
	        else if(i%5==3)
	        	button.setBackgroundColor(Color.parseColor("#FF0000"));
	        else if(i%5==4)
	        	button.setBackgroundColor(Color.parseColor("#376f81"));

	        rbs[i]=button;
	        cos[i]=cocourse;
	        gridLayout.addView(button, first);  
        } 
	}
	//找到课程信息
	private void findACourse(Courses course)
	{
		try
		{
			HttpClient client = new DefaultHttpClient();
		    HttpPost httpPost = new HttpPost("http://192.168.1.102:8080/theMobileCampusSystem/showACourse?CID="+course.getCID());
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
            JSONObject jsonObject = json.getJSONObject("CTeacher"); 
            Teachers teacher = new Teachers();
            teacher.setTID(jsonObject.getString("TID"));
            teacher.setName(jsonObject.getString("name"));
            teacher.setPassword(jsonObject.getString("password"));
            teacher.setEmail(jsonObject.getString("email"));
            teacher.setPhone(jsonObject.getString("phone"));
            teacher.setAffiliation(jsonObject.getString("affiliation"));
            teacher.setTitle(jsonObject.getString("title"));
     
            Intent it = new Intent(getActivity(),concrete_course.class);
            it.putExtra("course",new Gson().toJson(course));
            it.putExtra("teacher",new Gson().toJson(teacher));          
            startActivity(it);    
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    
	}
	
	//设置课程在表格的行位置
	private void setTime(GridLayout.LayoutParams first,Courses cocourse){
		if(cocourse.getTime().equals("1-2"))
			first.rowSpec = GridLayout.spec(1,2);
		else if(cocourse.getTime().equals("3-4"))
			first.rowSpec = GridLayout.spec(3,2);
		else if(cocourse.getTime().equals("6-7"))
			first.rowSpec = GridLayout.spec(6,2);
		else if(cocourse.getTime().equals("8-9"))
			first.rowSpec = GridLayout.spec(8,2);	
	}
	//设置课程在表格的列位置
	private void setDay(GridLayout.LayoutParams first,Courses cocourse){
		if(cocourse.getWday().equals("周一"))
			first.columnSpec = GridLayout.spec(1,1);
		else if(cocourse.getWday().equals("周二"))
			first.columnSpec = GridLayout.spec(2,1);
		else if(cocourse.getWday().equals("周三"))
			first.columnSpec = GridLayout.spec(3,1);
		else if(cocourse.getWday().equals("周四"))
			first.columnSpec = GridLayout.spec(4,1);
		else if(cocourse.getWday().equals("周五"))
			first.columnSpec = GridLayout.spec(5,1);
		else if(cocourse.getWday().equals("周六"))
			first.columnSpec = GridLayout.spec(6,1);
		else if(cocourse.getWday().equals("周日"))
			first.columnSpec = GridLayout.spec(7,1);
			
	}
    //注册课表按钮
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);
		try{
			for(int j=0;j<rbs.length;j++){ 
				if(rbs[j]!=null){
				rbs[j].setTag(j); 
		    	rbs[j].setOnClickListener(new OnClickListener() { 
		    	@Override
		        	public void onClick(View v) {
		        		int i = (Integer) v.getTag();
		        		Courses course=cos[i];
		        		findACourse(course); 
		        		}
		        	}); 
		    	} 
		    else break;
				}
			}
		catch (Exception e) {
            e.printStackTrace();
            }
		}
		 

}