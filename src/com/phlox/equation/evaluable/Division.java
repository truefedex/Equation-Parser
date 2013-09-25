package com.phlox.equation.evaluable;

import java.util.HashMap;

public class Division extends OperatorEntity {

	@Override
	public double evaluate(HashMap<String, Double> variables) {
		return getLeftOperand().evaluate(variables) / getRightOperand().evaluate(variables);
	}

	@Override
	public boolean isAppropriateOperands(boolean leftOperand, boolean rightOperand) {
		return leftOperand && rightOperand;
	}

	@Override
	public boolean readyForEval() {
		return getLeftOperand() != null && getRightOperand() != null;
	}

	@Override
	public int calcOperationPriority(boolean leftOperand, boolean rightOperand) {
		return NORMAL_PRIORITY;
	}
}
