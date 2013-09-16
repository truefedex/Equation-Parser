package com.phlox.equation.parser;

import java.util.ArrayList;

import com.phlox.equation.parser.part.AdditionParser;
import com.phlox.equation.parser.part.DigitParser;

public class EquationPartParsersLibrary {
	private ArrayList<EquationPartParser> parsers = new ArrayList<EquationPartParser>();

	public EquationPartParsersLibrary() {
		super();
		addDefaultParsers();
	}

	private void addDefaultParsers() {
		parsers.add(new DigitParser());
		parsers.add(new AdditionParser());
	}

	public ArrayList<EquationPartParser> getParsers() {
		return parsers;
	}
	
}
