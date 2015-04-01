package calculator.console;

import calculator.AbstractValueParser;
import calculator.Calculator;
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

//        class VectorRationalValue extends VectorValue<RationalValue> {
//            public VectorRationalValue(List<RationalValue> rationalValues) {
//                super(rationalValues);
//            }
//        }
        valueParsers = new AbstractValueParser[] { new IntegerValueParser(),
                new RealValueParser(), new RationalValueParser(),
                new ComplexValueParser(),
                new VectorValueParser<IntegerValue>(IntegerValue.class),
                new VectorValueParser<RealValue>(RealValue.class),
                new VectorValueParser<RationalValue>(RationalValue.class),
                new PolynomialValueParser(),
//                new VectorValueParser<VectorRationalValue>(VectorRationalValue.class)
                // Java isn't this stronk, it turns out.
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

}
