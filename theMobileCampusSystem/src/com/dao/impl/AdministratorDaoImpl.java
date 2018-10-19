package com.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Administrator;
import com.dao.AdministratorDao;
import com.opensymphony.xwork2.ActionContext;

public class AdministratorDaoImpl extends HibernateDaoSupport implements AdministratorDao
{
	@SuppressWarnings("unchecked")
	public boolean check(Administrator administrator) 
	{ 
		if(administrator!=null)
		{
			String hql="from Administrator where loginname=? and Apassword=?";
			List<Administrator> list=this.getHibernateTemplate().find(hql,new String[]
					{administrator.getLoginname(),administrator.getPassword()});
			if(!list.isEmpty())
			{
				Administrator loginUser=list.get(0);
				ActionContext.getContext().getSession().put("user", loginUser);
				//ActionContext.getContext().getSession().put("userType", "administrator");
				return true;
			}
			else return false;
		}
		else return false;
	}
	
}
