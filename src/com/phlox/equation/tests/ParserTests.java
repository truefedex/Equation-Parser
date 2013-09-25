package com.phlox.equation.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.phlox.equation.evaluable.Addition;
import com.phlox.equation.evaluable.Digit;
import com.phlox.equation.evaluable.EvaluableEntity;
import com.phlox.equation.evaluable.Substraction;
import com.phlox.equation.parser.EquationParser;
import com.phlox.equation.parser.exceprion.EmptyEquationException;
import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.parser.exceprion.WrongSyntaxException;

public class ParserTests {

	private EquationParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new EquationParser();
	}

	@Test
	public void testOneDigitParsing() throws UnknownEntityException, EmptyEquationException, WrongSyntaxException {
		EvaluableEntity parsedEquation = parser.parse("2");
		assertTrue(parsedEquation instanceof Digit);
	}
	
	@Test
	public void testAdditionOperatorParsing() throws UnknownEntityException, EmptyEquationException, WrongSyntaxException {
		EvaluableEntity parsedEquation = parser.parse("2+2");
		assertTrue(parsedEquation instanceof Addition);
	}
	
	@Test
	public void testUnarSubstractionOperatorParsing() throws UnknownEntityException, EmptyEquationException, WrongSyntaxException {
		EvaluableEntity parsedEquation = parser.parse("2+-2");
		assertTrue(parsedEquation instanceof Addition);
	}
	
	@Test
	public void testUnarSubstractionOperatorParsing2() throws UnknownEntityException, EmptyEquationException, WrongSyntaxException {
		EvaluableEntity parsedEquation = parser.parse("-2");
		assertTrue(parsedEquation instanceof Substraction);
	}
}
