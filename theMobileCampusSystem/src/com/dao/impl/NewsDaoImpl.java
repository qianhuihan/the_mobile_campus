package com.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.NewsDao;
import com.bean.News;

public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao
{
	@SuppressWarnings("unchecked")
	public List<News> display() 
	{
		String sql="from News as n order by n.NID desc";
		List<News> list=this.getHibernateTemplate().find(sql);
		//System.out.println(list.get(0).getNID());
		//ActionContext.getContext().getSession().put("news", list);
		return list;
	}

}
