package com.phlox.equation.parser.part;

import com.phlox.equation.evaluable.Substraction;
import com.phlox.equation.parser.EquationPartParser;

public class SubstractionParser extends EquationPartParser {

	private static final int ONE_CHAR_LENGTH = 1;
	private static final char SUBSTRACTION_CHAR = '-';

	@Override
	public ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener) {
		if (equation.charAt(fromPos) == SUBSTRACTION_CHAR) {
			return new ParseResult(new Substraction(), ONE_CHAR_LENGTH);
		} else {
			return null;
		}
	}

}
