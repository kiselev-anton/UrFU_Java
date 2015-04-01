package calculator.datatypes.complex;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.ParseValueException;
import calculator.datatypes.rational.RationalValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kiselev on 21.03.15.
 */
public class ComplexValueParser implements AbstractValueParser {

    @Override
    public AbstractValue parse(String value) throws ParseValueException {
        // (-)a+bi
        Matcher matcher1 = Pattern.compile("(.*)\\+(.*)i").matcher(value);
        Matcher matcher2 = Pattern.compile("(.*)\\-(.*)i").matcher(value);
        if (matcher1.matches()) {
            return new ComplexValue(Double.parseDouble(matcher1.group(1)), Double.parseDouble(matcher1.group(2)));
        } else if (matcher2.matches()) {
            return new ComplexValue(Double.parseDouble(matcher2.group(1)), (-1)*Double.parseDouble(matcher2.group(2)));
        } else throw new ParseValueException("Cannot parse as Complex: " + value);
    }

    @Override
    public String getDatatypeName() {
        return "Complex numbers.";
    }

}
