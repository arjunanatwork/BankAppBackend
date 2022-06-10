package com.arjunanr.bankapp.expection;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BusinessException(String errMessage) {
		super(errMessage);
	}

}
