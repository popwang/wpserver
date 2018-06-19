package com.ads.wpserver.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.wpserver.mysql.mapper.WpDataMapper;
import com.ads.wpserver.mysql.model.WpData;

@Service
public class WpDataService {
	@Autowired
	private WpDataMapper wpDataMapper;
	
	public void saveWpData(WpData wp){
		wpDataMapper.saveWpData(wp);
	}
}
