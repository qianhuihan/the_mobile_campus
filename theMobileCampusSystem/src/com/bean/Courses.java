package com.bean;

public class Courses {
	private String CID;//课程号
	private String Cname;//课程名
	private String TID;//教师id
	private String time;//上课时间
	private String wday;//上课星期
	private String place;//上课地点

	public Courses(){}

	public String getCID() {
		return CID;
	}

	public void setCID(String cID) {
		CID = cID;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWday() {
		return wday;
	}

	public void setWday(String wday) {
		this.wday = wday;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
