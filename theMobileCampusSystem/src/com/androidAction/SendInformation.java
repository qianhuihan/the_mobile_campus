package com.androidAction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.Information;

@SuppressWarnings("serial")
public class SendInformation extends BaseAction
{
	private Map<String, Object> jdata;
	private String SID;
	private String TID;
	private String sender;
	private String body;
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
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public String execute()
	{
		jdata = new HashMap<String,Object>();
		try
		{
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date);
			
			Information inform = new Information();
			inform.setBody(body);
			inform.setSID(SID);
			inform.setTID(TID);
			inform.setSender(sender);
			inform.setTime(time);
			
			String message=null;
			if(this.informationService.save(inform))
				message="success";
			else
				message="fail";
			
			jdata.put("sendResult", message);

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
