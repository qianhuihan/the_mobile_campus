package com.example.bean;

public class RollCallTable{
	private String CID;
	private String SID;
	private String Rdate;
	private boolean state;
	private String Sname;
	
	public RollCallTable(){}

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

	public String getRdate() {
		return Rdate;
	}

	public void setRdate(String rdate) {
		Rdate = rdate;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}
	
}
