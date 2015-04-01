package calculator.datatypes.vector;

import calculator.datatypes.integer.IntegerValue;
import calculator.datatypes.integer.IntegerValueParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VectorValueParserTest {

    @Test
    public void testParse() throws Exception {
        ArrayList<IntegerValue> values = new ArrayList<IntegerValue>();
        values.add(new IntegerValue(-2));
        values.add(new IntegerValue(2));
        values.add(new IntegerValue(-7));
        VectorValue<IntegerValue> testVector = new VectorValue<IntegerValue>(values);
//        System.out.println("testVector = " + testVector);

        VectorValueParser<IntegerValue> parser =
                new VectorValueParser<IntegerValue>(IntegerValue.class);
        VectorValue<IntegerValue> parsedVector = (VectorValue<IntegerValue>) parser.parse("[-2,2,-7]");
//        System.out.println("parsedVector = " + parsedVector);

        assert parsedVector.equals(testVector);
//        assert !((parser.parse("[1,1,1]")).equals(testVector));
    }
}