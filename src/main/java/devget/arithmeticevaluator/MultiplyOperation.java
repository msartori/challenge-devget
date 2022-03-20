package devget.arithmeticevaluator;

import java.util.Map;

public class MultiplyOperation extends BinaryOperation {

	public MultiplyOperation(Expression leftOperator, Expression rightOperator) {
		super(leftOperator, rightOperator);
	}

	@Override
	public int evaluate(Map<Character, Integer> arguments) {
		return leftOperator.evaluate(arguments) * rightOperator.evaluate(arguments);
	}
}