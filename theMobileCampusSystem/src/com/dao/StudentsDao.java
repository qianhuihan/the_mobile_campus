package com.dao;

import java.util.List;

import com.bean.ClassInform;
import com.bean.Courses;
import com.bean.Students;

public interface StudentsDao {
	public Students check(Students student);
	public List<Courses> displayAllCourses(Students student);
	public List<Courses> displayTodayCourses(Students student);
	public Students find(String SID);
	public String Signin(String CID,String SID);
	public List<ClassInform> getCInform(String SID);
}
