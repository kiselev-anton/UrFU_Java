package calculator.console;

import calculator.AbstractValueParser;
import calculator.Calculator;
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

import java.util.Scanner;

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

    public static void main(String args[]) {
        try {
            Program instance = new Program();
            instance.run(args);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    private void run(String[] args) {
        while (true) {
            String arg1 = scanner.next();
            if (arg1.equals("exit"))
                break;
            String op = scanner.next();
            String arg2 = scanner.next();
            try {
                System.out.println(" = " + calc.calculate(arg1, op, arg2));
            } catch (Exception exception) {
                System.out.println(exception.getLocalizedMessage());
            }
        }
    }

//    private void runReversePolish(String[] args) {
//        while (true) {
//            Stack<String> stack = new Stack<>();
//            String[] symbols = scanner.nextLine().split(" ");
//            if (symbols[0].equals("exit"))
//                break;
//
//            for (String symbol: symbols) {
//                if (isNumber(symbol)) {
//                    stack.push(symbol);
//                }
//            }
//
//            try {
////                System.out.println(" = " + calc.calculate(arg1, op, arg2));
//            } catch (Exception exception) {
//                System.out.println(exception.getLocalizedMessage());
//            }
//        }
//    }
//
//    private boolean isNumber(String s) {
//        return !isOp(s) && !isCloseParetheses(s) && !isOpenParentheses(s);
//    }
//
//    private boolean isOp(String s) {
//        return s.equals("*") || s.equals("+") || s.equals("/") || s.equals("-");
//    }
//
//    private boolean isOpenParentheses(String s) {
//        return s.equals("(");
//    }
//
//    private boolean isCloseParetheses(String s) {
//        return s.equals(")");
//    }
//
//    public String toReversePolish(String s) throws ParseValueException {
//        String result = "";
//        Stack<String> stack = new Stack<>();
//        String[] symbols = s.split(" ");
//        if (symbols[0].equals("exit"))
//            return "exit";
//
//        for (String symbol: symbols) {
//            if (isNumber(symbol)) {
//                result += symbol;
//            }
//            if (isOp(symbol) || isOpenParentheses(symbol)) {
//                stack.push(symbol);
//            }
//            if (isCloseParetheses(symbol)) {
//                if (stack.isEmpty())
//                    throw new ParseValueException("Cannot parse: brackets are not correct");
//                while (!isOpenParentheses(stack.peek())) {
//                    result += (stack.pop() + " ");
//                }
//                stack.pop(); // removing open bracket without pushing it to result
//            }
//        }
//
//        while (!stack.isEmpty()) {
//            String element = stack.pop();
//            if (!isOp(element))
//                throw new ParseValueException("Cannot parse: brackets are not correct");
//            result += (element + " ");
//        }
//
//        return result;
//    }

}
