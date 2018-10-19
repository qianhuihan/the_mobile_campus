package com.androidAction;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;

@SuppressWarnings("serial")
public class SendClassInform extends BaseAction
{
	private Map<String, Object> jdata;
	private String TID;
	private String title;
	private String body;
	public Map<String, Object> getJdata() {
		return jdata;
	}
	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public String execute()
	{
		jdata = new HashMap<String, Object>();
		try{
			String message = this.teachersService.informClass(TID, title, body);
			jdata.put("informclass", message);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
