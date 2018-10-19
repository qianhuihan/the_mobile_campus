package com.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ClassInform;
import com.bean.Courses;
import com.bean.SignInTable;
import com.bean.Students;
import com.dao.StudentsDao;

public class StudentsDaoImpl extends HibernateDaoSupport implements StudentsDao 
{
	@SuppressWarnings("unchecked")
	public Students check(Students student) 
	{
		if(student!=null)
		{
			String hql="from Students where SID=? and Spassword=?";
			try{
				List<Students> list=this.getHibernateTemplate().find(hql,new String[]
					{student.getSID(),student.getPassword()});
				if(!list.isEmpty())
				{
					Students loginUser=list.get(0);
					//ActionContext.getContext().getSession().put("user", loginUser);
					//ActionContext.getContext().getSession().put("userType", "student");
					return loginUser;
				}
				else return null;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Courses> displayTodayCourses(Students student) 
	{
		if(student!=null)
		{
			Calendar c = Calendar.getInstance();
			c.setTime(new Date(System.currentTimeMillis()));
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			String wday=null;
			switch (dayOfWeek) {
			case 1:
				wday="周日";
				break;
			case 2:
				wday="周一";
				break;
			case 3:
				wday="周二";
				break;
			case 4:
				wday="周三";
				break;
			case 5:
				wday="周四";
				break;
			case 6:
				wday="周五";
				break;
			case 7:
				wday="周六";
				break;
			}
			//System.out.println(student.getSID());
			//System.out.println(wday);
			try
			{
				String hql="from Courses as c where c.CID in (select cl.CID from Students as s,Clacourse as cl where cl.classname=s.Sclass and s.SID=?) and c.wday=?";
				List<Courses> list=this.getHibernateTemplate().find(hql,new String[]
						{student.getSID(),wday});
				return list;
				//System.out.println(list.get(0).getCID());
				//ActionContext.getContext().getSession().put("course", list);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Courses> displayAllCourses(Students student) 
	{
		try{
			String hql2="from Courses as c where c.CID in (select cl.CID from Students as s,Clacourse as cl where cl.classname=s.Sclass and s.SID=?)";
			List<Courses> list2=this.getHibernateTemplate().find(hql2,student.getSID());
			//ActionContext.getContext().getSession().put("Allcourse", list2);
			return list2;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Students find(String SID) 
	{
		String hql="from Students where SID=?";
		List<Students> list=this.getHibernateTemplate().find(hql,SID);
		return list.get(0);
	}

	public String Signin(String CID, String SID) 
	{
		String message;
		Date date = new Date();
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String time2 = format2.format(date);
		String sql="from SignInTable where SID=? AND CID=? AND Sdate like ?";
		@SuppressWarnings("unchecked")
		List<SignInTable> sign=this.getHibernateTemplate().find(sql,new String[]{SID,CID,time2+"%"});
		if(!sign.isEmpty())
		{
			SignInTable signin=sign.get(0);
			long now=System.currentTimeMillis();
			if(signin.getStime()!=null && signin.getStime()!="")
			{
				message="已签到，无需重复签到！";
			}
			else if(now<signin.getDeadline())
			{
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = format.format(date);
				signin.setStime(time);
				this.getHibernateTemplate().update(signin);
				message="签到成功！";
			}
			else message="签到时间已经截止！";
		}
		else message="该课程未开启签到接口！";
		return message;
	}

	@SuppressWarnings("unchecked")
	public List<ClassInform> getCInform(String SID)
	{
		String sql="from ClassInform where TID in (select TID from Teachers where affiliation=(select Sclass from Students where SID=?))";
		try{
			List<ClassInform> list=this.getHibernateTemplate().find(sql, SID);
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
