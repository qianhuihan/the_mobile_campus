package com.service.impl;

import java.util.List;

import com.bean.Courses;
import com.bean.RollCallTable;
import com.bean.SignInTable;
import com.bean.Teachers;
import com.dao.TeachersDao;
import com.service.TeachersService;

public class TeachersServiceImpl implements TeachersService
{
	private TeachersDao teachersDao;
	
	public TeachersDao getTeachersDao() {
		return teachersDao;
	}

	public void setTeachersDao(TeachersDao teachersDao) {
		this.teachersDao = teachersDao;
	}

	public Teachers check(Teachers teacher) 
	{
		Teachers tea = teachersDao.check(teacher);
		if(tea!=null)
			return tea;
		return null;
	}

	public List<Courses> showCourse(Teachers teacher) 
	{
		return teachersDao.showCourse(teacher);
	}
	
	public List<Courses> showAllCourse(Teachers teacher) 
	{
		return teachersDao.showAllCourse(teacher);
	}

	public Teachers find(String TID) {
		return this.teachersDao.find(TID);
	}
	
	public String informClass(String TID, String title, String body) 
	{
		return this.teachersDao.informClass(TID, title, body);
	}
	
	public String rollcall(List<RollCallTable> list)
	{
		return this.teachersDao.rollcall(list);
	}

	public List<RollCallTable> findRollcall(String CID)
	{
		return this.teachersDao.findRollcall(CID);
	}
	
	public List<SignInTable> findSignin(String CID)
	{
		return this.teachersDao.findSignin(CID);
	}
}
