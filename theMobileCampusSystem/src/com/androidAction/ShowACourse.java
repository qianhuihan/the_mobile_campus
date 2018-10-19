package com.androidAction;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.Courses;
import com.bean.Teachers;

@SuppressWarnings("serial")
public class ShowACourse extends BaseAction
{
	private Map<String, Object> jdata2;
	private String CID = null;
	public Map<String, Object> getJdata2() {
		return jdata2;
	}
	public void setJdata2(Map<String, Object> jdata2) {
		this.jdata2 = jdata2;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String execute()
	{
		jdata2 = new HashMap<String, Object>();
		try
		{
			Courses course=this.coursesService.find(CID);
			Teachers teacher = null;
			if(course!=null)
				teacher=this.coursesService.findt(course);
			jdata2.put("CTeacher", teacher);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
