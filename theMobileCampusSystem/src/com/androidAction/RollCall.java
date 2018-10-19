package com.androidAction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.RollCallTable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class RollCall extends BaseAction
{
	private Map<String, Object> jdata;
	private String rolltable;
	public Map<String, Object> getJdata() {
		return jdata;
	}
	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}
	public String getRolltable() {
		return rolltable;
	}
	public void setRolltable(String rolltable) {
		this.rolltable = rolltable;
	}
	
	public String execute()
	{
		jdata = new HashMap<String, Object>();
		try
		{
			JSONArray json = JSONArray.fromObject(rolltable); // 首先把字符串转成 JSONArray  对象
	
			Date rdate=new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(rdate);
			
			List<RollCallTable> list = new ArrayList<RollCallTable>();
			if(json.size()>0)
			{
				for(int i=0;i<json.size();i++)
				{
					RollCallTable rollcall=new RollCallTable();
					JSONObject roll = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					rollcall.setCID(roll.getString("CID"));
					rollcall.setSID(roll.getString("SID"));
					rollcall.setRdate(time);
					rollcall.setState(roll.getBoolean("state"));
					list.add(rollcall);
				}
			}
			String message=this.teachersService.rollcall(list);
			
			jdata.put("rollcallmessage", message);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
