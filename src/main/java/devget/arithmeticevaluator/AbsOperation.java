package devget.arithmeticevaluator;

import java.util.Map;

public class AbsOperation extends UnaryOperation {

	public AbsOperation(Expression operand) {
		super(operand);
	}

	@Override
	public int evaluate(Map<Character, Integer> arguments) {
		return Math.abs(operand.evaluate(arguments));
	}

}
