package calculator;


import java.util.List;
import java.util.Stack;

public class Calculator {
    private final AbstractValueParser valueParser;

    public Calculator(AbstractValueParser valueParser) {
        this.valueParser = valueParser;
    }

    public String calculate(String arg1, String operation, String arg2)
            throws DivisionByZeroException,
            ParseValueException, OperationNotSupportedException {
        AbstractValue left = valueParser.parse(arg1);
        AbstractValue right = valueParser.parse(arg2);
        if (!isOp(operation))
            throw new OperationNotSupportedException("Operation not supported:" + operation);
        return calculate(left, operation, right).toString();
    }

    private AbstractValue calculate(AbstractValue left, String operation,
            AbstractValue right) throws DivisionByZeroException, OperationNotSupportedException {
        if (operation.equals("+"))
            return left.add(right);
        if (operation.equals("-"))
            return left.sub(right);
        if (operation.equals("/"))
            return left.div(right);
        if (operation.equals("*"))
            return left.mul(right);
        throw new OperationNotSupportedException(operation);
    }

    public String calculateReversePolish(List<String> expression)
            throws DivisionByZeroException, OperationNotSupportedException, ParseValueException {
        Stack<String> stack = new Stack<>();
        for (String something : expression) { // expression is reverse polished expression
            if (isOp(something)) {
                String arg2 = stack.pop();
                String arg1 = stack.pop();
                stack.push(calculate(arg1, something, arg2));
            } else {
                valueParser.parse(something);
                stack.push(something);
            }
        }
        String result = stack.pop();
        valueParser.parse(result);
        return result;
    }

    private boolean isOp(String str) {
        return str.equals("+") || str.equals("-") || str.equals("/") || str.equals("*");
    }
}
