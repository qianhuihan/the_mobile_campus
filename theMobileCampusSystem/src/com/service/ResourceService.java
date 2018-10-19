package com.service;

import java.util.List;

import com.bean.Resource;

public interface ResourceService
{
	public List<Resource> dispaly();
	public String addDown(int RID);
	public String upload(Resource resource);
}
