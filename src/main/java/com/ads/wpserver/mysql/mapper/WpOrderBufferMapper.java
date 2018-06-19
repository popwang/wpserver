package com.ads.wpserver.mysql.mapper;

import org.apache.ibatis.annotations.Param;

import com.ads.wpserver.mysql.model.WpOrderBuffer;
/**
 * 指令缓存表
 * @author pactera
 *
 */
public interface WpOrderBufferMapper {
	/**
	 * 查询设备最新的待发送指令
	 * @param name
	 * @return
	 */
	public WpOrderBuffer selectOrderBufferByName(@Param("v_equipment_name") String name);
	/**
	 * 指令发送成功后执行指令计数器+1
	 * @param name
	 */
	public void updateOrderBufferFlag(@Param("v_equipment_name") String name);
}
