package com.phlox.equation.parser.part;

import com.phlox.equation.evaluable.Division;
import com.phlox.equation.parser.EquationPartParser;

public class DivisionParser extends EquationPartParser {

	private static final int ONE_CHAR_LENGTH = 1;
	private static final char DIVISION_CHAR = '/';

	@Override
	public ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener) {
		if (equation.charAt(fromPos) == DIVISION_CHAR) {
			return new ParseResult(new Division(), ONE_CHAR_LENGTH);
		} else {
			return null;
		}
	}
}
