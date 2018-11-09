package com.example.compus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.bean.RollCallTable;
import com.example.bean.SignInTable;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

//点名结果界面
public class SignResult extends Activity{
	private LinearLayout all;
	private Spinner spinner;
	private List<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private SignInTable rollArray[]=new SignInTable[20];
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.signresult);
        all=(LinearLayout)findViewById(R.id.all);
        TextView title = (TextView)findViewById(R.id.title); 
        title.setText("历史签到结果");
        spinner = (Spinner) findViewById(R.id.spinner);
        data_list = new ArrayList<String>();
        
        Bundle bd=getIntent().getExtras();
    	int classNum=bd.getInt("classNum");//课程人数
    	int mark=classNum;
        List<Map<String,SignInTable>> rolls=(List<Map<String,SignInTable>>)getIntent().getSerializableExtra("signinresult");
        if(rolls==null){
        	LinearLayout.LayoutParams t_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        	TextView textView1=new TextView(this);
        	textView1.setTextSize(17);
        	textView1.setGravity(Gravity.CENTER);
        	textView1.setText("暂无签到信息！");
        	textView1.setTextColor(Color.parseColor("#515151"));
        	all.addView(textView1, t_all);
        	return;
        }
        for(int i=0;i<rolls.size();i++)
        {
        	SignInTable coroll=new SignInTable();
	    	Map map=(Map)rolls.get(i);
	    	coroll.setCID((String) map.get("CID"));
	    	coroll.setSdate((String) map.get("Sdate"));
	    	coroll.setSID((String) map.get("SID"));
	    	coroll.setStime((String) map.get("stime"));
	    	coroll.setSname((String) map.get("Sname"));
	    	coroll.setDistance(5.23);
	    	if(i+1==mark){
	    		data_list.add(coroll.getSdate());
	    		mark+=classNum;
	    	}
	    	rollArray[i]=coroll;
        }
        //适配器
        arr_adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,data_list);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        
        //添加事件Spinner事件监听  
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            	all.removeAllViews();
            	for(int i=0;i<rollArray.length;i++){
            		if(rollArray[i]==null){
            			break;
            		}
            		if(rollArray[i].getSdate().equals((String)spinner.getItemAtPosition(position))){
            			addFirstLayout(rollArray[i]);
            		}
            	}  
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ImageButton button1 = (ImageButton)findViewById(R.id.imm_back);  
        button1.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {
            	finish();
            }    
        });  
	}
	private void addFirstLayout(SignInTable student){
		//学生名字
    	LinearLayout.LayoutParams t_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),1.0f);
    	TextView textView1=new TextView(this);
    	textView1.setTextSize(13);
    	textView1.setGravity(Gravity.CENTER);
    	textView1.setText(student.getSname());
    	textView1.setTextColor(Color.parseColor("#515151"));
    	
    	//学生学号
    	LinearLayout.LayoutParams t2_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),1.5f);
    	TextView textView2=new TextView(this);
    	textView2.setTextSize(13);
    	textView2.setText(student.getSID());
    	textView2.setTextColor(Color.parseColor("#515151"));
    	
    	
    	//签到时间
    	LinearLayout.LayoutParams ib_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),1.0f);
    	TextView ib1=new TextView(this);
    	ib1.setGravity(Gravity.CENTER);
    	
    	LinearLayout.LayoutParams dis_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),1.0f);
    	TextView dis=new TextView(this);
    	dis.setGravity(Gravity.CENTER);
    	
    	if(student.getStime()==null){
    		ib1.setText("未签到");
    		ib1.setTextColor(Color.parseColor("#e71a1a"));
    	}
    	else{
    		ib1.setText(student.getStime().substring(10));
    		ib1.setTextColor(Color.parseColor("#2e8138"));
    		dis.setText(student.getDistance()+"m");
    		dis.setTextColor(Color.parseColor("#2e8138"));
    	}
    	ib1.setTextSize(13);
    	
    	LinearLayout.LayoutParams big_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    	LinearLayout bigLinearLayout=new LinearLayout(this);
    	bigLinearLayout.setOrientation(0);
    	bigLinearLayout.setPadding(DensityUtil.dip2px(this,15),DensityUtil.dip2px(this,10),DensityUtil.dip2px(this,20),DensityUtil.dip2px(this,10));      
    	bigLinearLayout.addView(textView2,t2_l1);
    	bigLinearLayout.addView(textView1,t_l1);
    	bigLinearLayout.addView(ib1,ib_l1);
    	bigLinearLayout.addView(dis,dis_l1);
    	all.addView(bigLinearLayout, big_all);
    	
    	LinearLayout.LayoutParams line_all=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1);
    	View line=new View(this);
    	line.setBackgroundColor(Color.parseColor("#b5becd"));
    	all.addView(line, line_all);	
	}
}
