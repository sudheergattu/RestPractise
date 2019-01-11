package com.practise.rest.exceptions;

public class UserServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3817695676198292923L;

	public UserServiceException(String message) {
		super(message);
	}
}
