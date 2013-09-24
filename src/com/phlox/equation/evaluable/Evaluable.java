package com.phlox.equation.evaluable;

import java.util.HashMap;

public interface Evaluable {
	double evaluate(HashMap<String, Double> variables);
	public boolean readyForEval();
}
