package com.phlox.equation.parser.exceprion;

public class UnknownEntityException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public UnknownEntityException() {}

    public UnknownEntityException(String message)
    {
       super(message);
    }
}
