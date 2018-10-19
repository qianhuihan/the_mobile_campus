package com.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.androidAction.ResultUtils;
import com.bean.Information;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class SendInformationAction extends BaseAction
{
	private String SID;
	private String TID;
	private String sender;
	private String body;
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
			ActionContext.getContext().getSession().put("sendmessage", message);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
