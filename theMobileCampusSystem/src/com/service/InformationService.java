package com.service;

import java.util.List;

import com.bean.Information;

public interface InformationService 
{
	public List<Information> showAllT(String TID);
	public List<Information> showAllS(String SID);
	public List<Information> showSome(String SID,String TID);
	public boolean save(Information inform);
}
