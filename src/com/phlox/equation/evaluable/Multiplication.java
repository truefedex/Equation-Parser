package com.phlox.equation.evaluable;

import java.util.HashMap;

public class Multiplication extends OperatorEntity {

	@Override
	public double evaluate(HashMap<String, Double> variables) {
		return getLeftOperand().evaluate(variables) * getRightOperand().evaluate(variables);
	}

	@Override
	public int getOperatorPriority() {
		return 1;
	}
}
