package com.phlox.equation;

import java.util.HashMap;

import com.phlox.equation.evaluable.EvaluableEntity;
import com.phlox.equation.parser.EquationParser;
import com.phlox.equation.parser.exceprion.EmptyEquationException;
import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.parser.exceprion.WrongSyntaxException;

public class Sample {

	public static void main(String[] args) {
		EquationParser parser = new EquationParser();
		try {
			EvaluableEntity parsedEquation = parser.parse("2+2*(1+1)");
			System.out.println("2+2*(1+1)=" + parsedEquation.evaluate(new HashMap<String, Double>()));
		} catch (UnknownEntityException e) {
			e.printStackTrace();
		} catch (EmptyEquationException e) {
			e.printStackTrace();
		} catch (WrongSyntaxException e) {
			e.printStackTrace();
		}
	}

}
