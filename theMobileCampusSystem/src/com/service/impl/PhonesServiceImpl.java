package com.service.impl;

import java.util.List;

import com.bean.Phones;
import com.dao.PhonesDao;
import com.service.PhonesService;

public class PhonesServiceImpl implements PhonesService
{
	private PhonesDao phonesDao;
	
	public PhonesDao getPhonesDao() {
		return phonesDao;
	}

	public void setPhonesDao(PhonesDao phonesDao) {
		this.phonesDao = phonesDao;
	}

	public List<Phones> display() 
	{
		return this.phonesDao.display();
	}
	
}
