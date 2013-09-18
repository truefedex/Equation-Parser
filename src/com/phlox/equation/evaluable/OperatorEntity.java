package com.phlox.equation.evaluable;

public abstract class OperatorEntity extends EvaluableEntity implements Operator {
	private EvaluableEntity leftOperand = new ZeroEntity();
	private EvaluableEntity rightOperand = new ZeroEntity();

	@Override
	public int getOperatorPriority() {
		return 0;
	}

	@Override
	public void setLeftOperand(EvaluableEntity entity) {
		leftOperand = entity;
	}

	@Override
	public void setRightOperand(EvaluableEntity entity) {
		rightOperand = entity;
	}

	public EvaluableEntity getLeftOperand() {
		return leftOperand;
	}

	public EvaluableEntity getRightOperand() {
		return rightOperand;
	}
}
