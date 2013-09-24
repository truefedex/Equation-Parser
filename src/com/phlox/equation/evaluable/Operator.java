package com.phlox.equation.evaluable;

public interface Operator {
	public boolean setLeftOperand(EvaluableEntity entity);
	public boolean setRightOperand(EvaluableEntity entity);
	public boolean isAppropriateOperands(boolean leftOperand, boolean rightOperand);
	public int calcOperationPriority(boolean leftOperand, boolean rightOperand);
}
