package com.ads.wpserver.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ads.wpserver.mysql.mapper.WpData2Mapper;
import com.ads.wpserver.mysql.model.WpData;

@Service
public class WpDataService2 {
	@Autowired
	private WpData2Mapper wpData2Mapper;
	/**
	 * 启用事务，先删除后插入，保持表中只有一条最新数据
	 * @param wp
	 */
	@Transactional
	public void saveWpData(WpData wp){
		wpData2Mapper.deleteWpData2(wp);
		wpData2Mapper.saveWpData2(wp);
	}
}
