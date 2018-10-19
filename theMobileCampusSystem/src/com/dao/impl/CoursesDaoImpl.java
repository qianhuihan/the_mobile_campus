package com.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Courses;
import com.bean.SignInTable;
import com.bean.Students;
import com.bean.Teachers;
import com.dao.CoursesDao;

public class CoursesDaoImpl extends HibernateDaoSupport implements CoursesDao
{
	@SuppressWarnings("unchecked")
	public Courses find(String CID) 
	{
		String sql="from Courses where CID=?";
		List<Courses> list=this.getHibernateTemplate().find(sql,CID);
		if(list.get(0)!=null)
		{
			return list.get(0);
		}
		else return null;
	}
	
	@SuppressWarnings("unchecked")
	public Teachers findt(Courses course)
	{
		String sql2="from Teachers where TID=?";
		List<Teachers> list2=this.getHibernateTemplate().find(sql2,course.getTID());
		if(list2.get(0)!=null)
			return list2.get(0);
		return null;
			//ActionContext.getContext().getSession().put("CTeacher", list2.get(0));
	}

	@SuppressWarnings("unchecked")
	public List<Students> rollcallTable(String CID) 
	{
		String sql="from Students as s where s.Sclass in (select cl.classname from Clacourse as cl where cl.CID=?)";
		List<Students> list=this.getHibernateTemplate().find(sql,CID);
		if(list!=null)
			return list;
		return null;
	}

	@SuppressWarnings("unchecked")
	public String signinTable(String CID,int stime) 
	{
		String message=null;
		try
		{
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date);
			DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			String time2 = format2.format(date);
			String sql="from SignInTable where CID=? AND Sdate like ?";
			List<SignInTable> list2=this.getHibernateTemplate().find(sql,new String[]{CID,time2+"%"});
			if(list2!=null && list2.size()!=0 && list2.get(0)!=null)
				message="签到接口今天已开启，无法再次开启！";
			else{
				long seconds = System.currentTimeMillis()+60000*stime;
				List<Students> list=this.rollcallTable(CID);
				SignInTable table = new SignInTable();
				table.setCID(CID);
				table.setSdate(time);
				table.setSID(null);
				table.setDeadline(seconds);
				
				if(list!=null)
				{
					for(int i=0;i<list.size();i++)
					{
						if(list.get(i).getSID().equals(table.getSID()))
							continue;
						else
						{
							table.setSID(list.get(i).getSID());
							this.getHibernateTemplate().save(table);
						}
					}
					message="成功开启签到接口！";
				}

			}
		}
		catch(Exception e)
		{
			message="无法成功开启签到接口！";
			e.printStackTrace();
		}
		return message;
	}
	
	@SuppressWarnings("unchecked")
	public int classNum(String CID)
	{
		String sql="FROM Students where Sclass in (select classname from Clacourse where CID="+CID+")";
		List<Students> list = this.getHibernateTemplate().find(sql);
		int num = list.size();
		return num;
	}
}
