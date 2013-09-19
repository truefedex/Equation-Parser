package com.phlox.equation.parser;

import java.util.ArrayList;

import com.phlox.equation.parser.part.AdditionParser;
import com.phlox.equation.parser.part.DigitParser;
import com.phlox.equation.parser.part.MultiplicationParser;
import com.phlox.equation.parser.part.SubstractionParser;

public class EquationPartParsersLibrary {
	private ArrayList<EquationPartParser> parsers = new ArrayList<EquationPartParser>();
	
	private static EquationPartParsersLibrary instance = null;

	public EquationPartParsersLibrary() {
		super();
		addDefaultParsers();
	}

	private void addDefaultParsers() {
		parsers.add(new DigitParser());
		parsers.add(new AdditionParser());
		parsers.add(new SubstractionParser());
		parsers.add(new MultiplicationParser());
	}

	public ArrayList<EquationPartParser> getParsers() {
		return parsers;
	}

	public static EquationPartParsersLibrary getInstance() {
		if (instance == null) {
			instance = new EquationPartParsersLibrary();
		}
		return instance;
	}
}
