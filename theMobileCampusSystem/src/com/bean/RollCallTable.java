package com.bean;

import java.io.Serializable;

public class RollCallTable implements Serializable
{
    private static final long serialVersionUID = 1L;
	private String CID;
	private String SID;
	private String Rdate;
	private boolean state;
	
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

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}
	
}
