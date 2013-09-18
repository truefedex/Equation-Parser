package com.phlox.equation.parser.part;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.phlox.equation.evaluable.Digit;
import com.phlox.equation.parser.EquationPartParser;

public class DigitParser extends EquationPartParser {

	@Override
	public ParseResult tryParse(String equation, int fromPos, NestedEquationParseListener nestedListener) {
		Pattern pattern = Pattern.compile("\\d+(\\.{0,1}(\\d+?))?");
	    Matcher matcher = pattern.matcher(equation);
	    if (matcher.find(fromPos) && matcher.start() == fromPos) {
	    	double value = Double.valueOf(equation.substring(matcher.start(), matcher.end()));
	    	return new ParseResult(new Digit(value), matcher.end() - matcher.start());
	    } else {
	    	return null;
	    }
	}

}
