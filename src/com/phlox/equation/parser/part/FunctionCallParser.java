package com.phlox.equation.parser.part;

import java.util.ArrayList;
import java.util.List;

import com.phlox.equation.evaluable.EvaluableEntity;
import com.phlox.equation.parser.EquationPartParser;
import com.phlox.equation.parser.exceprion.EmptyEquationException;
import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.parser.exceprion.WrongSyntaxException;

public abstract class FunctionCallParser extends EquationPartParser {

	private static final char OPEN_BRACKET_CHAR = '(';
	private static final char CLOSE_BRACKET_CHAR = ')';
	private static final char PARAMETERS_DIVIDER = ',';

	@Override
	public ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener)
			throws UnknownEntityException, EmptyEquationException, WrongSyntaxException {
		String functionName = getFunctionName();
		if (equation.startsWith(functionName + OPEN_BRACKET_CHAR, fromPos)) {
			int opened_brackets = 1;
			int paramStartPos = fromPos + functionName.length() + 1;
			List<EvaluableEntity> parameters = new ArrayList<EvaluableEntity>();
			for (int i = fromPos + functionName.length() + 1; i < equation.length(); i++) {
				if (equation.charAt(i) == OPEN_BRACKET_CHAR) {
					opened_brackets++;
				} else if (equation.charAt(i) == CLOSE_BRACKET_CHAR) {
					opened_brackets--;
				}
				if ((equation.charAt(i) == PARAMETERS_DIVIDER && opened_brackets == 1) ||
						opened_brackets == 0) {
					String paramString = equation.substring(paramStartPos, i);
					parameters.add(nestedListener.onNestedEquationFound(paramString));
					paramStartPos = i + 1;
				}
				if (opened_brackets == 0) {
					checkParameters(parameters);
					return new ParseResult(makeEntity(parameters), i + 1 - fromPos);
				}
			}
		}

		return null;
	}

	private void checkParameters(List<EvaluableEntity> parameters) throws WrongSyntaxException {
		if (parameters.size() != getParametersCount()) {
			throw new WrongSyntaxException(String.format("Inadequate number of parameters for function \"%s\"",
					getFunctionName()));
		}
	}

	public abstract int getParametersCount();

	public abstract String getFunctionName();
	
	public abstract EvaluableEntity makeEntity(List<EvaluableEntity> parameters) throws WrongSyntaxException;
}
