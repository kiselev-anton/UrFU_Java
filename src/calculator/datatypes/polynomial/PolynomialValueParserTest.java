package calculator.datatypes.polynomial;

import org.junit.Test;

import java.util.HashMap;

public class PolynomialValueParserTest {

    @Test
    public void testParse() throws Exception {
        PolynomialValueParser p = new PolynomialValueParser();
        PolynomialValue parsedPolynomial = (PolynomialValue) p.parse("-5.0+2.0*x^5-9.0*x^8");
//        System.out.println(parsedPolynomial);

        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(0, -5.0);
        newValues.put(5,2.0);
        newValues.put(8,-9.0);
        PolynomialValue test1 = new PolynomialValue(newValues);
//        System.out.println(test1);

        assert test1.equals(parsedPolynomial);

        parsedPolynomial = (PolynomialValue) p.parse("2.0*x^5-9.0*x^8");
//        System.out.println(parsedPolynomial);

        newValues = new HashMap<>();
        newValues.put(5,2.0);
        newValues.put(8,-9.0);
        PolynomialValue test2 = new PolynomialValue(newValues);
//        System.out.println(test2);

        assert test2.equals(parsedPolynomial);
    }
}