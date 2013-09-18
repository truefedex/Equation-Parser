package com.phlox.equation.parser;

import com.phlox.equation.evaluable.EvaluableEntity;
import com.phlox.equation.parser.exceprion.EmptyEquationException;
import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.parser.exceprion.WrongSyntaxException;


public abstract class EquationPartParser {
	public abstract ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener);
	
	public class ParseResult {
		public EvaluableEntity object;
		public int partLength;
		public ParseResult(EvaluableEntity object, int partLength) {
			super();
			this.object = object;
			this.partLength = partLength;
		}
	}
	
	public interface NestedEquationParseListener {
		EvaluableEntity onNestedEquationFound(String subPart) throws UnknownEntityException, EmptyEquationException, WrongSyntaxException;
	}
}
