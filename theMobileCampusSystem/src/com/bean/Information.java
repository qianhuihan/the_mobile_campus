package com.bean;

public class Information 
{
	private int IID;
	private String SID;
	private String TID;
	private String sender;//T��ʾteacher S��ʾstudent
	private String time;
	private String body;
	
	public int getIID() {
		return IID;
	}
	public void setIID(int iID) {
		IID = iID;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
}
