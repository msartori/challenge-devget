package devget.arithmeticevaluator;

import java.util.Map;

public class DivideOperation extends BinaryOperation {

	public DivideOperation(Expression leftOperator, Expression rightOperator) {
		super(leftOperator, rightOperator);
	}

	@Override
	public int evaluate(Map<Character, Integer> arguments) {
		int divider = rightOperator.evaluate(arguments);
		if (divider == 0) {
			throw new RuntimeException("Unable to divide by 0");
		}
		return leftOperator.evaluate(arguments) / divider;
	} 

}
