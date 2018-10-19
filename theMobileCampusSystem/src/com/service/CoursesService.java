package com.service;

import java.util.List;

import com.bean.Courses;
import com.bean.Students;
import com.bean.Teachers;

public interface CoursesService 
{
	public Courses find(String CID);
	public Teachers findt(Courses course);
	public List<Students> rollcallTable(String CID);
	public String signinTable(String CID,int stime);
	public int classNum(String CID);
}
