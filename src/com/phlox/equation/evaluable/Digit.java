package com.phlox.equation.evaluable;

import java.util.HashMap;

public class Digit extends EvaluableEntity {
	private double value = 0;	

	public Digit(double value) {
		super();
		this.value = value;
	}

	@Override
	public double evaluate(HashMap<String, Double> variables) {
		return value;
	}

	@Override
	public boolean readyForEval() {
		return true;
	}

}
