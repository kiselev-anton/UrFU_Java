package calculator.console;

import calculator.AbstractValueParser;
import calculator.Calculator;
import calculator.ParseValueException;
import calculator.datatypes.complex.ComplexValue;
import calculator.datatypes.complex.ComplexValueParser;
import calculator.datatypes.integer.IntegerValue;
import calculator.datatypes.integer.IntegerValueParser;
import calculator.datatypes.polynomial.PolynomialValueParser;
import calculator.datatypes.rational.RationalValue;
import calculator.datatypes.rational.RationalValueParser;
import calculator.datatypes.real.RealValue;
import calculator.datatypes.real.RealValueParser;
import calculator.datatypes.vector.VectorValueParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Program {

    private final Scanner scanner;

    private final Calculator calc;

    private AbstractValueParser[] valueParsers;

    public Program() {
        scanner = new Scanner(System.in);

        valueParsers = new AbstractValueParser[] { new IntegerValueParser(),
                new RealValueParser(), new RationalValueParser(),
                new ComplexValueParser(),
                new VectorValueParser<>(IntegerValue.class),
                new VectorValueParser<>(RealValue.class),
                new VectorValueParser<>(RationalValue.class),
                new VectorValueParser<>(ComplexValue.class),
                new PolynomialValueParser()
        };
        AbstractValueParser parser = inputValueParser();
        System.out.println("Работаем с типом '" + parser.getDatatypeName()
                + "'");
        calc = new Calculator(parser);
    }

    public static void main(String args[]) {
        try {
            Program instance = new Program();
//            instance.run(args);
            instance.runReversePolish(args);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private AbstractValueParser inputValueParser() {
        showChoices();
        int choice = scanner.nextInt();
        if (choice >= 1 && choice <= valueParsers.length)
            return valueParsers[choice - 1];
        else {
            System.out.println("Неверный выбор!");
            return inputValueParser();
        }
    }

    private void showChoices() {
        System.out.println("Вам нужно выбрать тип данных. Возможные варианты:");
        for (int i = 0; i < valueParsers.length; i++)
            System.out.println("  " + (i + 1) + ". "
                    + valueParsers[i].getDatatypeName());
    }
//
//    private void run(String[] args) {
//        while (true) {
//            String arg1 = scanner.next();
//            if (arg1.equals("exit"))
//                break;
//            String op = scanner.next();
//            String arg2 = scanner.next();
//            try {
//                System.out.println(" = " + calc.calculate(arg1, op, arg2));
//            } catch (Exception exception) {
//                System.out.println(exception.getLocalizedMessage());
//            }
//        }
//    }

    private void runReversePolish(String[] args) {
        while (true) {
            String expression = scanner.nextLine();
            try {
                if (expression.equals("exit"))
                    break;
                if (expression.equals(""))
                    continue;
                System.out.println(" = " + calc.calculateReversePolish(toReversePolish(expression)));
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    private boolean isNumber(String s) {
        return !isOp(s) && !isCloseParenthesis(s) && !isOpenParenthesis(s);
    }

    private boolean isOp(String s) {
        return priority(s) != -1;
    }

    private boolean isOpenParenthesis(String s) {
        return s.equals("(");
    }

    private boolean isCloseParenthesis(String s) {
        return s.equals(")");
    }

    public List<String> toReversePolish(String s) throws ParseValueException {
        List<String> reversePolishExpression = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String[] symbols = s.split(" ");
        for (String symbol : symbols) {
            if (isNumber(symbol)) {
                reversePolishExpression.add(symbol);
            }
            if (isOpenParenthesis(symbol)) {
                stack.push(symbol);
            }
            if (isCloseParenthesis(symbol)) {
                if (stack.isEmpty())
                    throw new ParseValueException("Cannot parse: brackets are not correct");
                while (!isOpenParenthesis(stack.peek())) {
                    reversePolishExpression.add(stack.pop());
                }
                stack.pop(); // removing open bracket without pushing it to reversePolishExpression
                if (!stack.empty() && isOp(stack.peek()))
                    reversePolishExpression.add(stack.pop());
            }
            if (isOp(symbol)) {
                while (!stack.empty() && priority(symbol) <= priority(stack.peek())) {
                    reversePolishExpression.add(stack.pop());
                }
                stack.push(symbol);
            }
        }
        while (!stack.empty()) {
            String symbol = stack.pop();
            if (!(isOp(symbol)))
                throw new ParseValueException("Cannot parse: brackets are not correct");
            else reversePolishExpression.add(symbol);
        }
        System.out.println(reversePolishExpression); // debug
        return reversePolishExpression;
    }

    private int priority(String op) {
        if (op.equals("*") || op.equals("/"))
            return 2;
        if (op.equals("-") || op.equals("+"))
            return 1;
        else return -1;
    }

}
