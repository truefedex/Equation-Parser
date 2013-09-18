package com.phlox.equation.part;

import java.util.HashMap;

public abstract class EquationPart {
	
	private static final int NO_OPERATOR = -1;

	public abstract double calculateValue(HashMap<String, Double> variables);

	public boolean isOperator() {
		return getOperatorPriority() != NO_OPERATOR;
	}
	
	public int getOperatorPriority() {
		return NO_OPERATOR;
	}

	public boolean setLeftOperand(EquationPart equationPart) {
		return false;
	}

	public boolean setRightOperand(EquationPart equationPart) {
		return false;
	}
}
