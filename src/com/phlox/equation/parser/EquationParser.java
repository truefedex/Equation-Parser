package com.phlox.equation.parser;

import java.util.ArrayList;

import com.phlox.equation.evaluable.EvaluableEntity;
import com.phlox.equation.evaluable.Operator;
import com.phlox.equation.parser.EquationPartParser.NestedEquationParseListener;
import com.phlox.equation.parser.EquationPartParser.ParseResult;
import com.phlox.equation.parser.exceprion.EmptyEquationException;
import com.phlox.equation.parser.exceprion.UnknownEntityException;
import com.phlox.equation.parser.exceprion.WrongSyntaxException;

public final class EquationParser implements NestedEquationParseListener {

	public EvaluableEntity parse(String equationString) throws UnknownEntityException, EmptyEquationException,
			WrongSyntaxException {
		ArrayList<EvaluableEntity> entities = applyParsers(equationString);

		collapseOperationsOperands(entities);
		if (entities.size() == 0) {
			throw new EmptyEquationException(String.format("Empty equation: \"%s\"", equationString));
		} else if (entities.size() > 1) {
			throw new WrongSyntaxException(String.format("Missing operator: \"%s\"", equationString));
		} else {
			return entities.get(0);
		}
	}

	private void collapseOperationsOperands(ArrayList<EvaluableEntity> entities)
			throws WrongSyntaxException {
		EvaluableEntity operatorForCollapse;
		do {
			operatorForCollapse = null;
			int operatorIndex = -1;
			int maxPriority = -1;
			for (int i = 0; i < entities.size(); i++) {
				EvaluableEntity entity = entities.get(i);
				if (entity instanceof Operator && !entity.readyForEval()) {
					Operator operator = (Operator) entity;
					boolean leftOperand = false, rightOperand = false;
					if (i > 0 && entities.get(i - 1).readyForEval()) {
						leftOperand = true;
					}
					if (i < (entities.size() - 1) && entities.get(i + 1).readyForEval()) {
						rightOperand = true;
					}
					if (operator.isAppropriateOperands(leftOperand, rightOperand)) {
						int priority = operator.calcOperationPriority(leftOperand, rightOperand);
						if (priority > maxPriority) {
							operatorForCollapse = entity;
							operatorIndex = i;
							maxPriority = priority;
						}
					}
				}
			}
			if (operatorForCollapse != null) {
				boolean isOperatorSuitable;
				if (operatorIndex > 0 && entities.get(operatorIndex - 1).readyForEval()) {
					isOperatorSuitable = ((Operator)operatorForCollapse).setLeftOperand(entities.get(operatorIndex - 1));
					if (isOperatorSuitable) {
						entities.remove(operatorIndex - 1);
						operatorIndex--;
					}
				}
				if (operatorIndex < (entities.size() - 1) && entities.get(operatorIndex + 1).readyForEval()) {
					isOperatorSuitable = ((Operator)operatorForCollapse).setRightOperand(entities.get(operatorIndex + 1));
					if (isOperatorSuitable) {
						entities.remove(operatorIndex + 1);
					}
				}
			}
		} while (operatorForCollapse != null);
		
	}

	private ArrayList<EvaluableEntity> applyParsers(String equationString) throws UnknownEntityException {
		EquationPartParsersLibrary library = new EquationPartParsersLibrary();
		ArrayList<EvaluableEntity> entities = new ArrayList<EvaluableEntity>();
		int position = 0;
		while (position < equationString.length()) {
			boolean found = false;
			for (EquationPartParser parser : library.getParsers()) {
				ParseResult parseResult = parser.tryParse(equationString, position, this);
				if (parseResult != null) {
					entities.add(parseResult.object);
					position += parseResult.partLength;
					found = true;
					break;
				}
			}
			if (!found) {
				throw new UnknownEntityException(String.format("Unknown entity at position: [%s:%d]", equationString,
						position));
			}
		}
		return entities;
	}

	@Override
	public EvaluableEntity onNestedEquationFound(String subPart) throws UnknownEntityException, EmptyEquationException,
			WrongSyntaxException {
		return parse(subPart);
	}
}
