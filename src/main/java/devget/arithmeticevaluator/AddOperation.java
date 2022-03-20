package devget.arithmeticevaluator;

import java.util.Map;

public class AddOperation extends BinaryOperation {

	public AddOperation(Expression leftOperator, Expression rightOperator) {
		super(leftOperator, rightOperator);
	}

	@Override
	public int evaluate(Map<Character, Integer> arguments) {
		return leftOperator.evaluate(arguments) + rightOperator.evaluate(arguments);
	}

}
