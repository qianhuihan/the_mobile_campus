package com.bean;

import java.io.Serializable;

public class SignInTable implements Serializable{
    private static final long serialVersionUID = 1L;
	private String CID;
	private String SID;
	private String Sdate;
	private String stime;
	private double distance;
	private long deadline;
	
	public SignInTable(){}

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

	public String getSdate() {
		return Sdate;
	}

	public void setSdate(String sdate) {
		Sdate = sdate;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public long getDeadline() {
		return deadline;
	}

	public void setDeadline(long deadline) {
		this.deadline = deadline;
	}

}
