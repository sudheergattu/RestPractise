package com.practise.rest.model;

import java.util.Date;

public class ErrorMessage {

	private String message;
	private Date timestamp;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public ErrorMessage(String message, Date timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public ErrorMessage() {
		
	}
}
