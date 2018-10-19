package com.androidAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.ClassInform;

@SuppressWarnings("serial")
public class GetClassInform extends BaseAction
{
	private Map<String, Object> jdata;
	private String SID;
	public Map<String, Object> getJdata() {
		return jdata;
	}
	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	
	public String execute()
	{
		jdata = new HashMap<String, Object>();
		try
		{
			List<ClassInform> list=this.studentsService.getCInform(SID);
			jdata.put("classInform", list);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
