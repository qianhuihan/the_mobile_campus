package com.androidAction;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;

@SuppressWarnings("serial")
public class SignInAndroid extends BaseAction
{
	private Map<String, Object> jdata;
	private String CID;
	private int stime;
	
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
	
	public int getStime() {
		return stime;
	}

	public void setStime(int stime) {
		this.stime = stime;
	}

	public String execute()
	{
		jdata = new HashMap<String, Object>();
		try
		{
			String message=this.coursesService.signinTable(CID,stime);
			jdata.put("signinmessage", message);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
