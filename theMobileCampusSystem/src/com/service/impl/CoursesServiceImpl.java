package com.service.impl;

import java.util.List;

import com.bean.Courses;
import com.bean.Students;
import com.bean.Teachers;
import com.dao.CoursesDao;
import com.service.CoursesService;

public class CoursesServiceImpl implements CoursesService
{
	private CoursesDao coursesDao;
	
	public CoursesDao getCoursesDao() {
		return coursesDao;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public Courses find(String CID) 
	{
		return this.coursesDao.find(CID);
	}

	public Teachers findt(Courses course) 
	{
		return this.coursesDao.findt(course);
	}

	public List<Students> rollcallTable(String CID) 
	{
		return this.coursesDao.rollcallTable(CID);
	}

	public String signinTable(String CID,int stime) 
	{
		return this.coursesDao.signinTable(CID,stime);
	}
	
	public int classNum(String CID)
	{
		return this.coursesDao.classNum(CID);
	}
}
