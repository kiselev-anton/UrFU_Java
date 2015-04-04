package calculator.datatypes.polynomial;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.ParseValueException;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialValueParser implements AbstractValueParser {

    @Override
    public AbstractValue parse(String value) throws ParseValueException {

        HashMap<Integer, Double> newValues = new HashMap<>();
        //[+-]?[.0-9]+
        //[+-]?[.0-9]+(\*x^[0-9]+)*
        Pattern pattern = Pattern.compile("[+-]?[.0-9]+");
        Matcher matcher = pattern.matcher(value);

        int i = 0, degree;
        double coefficient;
        try {
        while (matcher.find()) {
            if (i == 0) {
                coefficient = Double.parseDouble(matcher.group(0));
                matcher.find();
                String secondCoefficientStr = matcher.group(0);
                if (isInt(secondCoefficientStr))
                    newValues.put(Integer.parseInt(secondCoefficientStr), coefficient);
                else {
                    newValues.put(0, coefficient);
                    matcher.find();
                    degree = Integer.parseInt(matcher.group(0));
                    coefficient = Double.parseDouble(secondCoefficientStr);
                    newValues.put(degree, coefficient);
                }
                i++;
//                System.out.println("Shit1:" + degree + " " + coefficient);
            }
            else {
                coefficient = Double.parseDouble(matcher.group(0));
                matcher.find();
                degree = Integer.parseInt(matcher.group(0));
                newValues.put(degree, coefficient);
//                System.out.println("Shit2:" + degree + " " + coefficient);
                i++;
            }
        } } catch (NumberFormatException e) {
            throw new ParseValueException("Cannot parse as Polynomial");
        }

        return new PolynomialValue(newValues);
    }

    private boolean isInt(String str) {
        return !str.contains(".");
    }

    @Override
    public String getDatatypeName() {
        return "Polynomials";
    }
}
