package com.phlox.equation.parser.exceprion;

public class UnknownEntityException extends Exception {
	
	public UnknownEntityException() {
		super();
	}

    public UnknownEntityException(String message) {
       super(message);
    }
}
