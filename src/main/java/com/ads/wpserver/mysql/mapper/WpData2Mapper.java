package com.ads.wpserver.mysql.mapper;

import com.ads.wpserver.mysql.model.WpData;
/**
 * 状态数据操作mapper
 * 这个表中只有最新的一条数据
 * @author pactera
 *
 */
public interface WpData2Mapper {
	/**
	 * 保存状态数据
	 * @param wp
	 */
	public void saveWpData2(WpData wp);
	/**
	 * 删除状态数据
	 */
	public void deleteWpData2(WpData wp);
}
