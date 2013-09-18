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

		entities = collapseOperationsOperands(entities, findOperationsMaxPriority(entities));
		if (entities.size() == 0) {
			throw new EmptyEquationException(String.format("Empty equation: \"%s\"", equationString));
		} else if (entities.size() > 1) {
			throw new WrongSyntaxException(String.format("Missing operator: \"%s\"", equationString));
		} else {
			return entities.get(0);
		}
	}

	private ArrayList<EvaluableEntity> collapseOperationsOperands(ArrayList<EvaluableEntity> entities, int maxPriority)
			throws WrongSyntaxException {
		int priority = maxPriority;
		while (priority >= 0) {
			int i = 0;
			while (i < entities.size()) {
				if (entities.get(i) instanceof Operator) {
					Operator entity = (Operator) entities.get(i);
					if (entity.getOperatorPriority() == priority) {
						if (i > 0) {
							entity.setLeftOperand(entities.get(i - 1));
							entities.remove(i - 1);
							i--;
						}
						if (i < (entities.size() - 1)) {
							EvaluableEntity operand = collapseNextOperand(entities, i + 1);
							entity.setRightOperand(operand);
							entities.remove(i + 1);
						}
					}
				}
				i++;
			}
			priority--;
		}
		return entities;
	}

	private EvaluableEntity collapseNextOperand(ArrayList<EvaluableEntity> entities, int position)
			throws WrongSyntaxException {
		EvaluableEntity entity = entities.get(position);
		if (entity instanceof Operator) {
			if (position < (entities.size() - 1)) {
				Operator operator = (Operator) entity;
				operator.setRightOperand(collapseNextOperand(entities, position + 1));
				entities.remove(position + 1);
				return entity;
			} else {
				throw new WrongSyntaxException("Missing an operand.");
			}
		} else {
			return entities.get(position);
		}
	}

	private int findOperationsMaxPriority(ArrayList<EvaluableEntity> entities) {
		int maxPriority = 0;
		for (EvaluableEntity entity : entities) {
			if (entity instanceof Operator) {
				maxPriority = Math.max(maxPriority, ((Operator) entity).getOperatorPriority());
			}
		}
		return maxPriority;
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
