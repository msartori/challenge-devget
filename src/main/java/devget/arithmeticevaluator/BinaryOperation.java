package devget.arithmeticevaluator;

public abstract class BinaryOperation implements Expression {

	protected Expression leftOperator;
	protected Expression rightOperator;
	
	public BinaryOperation(Expression leftOperator, Expression rightOperator) {
		this.leftOperator = leftOperator;
		this.rightOperator = rightOperator;
	}
	
	
}
