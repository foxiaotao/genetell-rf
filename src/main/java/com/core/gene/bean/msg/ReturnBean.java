package com.core.gene.bean.msg;

import java.util.Date;
import java.util.List;

public class ReturnBean<T> {

    private String msg;

    private boolean success;

    private List<T> result;

    private int total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public ReturnBean(String msg, boolean success, List<T> result, int total) {
		super();
		this.msg = msg;
		this.success = success;
		this.result = result;
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public ReturnBean(String msg, boolean success) {
		this.msg = msg;
		this.success = success;
	}

    
}