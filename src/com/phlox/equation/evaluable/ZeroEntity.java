package com.phlox.equation.evaluable;

import java.util.HashMap;

public class ZeroEntity extends EvaluableEntity {

	@Override
	public double evaluate(HashMap<String, Double> variables) {
		return 0;
	}

}
