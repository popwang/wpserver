package com.ads.wpserver.util;

import com.ads.wpserver.mysql.model.WpData;

public class ObjectUtil {
	public static WpData stringToObject(String[] strs){
		WpData wp = new WpData();
		wp.setV_equipment_name(strs[2]);
		wp.setI_work_model(Integer.parseInt(strs[3].substring(0, 1)));
		wp.setI_work_status(Integer.parseInt(strs[3].substring(1)));
		wp.setV_motor_status(strs[4]);
		wp.setV_motor_temp(strs[5]);
		wp.setV_motor_speed(strs[6]);
		wp.setD_latitude(Double.parseDouble(strs[15]));
		wp.setD_longitude(Double.parseDouble(strs[16]));
		return wp;
	}
	
	public static void main(String[] args) {
		String s = "SEND,DATAS,00000044,11,1111111111,11223344556677889900,11223344556677889900,,,,,,,,,30.811207,111.294708,END";
		String strs[] = s.split(",");
		for(int i=0;i<strs.length;i++){
			System.out.println("第"+i+"个元素："+strs[i]);
		}
	}
}
