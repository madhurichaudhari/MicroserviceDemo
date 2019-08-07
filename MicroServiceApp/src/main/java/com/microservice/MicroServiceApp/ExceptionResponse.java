package com.microservice.MicroServiceApp;

import java.util.Date;

public class ExceptionResponse {
	
	public ExceptionResponse(Date timestamp, String msg, String details) {
		super();
		this.msg = msg;
		this.timestamp = timestamp;
		this.details = details;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	private String msg;
	private Date timestamp;
	private String details;
	

}
