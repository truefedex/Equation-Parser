package com.phlox.equation.parser.exceprion;

public class UnknownEntityException extends Exception {
	
	public UnknownEntityException() {}

    public UnknownEntityException(String message) {
       super(message);
    }
}
