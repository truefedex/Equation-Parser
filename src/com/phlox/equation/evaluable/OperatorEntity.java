package com.phlox.equation.evaluable;

public abstract class OperatorEntity extends EvaluableEntity implements Operator {
	/**
	 * suitable for +,- operators
	 */
	public static final int LOW_PRIORITY = 0;
	/**
	 * suitable for *,/ operators
	 */
	public static final int NORMAL_PRIORITY = 1;
	/**
	 * suitable for ^ operator
	 */
	public static final int HIGH_PRIORITY = 2;
	/**
	 * suitable for unary operators
	 */
	public static final int UNARY_PRIORITY = 3;

	private EvaluableEntity leftOperand = null;
	private EvaluableEntity rightOperand = null;

	@Override
	public boolean setLeftOperand(EvaluableEntity entity) {
		leftOperand = entity;
		return true;
	}

	@Override
	public boolean setRightOperand(EvaluableEntity entity) {
		rightOperand = entity;
		return true;
	}

	public EvaluableEntity getLeftOperand() {
		return leftOperand;
	}

	public EvaluableEntity getRightOperand() {
		return rightOperand;
	}

	/**
	 * Should decide operation priority depend of operands availability. For
	 * example "-" can act like binary operator with low priority, if both
	 * operands available. But if only one right operator available - it will
	 * act like unary operator with highest priority.
	 */
	@Override
	public int calcOperationPriority(boolean leftOperand, boolean rightOperand) {
		return LOW_PRIORITY;
	}
}
