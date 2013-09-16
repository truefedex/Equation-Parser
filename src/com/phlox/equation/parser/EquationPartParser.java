package com.phlox.equation.parser;

import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.part.EquationPart;


public abstract class EquationPartParser {
	public abstract ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener);
	
	public class ParseResult {
		public EquationPart object;
		public int partLength;
	}
	
	public interface NestedEquationParseListener {
		EquationPart onNestedEquationFound(String subPart) throws UnknownEntityException;
	}
}
