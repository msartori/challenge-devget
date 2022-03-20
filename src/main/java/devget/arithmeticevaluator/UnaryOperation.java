package devget.arithmeticevaluator;

public abstract class UnaryOperation implements Expression {
	protected Expression operand;
	
	public UnaryOperation(Expression operand) {
		this.operand = operand;
	}

}
