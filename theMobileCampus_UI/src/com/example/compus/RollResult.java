package com.example.compus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.example.bean.RollCallTable;
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
public class RollResult extends Activity{
	private LinearLayout all;
	private Spinner spinner;
	private List<String> data_list;
	private ArrayAdapter<String> arr_adapter;
	private RollCallTable rollArray[]=new RollCallTable[20];
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.signresult);
        all=(LinearLayout)findViewById(R.id.all);
        
        spinner = (Spinner) findViewById(R.id.spinner);
        data_list = new ArrayList<String>();
        
        Bundle bd=getIntent().getExtras();
    	int classNum=bd.getInt("classNum");//课程人数
    	int mark=classNum;
        List<Map<String,RollCallTable>> rolls=(List<Map<String,RollCallTable>>)getIntent().getSerializableExtra("rollcallresult");
        for(int i=0;i<rolls.size();i++)
        {
        	RollCallTable coroll=new RollCallTable();
	    	Map map=(Map)rolls.get(i);
	    	coroll.setCID((String) map.get("CID"));
	    	coroll.setRdate((String) map.get("Rdate"));
	    	coroll.setSID((String) map.get("SID"));
	    	coroll.setState((Boolean) map.get("state"));
	    	coroll.setSname((String) map.get("Sname"));
	    	if(i+1==mark){
	    		data_list.add(coroll.getRdate());
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
            		if(rollArray[i].getRdate().equals((String)spinner.getItemAtPosition(position))){
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
	private void addFirstLayout(RollCallTable student){
		//学生名字
    	LinearLayout.LayoutParams t_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),1.0f);
    	TextView textView1=new TextView(this);
    	textView1.setTextSize(17);
    	textView1.setGravity(Gravity.CENTER);
    	textView1.setText(student.getSname());
    	textView1.setTextColor(Color.parseColor("#515151"));
    	
    	//学生学号
    	LinearLayout.LayoutParams t2_l1=new LinearLayout.LayoutParams(0,DensityUtil.dip2px(this,30),2.0f);
    	TextView textView2=new TextView(this);
    	textView2.setTextSize(17);
    	textView2.setText(student.getSID());
    	textView2.setTextColor(Color.parseColor("#515151"));
    	
    	//是否到课图案
    	LinearLayout.LayoutParams ib_l22=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,25),DensityUtil.dip2px(this,25));
    	ImageView ib1=new ImageView(this);
    	ib_l22.setMargins(0,DensityUtil.dip2px(this,2),0,0);
    	if(student.getState()==true){
    		ib1.setBackgroundResource(R.drawable.yes2);
    	}
    	else{
    		ib1.setBackgroundResource(R.drawable.no2);
    	}
    	LinearLayout.LayoutParams l22_l1=new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1.0f);
    	LinearLayout ln22=new LinearLayout(this);
    	ln22.setGravity(Gravity.CENTER);
    	ln22.addView(ib1,ib_l22);
    	
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
