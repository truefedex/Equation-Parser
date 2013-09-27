package com.phlox.equation.evaluable;

import java.util.HashMap;

public class Max extends EvaluableEntity {
	private EvaluableEntity firstParam, secondParam;

	public Max(EvaluableEntity firstParam, EvaluableEntity secondParam) {
		super();
		this.firstParam = firstParam;
		this.secondParam = secondParam;
	}

	@Override
	public double evaluate(HashMap<String, Double> variables) {
		double firstValue = firstParam.evaluate(variables);
		double secondValue = secondParam.evaluate(variables);
		return firstValue > secondValue ? firstValue : secondValue;
	}

	@Override
	public boolean readyForEval() {
		return firstParam != null && secondParam != null;
	}

	public EvaluableEntity getFirstParam() {
		return firstParam;
	}

	public void setFirstParam(EvaluableEntity firstParam) {
		this.firstParam = firstParam;
	}

	public EvaluableEntity getSecondParam() {
		return secondParam;
	}

	public void setSecondParam(EvaluableEntity secondParam) {
		this.secondParam = secondParam;
	}
}
