package com.ads.wpserver.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ads.wpserver.mysql.mapper.WpOrderBufferMapper;
import com.ads.wpserver.mysql.model.WpOrderBuffer;

@Service
public class WpOrderBufferService {
	@Autowired
	private WpOrderBufferMapper wpOrderBufferMapper;
	
	public WpOrderBuffer selectOrderBufferByName(String name){
		WpOrderBuffer order = this.wpOrderBufferMapper.selectOrderBufferByName(name);
		return order;
	}
	
	public void updateOrderBufferFlag(String name){
		this.wpOrderBufferMapper.updateOrderBufferFlag(name);
	}
}
