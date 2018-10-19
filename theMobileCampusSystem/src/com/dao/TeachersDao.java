package com.dao;

import java.util.List;

import com.bean.Courses;
import com.bean.RollCallTable;
import com.bean.SignInTable;
import com.bean.Teachers;

public interface TeachersDao 
{
	public Teachers check(Teachers teacher);
	public List<Courses> showCourse(Teachers teacher);
	public List<Courses> showAllCourse(Teachers teacher);
	public Teachers find(String TID);
	public String informClass(String TID,String title,String body);
	public String rollcall(List<RollCallTable> list);
	public List<RollCallTable> findRollcall(String CID);
	public List<SignInTable> findSignin(String CID);
}
