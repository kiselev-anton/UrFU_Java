package calculator.datatypes.rational;

import calculator.AbstractValue;
import calculator.AbstractValueParser;
import calculator.ParseValueException;
import calculator.datatypes.complex.ComplexValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RationalValueParser implements AbstractValueParser {

    @Override
    public AbstractValue parse(String value) throws ParseValueException {
        // (-)a/(-)b
        Pattern pattern = Pattern.compile("(-?\\d+)/(-?\\d+)");
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            return new RationalValue(Long.parseLong(matcher.group(1)), Long.parseLong(matcher.group(2)));
        } else throw new ParseValueException("Cannot parse as Rational: " + value);
    }

    @Override
    public String getDatatypeName() {
        return "Rational numbers.";
    }

}
