package com.phlox.equation.parser.part;

import com.phlox.equation.parser.EquationPartParser;
import com.phlox.equation.part.Addition;

public class AdditionParser extends EquationPartParser {

	private static final int ONE_CHAR_LENGTH = 1;
	private static final char ADDITION_CHAR = '+';

	@Override
	public ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener) {
		if (equation.charAt(fromPos) == ADDITION_CHAR) {
			return new ParseResult(new Addition(), ONE_CHAR_LENGTH);
		} else {
			return null;
		}
	}

}
