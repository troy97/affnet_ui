package com.unkur.affnetui.dao.exceptions;

@SuppressWarnings("serial")
public class NoSuchEntityException extends DaoException {

	public NoSuchEntityException() {
		super();
	}

	public NoSuchEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchEntityException(String message) {
		super(message);
	}

	public NoSuchEntityException(Throwable cause) {
		super(cause);
	}
	
}
