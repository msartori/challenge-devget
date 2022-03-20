package devget.arithmeticevaluator;

import java.util.Map;

public interface Expression {
	public int evaluate(Map<Character, Integer> arguments);
}
