package com.phlox.equation.evaluable;

public interface Operator {
	public int getOperatorPriority();
	public void setLeftOperand(EvaluableEntity entity);
	public void setRightOperand(EvaluableEntity entity);
}
