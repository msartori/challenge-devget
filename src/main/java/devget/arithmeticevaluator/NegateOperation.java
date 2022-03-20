package devget.arithmeticevaluator;

import java.util.Map;

public class NegateOperation extends UnaryOperation {

	public NegateOperation(Expression operand) {
		super(operand);
	}

	@Override
	public int evaluate(Map<Character, Integer> arguments) {
		return -1 * operand.evaluate(arguments);
	}
}