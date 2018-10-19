package com.androidAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.bean.Courses;
import com.bean.News;
import com.bean.Phones;
import com.bean.Resource;
import com.bean.Students;
import com.bean.Teachers;


@SuppressWarnings("serial")
public class LoginAndroidAction extends BaseAction
{
	private Map<String, Object> jdata;
	private String loginname=null;
	private String password=null;
	private String usertype=null;
	
	public Map<String, Object> getJdata() {
		return jdata;
	}
	public void setJdata(Map<String, Object> jdata) {
		this.jdata = jdata;
	}
	
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

	public String execute() {
        //HttpServletRequest request = ServletActionContext.getRequest(); 
        //String loginname = request.getParameter("loginname");
        //String password = request.getParameter("password");
        //String usertype = request.getParameter("usertype");
		jdata = new HashMap<String, Object>();
		
        try
	    {
	        if(usertype.equals("student"))
			{
				Students student=new Students();
				student.setSID(loginname);
				student.setPassword(password);
		        //System.out.println("µΩ’‚¿Ô");
				Students loginUser = this.studentsService.check(student);
				if(loginUser != null)
				{
		            jdata.put("success", "student"); 
		            jdata.put("loginUser",loginUser);

					List<Courses> list=this.studentsService.displayAllCourses(loginUser);
					List<Courses> list2=this.studentsService.displayTodayCourses(loginUser);
					jdata.put("todayCourses", list2);
					jdata.put("allCourses", list);
					
					List<News> news=this.newsService.display();
					List<Phones> phones=this.phonesService.display();
					List<Resource> resource=this.resourceService.dispaly();
					jdata.put("resource", resource);
					jdata.put("phones", phones);
					jdata.put("news", news);
				}
				else jdata.put("success", "failed");
			}
			else if(usertype.equals("teacher"))
			{
				Teachers teacher=new Teachers();
				teacher.setTID(loginname);
				teacher.setPassword(password);
				Teachers loginUser = this.teachersService.check(teacher);
				if(loginUser!=null)
				{
		            jdata.put("success", "teacher"); 
		            jdata.put("loginUser",loginUser);

					List<Courses> list = this.teachersService.showCourse(loginUser);
					List<Courses> list2 = this.teachersService.showAllCourse(loginUser);
					jdata.put("todayCourses", list);
					jdata.put("allCourses", list2);

					List<News> news=this.newsService.display();
					List<Phones> phones=this.phonesService.display();
					List<Resource> resource=this.resourceService.dispaly();
					jdata.put("resource", resource);
					jdata.put("phones", phones);
					jdata.put("news", news);
				}
				else jdata.put("success", "failed");
			}
			else
			{
				jdata.put("success", "failed"); 
			}

	        ResultUtils.toJson(ServletActionContext.getResponse(), jdata);
	    }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		return null;

    }
}