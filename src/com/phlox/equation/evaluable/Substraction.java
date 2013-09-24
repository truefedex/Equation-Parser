package com.phlox.equation.evaluable;

import java.util.HashMap;

public class Substraction extends OperatorEntity {

	@Override
	public double evaluate(HashMap<String, Double> variables) {
		double leftValue = getLeftOperand() != null ? getLeftOperand().evaluate(variables) : 0;
		return leftValue - getRightOperand().evaluate(variables);
	}

	@Override
	public boolean isAppropriateOperands(boolean leftOperand, boolean rightOperand) {
		return rightOperand;
	}

	@Override
	public boolean readyForEval() {
		return getRightOperand() != null;
	}

	@Override
	public int calcOperationPriority(boolean leftOperand, boolean rightOperand) {
		if (leftOperand && rightOperand) {
			return LOW_PRIORITY;
		} else {//just right operand
			return UNARY_PRIORITY;
		}
	}
}
