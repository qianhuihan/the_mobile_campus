package com.example.compus;

import com.example.bean.Students;
import com.example.bean.Teachers;
import com.google.gson.Gson;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

public class myInformation extends Fragment{
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.myinformation, container, false); 
    	
    	Bundle bd=getActivity().getIntent().getExtras();
    	String job=bd.getString("job");
    	String userJson=getActivity().getIntent().getStringExtra("loginUser");
    	if(job.equals("student"))
    	{
    		Students student=new Gson().fromJson(userJson,Students.class);
        	TextView tou=(TextView) view.findViewById(R.id.imagetouxiang);
        	tou.setText(student.getName());
        	TextView conumber=(TextView) view.findViewById(R.id.my_connumber);
        	conumber.setText(student.getSID());
        	TextView coname=(TextView) view.findViewById(R.id.my_conname);
        	coname.setText(student.getName());
        	TextView cosclass=(TextView) view.findViewById(R.id.my_conclass);
        	cosclass.setText(student.getSclass());
        	TextView cophone=(TextView) view.findViewById(R.id.my_phone);
        	cophone.setText(student.getPhone());
        	TextView coemail=(TextView) view.findViewById(R.id.my_emaill);
        	coemail.setText(student.getEmail());
    	}
    	else{
    		Teachers teacher=new Gson().fromJson(userJson,Teachers.class);
        	TextView tou=(TextView) view.findViewById(R.id.imagetouxiang);
        	tou.setText(teacher.getName());
        	TextView numname=(TextView) view.findViewById(R.id.my_number);
        	numname.setText("工号");
            TextView conumber=(TextView) view.findViewById(R.id.my_connumber);
            conumber.setText(teacher.getTID());
            TextView coname=(TextView) view.findViewById(R.id.my_conname);
            coname.setText(teacher.getName());
            TextView title=(TextView) view.findViewById(R.id.my_class);
        	title.setText("职称");
        	TextView cosclass=(TextView) view.findViewById(R.id.my_conclass);
        	cosclass.setText(teacher.getTitle());
        	TextView cophone=(TextView) view.findViewById(R.id.my_phone);
        	cophone.setText(teacher.getPhone());
        	TextView coemail=(TextView) view.findViewById(R.id.my_emaill);
        	coemail.setText(teacher.getEmail());
        	TextView affiliation=(TextView) view.findViewById(R.id.my_affiliation);
        	affiliation.setText("班主任");
        	TextView coaffiliation=(TextView) view.findViewById(R.id.my_coaffiliation);
        	String aff=teacher.getAffiliation();
        	if(aff==null)
        		aff="无";
        	coaffiliation.setText(aff);	
    	}
    	return view;
	}
	

}
