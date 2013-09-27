package com.phlox.equation.parser.part;

import java.util.List;

import com.phlox.equation.evaluable.EvaluableEntity;
import com.phlox.equation.evaluable.Max;
import com.phlox.equation.parser.exceprion.WrongSyntaxException;

public class MaxParser extends FunctionCallParser {

	private static final int PARAMETERS_COUNT = 2;
	private static final String FUNCTION_NAME = "max";

	@Override
	public String getFunctionName() {
		return FUNCTION_NAME;
	}

	@Override
	public EvaluableEntity makeEntity(List<EvaluableEntity> parameters) throws WrongSyntaxException {
		return new Max(parameters.get(0), parameters.get(1));
	}

	@Override
	public int getParametersCount() {
		return PARAMETERS_COUNT;
	}

}
