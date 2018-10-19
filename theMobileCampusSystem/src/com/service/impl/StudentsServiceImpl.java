package com.service.impl;

import java.util.List;

import com.bean.ClassInform;
import com.bean.Courses;
import com.bean.Students;
import com.dao.StudentsDao;
import com.service.StudentsService;

public class StudentsServiceImpl implements StudentsService
{
	private StudentsDao studentsDao;
	
	public StudentsDao getStudentsDao() {
		return studentsDao;
	}

	public void setStudentsDao(StudentsDao studentsDao) {
		this.studentsDao = studentsDao;
	}

	public Students check(Students student) 
	{
		Students stu = studentsDao.check(student);
		if(stu!=null)
			return stu;
		return null;
	}

	public List<Courses> displayTodayCourses(Students student) {
		return studentsDao.displayTodayCourses(student);
	}
	
	public List<Courses> displayAllCourses(Students student) {
		return studentsDao.displayAllCourses(student);
	}

	public Students find(String SID) {
		return this.studentsDao.find(SID);
	}

	public String Signin(String CID,String SID)
	{
		return this.studentsDao.Signin(CID, SID);
	}

	public List<ClassInform> getCInform(String SID)
	{
		return this.studentsDao.getCInform(SID);
	}
}
