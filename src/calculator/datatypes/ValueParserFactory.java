package calculator.datatypes;

import calculator.AbstractValueParser;
import calculator.datatypes.complex.ComplexValue;
import calculator.datatypes.complex.ComplexValueParser;
import calculator.datatypes.integer.IntegerValue;
import calculator.datatypes.integer.IntegerValueParser;
import calculator.datatypes.rational.RationalValue;
import calculator.datatypes.rational.RationalValueParser;
import calculator.datatypes.real.RealValue;
import calculator.datatypes.real.RealValueParser;
import calculator.datatypes.vector.VectorValue;
import calculator.datatypes.vector.VectorValueParser;

/**
 * Created by kiselev on 22.03.15.
 */
public class ValueParserFactory {

    public AbstractValueParser createParser(Class<?> valueClass) {
        if (valueClass == ComplexValue.class) {
            return new ComplexValueParser();
        }
        if (valueClass == IntegerValue.class) {
            return new IntegerValueParser();
        }
        if (valueClass == RationalValue.class) {
            return new RationalValueParser();
        }
        if (valueClass == RealValue.class) {
            return new RealValueParser();
        }
//        if (valueClass == VectorValue.class) {
//            return new VectorValueParser<?>()
//        }
        else return null;
    }
}
