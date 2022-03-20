package devget.arithmeticevaluator;

import java.util.Map;

public class Constant implements Expression {
	private Integer value;
	
	public Constant(Integer value) {
		this.value = value;
	}

	@Override
	public int evaluate(Map<Character, Integer> arguments) {
		return value;
	}
	
}
