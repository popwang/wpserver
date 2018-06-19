package com.ads.wpserver.mysql.mapper;

import com.ads.wpserver.mysql.model.WpData;
/**
 * 状态数据操作mapper
 * @author pactera
 *
 */
public interface WpDataMapper {
	/**
	 * 保存状态数据
	 * @param wp
	 */
	public void saveWpData(WpData wp);
}
