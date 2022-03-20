package devget.arithmeticevaluator;

import java.util.regex.Pattern;

public class Parser {

	Pattern digit = Pattern.compile("[0-9]{1}");
	Pattern letter = Pattern.compile("[a-z]{1}");
	Pattern add = Pattern.compile("\\+");
	Pattern substract = Pattern.compile("\\-");
	Pattern multiply = Pattern.compile("\\*");
	Pattern divide = Pattern.compile("\\/");
	Pattern open = Pattern.compile("\\(");
	Pattern close = Pattern.compile("\\)");

	public Expression parse(String expression) {
		Expression currentExpression = null;
		StringBuffer dbuffer = new StringBuffer();
		Character var = null;
		boolean startNegate = false;
		boolean startAbs = false;

		char[] arr = expression.toCharArray();

		for (int i = 0; i < arr.length; i++) {
			String str = String.valueOf(arr[i]);
			if (digit.matcher(str).matches()) {
				if (var != null) {
					throw new RuntimeException("unexpected digit");
				}
				dbuffer.append(arr[i]);
				continue;
			}
			if (letter.matcher(str).matches()) {
				if (dbuffer.length() > 0) {
					throw new RuntimeException("unexpected letter");
				}
				if (var != null) {
					if ((var == 'a' && arr[i] == 'b') || (startAbs && arr[i] == 's')) {
						startAbs = true;
					} else {
						throw new RuntimeException("unexpected letter");
					}
				} else {
					var = arr[i];
				}
				continue;
			}

			if (add.matcher(str).matches()) {
				if (dbuffer.length() > 0) {
					currentExpression = createConstant(dbuffer.toString(), startNegate);
					startNegate = false;
					dbuffer = new StringBuffer();
				} else if (var != null) {
					currentExpression = createVariable(var, startNegate);
					startNegate = false;
					var = null;
				} else if (currentExpression == null) {
					throw new RuntimeException("unexpected +");
				}
				currentExpression = new AddOperation(currentExpression, parse(expression.substring(i + 1)));
				break;
			}

			if (substract.matcher(str).matches()) {
				if (dbuffer.length() > 0) {
					currentExpression = createConstant(dbuffer.toString(), startNegate);
					startNegate = false;
					dbuffer = new StringBuffer();
				} else if (var != null) {
					currentExpression = createVariable(var, startNegate);
					startNegate = false;
					var = null;
				} else if (currentExpression == null) {
					startNegate = true;
					continue;
				}
				currentExpression = new SubstractOperation(currentExpression, parse(expression.substring(i + 1)));
				break;
			}

			if (multiply.matcher(str).matches()) {
				if (dbuffer.length() > 0) {
					currentExpression = createConstant(dbuffer.toString(), startNegate);
					startNegate = false;
					dbuffer = new StringBuffer();
				} else if (var != null) {
					currentExpression = createVariable(var, startNegate);
					startNegate = false;
					var = null;
				} else if (currentExpression == null) {
					throw new RuntimeException("unexpected *");
				}
				currentExpression = new MultiplyOperation(currentExpression, parse(expression.substring(i + 1)));
				break;
			}

			if (divide.matcher(str).matches()) {
				if (dbuffer.length() > 0) {
					currentExpression = createConstant(dbuffer.toString(), startNegate);
					startNegate = false;
					dbuffer = new StringBuffer();
				} else if (var != null) {
					currentExpression = createVariable(var, startNegate);
					startNegate = false;
					var = null;
				} else if (currentExpression == null) {
					throw new RuntimeException("unexpected /");
				}
				currentExpression = new DivideOperation(currentExpression, parse(expression.substring(i + 1)));
				break;
			}

			if (open.matcher(str).matches()) {
				int closeParentesis = findNextClose(expression.substring(i + 1));
				if (startNegate) {
					currentExpression = new NegateOperation(
							parse(expression.substring(i + 1, i + 1 + closeParentesis)));
					startNegate = false;
				} else if (startAbs) {
					currentExpression = new AbsOperation(parse(expression.substring(i + 1, i + 1 + closeParentesis)));
					var = null;
					startAbs = false;
				} else {
					currentExpression = parse(expression.substring(i + 1, i + 1 + closeParentesis));
				}
				i = i + 1 + closeParentesis;
				continue;
			}
		}
		if (dbuffer.length() > 0) {
			currentExpression = new Constant(Integer.valueOf(dbuffer.toString()));
		}
		if (var != null) {
			currentExpression = new Variable(var);
		}
		return currentExpression;
	}

	public int findNextClose(String expression) {
		char[] arr = expression.toCharArray();
		int opened = 0;
		for (int i = 0; i < arr.length; i++) {
			String str = String.valueOf(arr[i]);
			if (close.matcher(str).matches()) {
				if (opened == 0) {
					return i;
				}
				opened--;
			}
			if (open.matcher(str).matches()) {
				opened++;
			}
		}
		throw new RuntimeException("unclosed (");
	}

	private Expression createConstant(String constant, boolean startNegate) {
		if (startNegate) {
			return new NegateOperation(new Constant(Integer.valueOf(constant)));
		} else {
			return new Constant(Integer.valueOf(constant));
		}
	}

	private Expression createVariable(char var, boolean startNegate) {
		if (startNegate) {
			return new NegateOperation(new Variable(var));
		} else {
			return new Variable(var);
		}
	}
}
