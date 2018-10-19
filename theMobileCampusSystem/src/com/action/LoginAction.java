package com.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bean.Administrator;
import com.bean.ClassInform;
import com.bean.Courses;
import com.bean.Students;
import com.bean.Teachers;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class LoginAction extends BaseAction
{
	private String loginname;
	private String password;
	private String usertype;
	
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	public String execute()
	{
		//System.out.println(usertype);
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
		ActionContext.getContext().getSession().put("weekday",wday);
		if(this.usertype.equals("student"))
		{
			Students student=new Students();
			student.setSID(loginname);
			student.setPassword(password);
			Students loginUser = this.studentsService.check(student);
			if(loginUser!=null)
			{
				List<Courses> list=this.studentsService.displayAllCourses(student);
				List<Courses> list2=this.studentsService.displayTodayCourses(student);
				List<ClassInform> list3=this.studentsService.getCInform(loginUser.getSID());
				
				ActionContext.getContext().getSession().put("Allcourse", list);
				ActionContext.getContext().getSession().put("course", list2);
				ActionContext.getContext().getSession().put("classInform", list3);
				ActionContext.getContext().getSession().put("user", loginUser);
				ActionContext.getContext().getSession().put("loginmessage","学生" );
				ActionContext.getContext().getSession().put("type","学生" );
				
				return SUCCESS;
			}
		}
		else if(this.usertype.equals("teacher"))
		{
			Teachers teacher=new Teachers();
			teacher.setTID(loginname);
			teacher.setPassword(password);
			Teachers loginUser = this.teachersService.check(teacher);
			if(loginUser!=null)
			{
				List<Courses> todaylist = this.teachersService.showCourse(teacher);
				List<Courses> list = this.teachersService.showAllCourse(teacher);
				ActionContext.getContext().getSession().put("Tcourse", todaylist);
				ActionContext.getContext().getSession().put("TAllcourse", list);
				ActionContext.getContext().getSession().put("user", loginUser);
				ActionContext.getContext().getSession().put("loginmessage","教师" );
				ActionContext.getContext().getSession().put("type","教师" );
				return SUCCESS;
			}
		}
		else
		{
			Administrator administrator = new Administrator();
			administrator.setLoginname(loginname);
			administrator.setPassword(password);
			if(this.administratorService.check(administrator))
			{
				ActionContext.getContext().getSession().put("loginmessage","管理员" );
				ActionContext.getContext().getSession().put("type","管理员" );
				return SUCCESS;
			}
		}
		ActionContext.getContext().getSession().put("loginmessage","失败" );
		return INPUT;
	}
	
}
