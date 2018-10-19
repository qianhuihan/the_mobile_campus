package com.dao;

import java.util.List;

import com.bean.Resource;

public interface ResourceDao 
{
	public List<Resource> display();
	public String addDown(int RID);
	public String upload(Resource resource);
}
