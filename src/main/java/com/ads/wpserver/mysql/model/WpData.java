package com.ads.wpserver.mysql.model;
/**
 * 状态数据实体类
 * @author pactera
 */
public class WpData {
	private String v_equipment_name;
	private String v_motor_status;
	private String v_motor_temp;
	private String v_motor_speed;
	private Double d_longitude;
	private Double d_latitude;
	private String dtm_create;
	private Integer i_work_model;
	private String v_work_model;
	private Integer i_work_status;
	private String v_work_status;
	
	public String getV_equipment_name() {
		return v_equipment_name;
	}
	public void setV_equipment_name(String v_equipment_name) {
		this.v_equipment_name = v_equipment_name;
	}
	public String getV_motor_status() {
		return v_motor_status;
	}
	public void setV_motor_status(String v_motor_status) {
		this.v_motor_status = v_motor_status;
	}
	public String getV_motor_temp() {
		return v_motor_temp;
	}
	public void setV_motor_temp(String v_motor_temp) {
		this.v_motor_temp = v_motor_temp;
	}
	public String getV_motor_speed() {
		return v_motor_speed;
	}
	public void setV_motor_speed(String v_motor_speed) {
		this.v_motor_speed = v_motor_speed;
	}
	public Double getD_longitude() {
		return d_longitude;
	}
	public void setD_longitude(Double d_longitude) {
		this.d_longitude = d_longitude;
	}
	public Double getD_latitude() {
		return d_latitude;
	}
	public void setD_latitude(Double d_latitude) {
		this.d_latitude = d_latitude;
	}
	public String getDtm_create() {
		return dtm_create;
	}
	public void setDtm_create(String dtm_create) {
		this.dtm_create = dtm_create;
	}
	public Integer getI_work_model() {
		return i_work_model;
	}
	public void setI_work_model(Integer i_work_model) {
		this.i_work_model = i_work_model;
	}
	public String getV_work_model() {
		return v_work_model;
	}
	public void setV_work_model(String v_work_model) {
		this.v_work_model = v_work_model;
	}
	public Integer getI_work_status() {
		return i_work_status;
	}
	public void setI_work_status(Integer i_work_status) {
		this.i_work_status = i_work_status;
	}
	public String getV_work_status() {
		return v_work_status;
	}
	public void setV_work_status(String v_work_status) {
		this.v_work_status = v_work_status;
	}
}
