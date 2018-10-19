package com.bean;

import java.io.Serializable;

public class Clacourse implements Serializable
{
	private static final long serialVersionUID=1L;
	private String CID;
	private String classname;
	
	public Clacourse(){}
	
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	
}
