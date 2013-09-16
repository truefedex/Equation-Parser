package com.phlox.equation.part;

import java.util.HashMap;

public abstract class EquationPart {
	public abstract double calculateValue(HashMap<String, Double> variables);
}
