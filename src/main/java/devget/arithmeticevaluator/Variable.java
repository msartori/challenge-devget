package devget.arithmeticevaluator;

import java.util.Map;

public class Variable implements Expression {
	private Character value;

	public Variable(Character value) {
		this.value = value;
	}

	@Override
	public int evaluate(Map<Character, Integer> arguments) {
		return arguments.get(value);
	}

}
