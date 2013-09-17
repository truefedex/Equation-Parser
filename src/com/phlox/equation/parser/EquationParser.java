package com.phlox.equation.parser;

import java.util.ArrayList;

import com.phlox.equation.parser.EquationPartParser.NestedEquationParseListener;
import com.phlox.equation.parser.EquationPartParser.ParseResult;
import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.part.EquationPart;

public final class EquationParser implements NestedEquationParseListener {
	
	public EquationPart parse(String equationString) throws UnknownEntityException {
		EquationPartParsersLibrary library = new EquationPartParsersLibrary();
		ArrayList<EquationPart> parts = new ArrayList<EquationPart>();
		int position = 0;
		while (position < equationString.length()) {
			boolean found = false;
			for (EquationPartParser parser : library.getParsers()) {
				ParseResult parseResult = parser.tryParse(equationString, position, this);
				if (parseResult.object != null) {
					parts.add(parseResult.object);
					position += parseResult.partLength;
					found = true;
					break;
				}
			}
			if (!found) {
				throw new UnknownEntityException(String.format("Unknown entity at position: [%s:%d]", equationString, position));
			}
		}
		int maxPriority = 0;
		for (EquationPart equationPart : parts) {
			maxPriority = Math.max(maxPriority, equationPart.getOperatorPriority());
		}
		int priority = maxPriority;
		while (priority >= 0) {
			int i = 0;
			while (i < parts.size()) {
				EquationPart equationPart = parts.get(i);
				if (equationPart.getOperatorPriority() != priority) {
					continue;
				}
				if (i > 0) {

				}
			}
		}
		return null;
	}

	@Override
	public EquationPart onNestedEquationFound(String subPart) throws UnknownEntityException {
		return parse(subPart);
	}
}
