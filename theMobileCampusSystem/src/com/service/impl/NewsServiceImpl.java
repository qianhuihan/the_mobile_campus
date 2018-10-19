package com.service.impl;

import java.util.List;

import com.bean.News;
import com.dao.NewsDao;
import com.service.NewsService;

public class NewsServiceImpl implements NewsService
{
	private NewsDao newsDao;
	
	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public List<News> display()
	{
		return this.newsDao.display();
	}
	
}
