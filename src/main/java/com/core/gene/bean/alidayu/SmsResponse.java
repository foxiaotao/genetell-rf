package com.core.gene.bean.alidayu;

public class SmsResponse {
	//属性全定义成object 类型 目的在于防止json中某个属性对应json的key为空，又不报错
	private Object error_response;//失败的时候 才有值
	private Object alibaba_aliqin_fc_sms_num_send_response; //成功的时候才有值
	
	private boolean success;//不是json中的数据，另外设计逻辑字段
	
	public Object getError_response() {
		return error_response;
	}
	public void setError_response(Object error_response) {
		this.error_response = error_response;
	}
	public Object getAlibaba_aliqin_fc_sms_num_send_response() {
		return alibaba_aliqin_fc_sms_num_send_response;
	}
	public void setAlibaba_aliqin_fc_sms_num_send_response(Object alibaba_aliqin_fc_sms_num_send_response) {
		this.alibaba_aliqin_fc_sms_num_send_response = alibaba_aliqin_fc_sms_num_send_response;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
