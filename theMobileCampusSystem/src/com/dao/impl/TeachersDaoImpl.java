package com.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ClassInform;
import com.bean.Courses;
import com.bean.RollCallTable;
import com.bean.SignInTable;
import com.bean.Teachers;
import com.dao.TeachersDao;

public class TeachersDaoImpl extends HibernateDaoSupport implements TeachersDao
{
	@SuppressWarnings("unchecked")
	public Teachers check(Teachers teacher) 
	{
		if(teacher!=null)
		{
			String hql="from Teachers where TID=? and Tpassword=?";
			try{
				List<Teachers> list=this.getHibernateTemplate().find(hql,new String[]
						{teacher.getTID(),teacher.getPassword()});
				if(!list.isEmpty())
				{
					Teachers loginUser=list.get(0);
					//ActionContext.getContext().getSession().put("user", loginUser);
					//ActionContext.getContext().getSession().put("userType", "teacher");
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
	public List<Courses> showCourse(Teachers teacher) 
	{
		if(teacher!=null)
		{
			Calendar c = Calendar.getInstance();
			c.setTime(new Date(System.currentTimeMillis()));
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			String wday=null;
			switch (dayOfWeek) {
			case 1:
				wday="����";
				break;
			case 2:
				wday="��һ";
				break;
			case 3:
				wday="�ܶ�";
				break;
			case 4:
				wday="����";
				break;
			case 5:
				wday="����";
				break;
			case 6:
				wday="����";
				break;
			case 7:
				wday="����";
				break;
			}
			//System.out.println(student.getSID());
			//System.out.println(wday);
			try{
				String hql="from Courses where TID=? and wday=?";
				List<Courses> todaylist=this.getHibernateTemplate().find(hql,new String[]
						{teacher.getTID(),wday});
				//System.out.println(list.get(0).getCID());
				//ActionContext.getContext().getSession().put("Tcourse", todaylist);
				return todaylist;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Courses> showAllCourse(Teachers teacher) 
	{
		try{
			String hql2="from Courses where TID=?";
			List<Courses> list=this.getHibernateTemplate().find(hql2,teacher.getTID());
			//ActionContext.getContext().getSession().put("TAllcourse", list);
			return list;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Teachers find(String TID) 
	{
		String hql="from Teachers where TID=?";
		List<Teachers> list=this.getHibernateTemplate().find(hql,TID);
		return list.get(0);
	}

	public String informClass(String TID, String title, String body) 
	{
		String message=null;
		ClassInform inform=new ClassInform();
		Date date=new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		inform.setTID(TID);
		inform.setTitle(title);
		inform.setBody(body);
		inform.setTime(time);
		try{
			this.getHibernateTemplate().save(inform);
			message="֪ͨ���ͳɹ���";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			message="֪ͨ����ʧ�ܣ������ԣ�";
		}
		return message;
	}
	
	public String rollcall(List<RollCallTable> list)
	{
		String message=null;
		try
		{
			for(int i=0;i<list.size();i++)
			{
				this.getHibernateTemplate().save(list.get(i));
			}
			message="������Ϣ¼��ɹ���";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			message="�������¼��ʧ�ܣ�";
		}
		return message;
	}

	@SuppressWarnings("unchecked")
	public List<RollCallTable> findRollcall(String CID) 
	{
		String sql="from RollCallTable where CID="+CID+" order by Rdate desc";
		List<RollCallTable> list=this.getHibernateTemplate().find(sql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SignInTable> findSignin(String CID) 
	{
		String sql="from SignInTable where CID="+CID+" order by Sdate desc";
		List<SignInTable> list=this.getHibernateTemplate().find(sql);
		return list;
	}
}
