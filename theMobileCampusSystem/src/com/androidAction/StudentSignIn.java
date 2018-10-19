package com.androidAction;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;

@SuppressWarnings("serial")
public class StudentSignIn extends BaseAction
{
	private Map<String, Object> jdata;
	private String CID;
	private String SID;
	public Map<String, Object> getJdata() {
		return jdata;
	}
	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
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
			String message=this.studentsService.Signin(CID, SID);
			jdata.put("StuSigninmessage", message);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
