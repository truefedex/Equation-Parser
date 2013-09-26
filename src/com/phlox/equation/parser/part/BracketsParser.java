package com.phlox.equation.parser.part;

import com.phlox.equation.parser.EquationPartParser;
import com.phlox.equation.parser.exceprion.EmptyEquationException;
import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.parser.exceprion.WrongSyntaxException;

public class BracketsParser extends EquationPartParser {

	private static final char OPEN_BRACKET_CHAR = '(';
	private static final char CLOSE_BRACKET_CHAR = ')';

	@Override
	public ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener)
			throws UnknownEntityException, EmptyEquationException, WrongSyntaxException {
		if (equation.charAt(fromPos) == OPEN_BRACKET_CHAR) {
			int opened_brackets = 1;
			for (int i = fromPos + 1; i < equation.length(); i++) {
				if (equation.charAt(i) == OPEN_BRACKET_CHAR) {
					opened_brackets++;
				} else if (equation.charAt(i) == CLOSE_BRACKET_CHAR) {
					opened_brackets--;
				}
				if (opened_brackets == 0) {
					int bracketsBlockLength = i + 1	- fromPos;
					int subEquationLength = bracketsBlockLength - 2;
					int subEquationStart = fromPos + 1;
					if (subEquationLength == 0) {
						throw new EmptyEquationException();
					}
					return new ParseResult(nestedListener.onNestedEquationFound(equation.substring(subEquationStart, subEquationStart + subEquationLength)), bracketsBlockLength);
				}
			}
		}

		return null;
	}

}
