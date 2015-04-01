package calculator.datatypes.rational;

import org.junit.Test;

import static org.junit.Assert.*;

public class RationalValueParserTest {

    @Test
    public void testParse() throws Exception {
        RationalValueParser parser = new RationalValueParser();
        assert parser.parse("14/88").equals(new RationalValue(14,88));
        assert parser.parse("-5/11").equals(new RationalValue(-5, 11));
        assert parser.parse("5/-33").equals(new RationalValue(5,-33));
    }
}