package com.phlox.equation.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.phlox.equation.evaluable.Addition;
import com.phlox.equation.evaluable.Digit;
import com.phlox.equation.evaluable.EvaluableEntity;

@RunWith(JUnit4.class)
public class EvaluableTests {

	private HashMap<String, Double> variables;

	@Before
	public void setUp() throws Exception {
		variables = new HashMap<String, Double>();
		variables.put("x", 22.0);
		variables.put("y", 33.0);
	}

	@After
	public void tearDown() throws Exception {
		variables.clear();
		variables = null;
	}
	
	@Test
	public void testDigitEvaluable() {
		Digit digit = new Digit(10.0);
		assertEquals(10.0, digit.evaluate(variables), 0.0);
	}

	@Test
	public void testAdditionEvaluable() {
		Addition addition = new Addition();
		addition.setRightOperand(new Digit(10.0));
		assertEquals(10.0, addition.evaluate(variables), 0.0);
	}

	@Test
	public void testAdditionEvaluable2() {
		Addition addition = new Addition();
		addition.setRightOperand(new Digit(10.0));
		addition.setLeftOperand(new EvaluableEntity() {
			@Override
			public boolean readyForEval() {
				return true;
			}
			
			@Override
			public double evaluate(HashMap<String, Double> variables) {
				return 5.0;
			}
		});
		assertEquals(15.0, addition.evaluate(variables), 0.0);
	}
}
