package calculator.datatypes.complex;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexValueParserTest {

    @Test
    public void testParse() throws Exception {
        ComplexValueParser p = new ComplexValueParser();
        assert p.parse("5+6i").equals(new ComplexValue(5,6));
        assert p.parse("-5-6i").equals(new ComplexValue(-5,-6));
        assert p.parse("0.11-5.12i").equals(new ComplexValue(0.11, -5.12));
    }
}