package calculator.datatypes.complex;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexValueTest {
    @Test
    public void testAdd() throws Exception {
        ComplexValueParser p = new ComplexValueParser();
        ComplexValue val1 = (ComplexValue) p.parse("2+3i");
        ComplexValue val2 = (ComplexValue) p.parse("-6-7i");
//        System.out.println(val1.add(val2).toString());
        assert val1.add(val2).equals(p.parse("-4-4i"));
    }

    @Test
    public void testSub() throws Exception {
        ComplexValueParser p = new ComplexValueParser();
        ComplexValue val1 = (ComplexValue) p.parse("2+3i");
        ComplexValue val2 = (ComplexValue) p.parse("-6-7i");
        assert val1.sub(val2).equals(p.parse("8+10i"));
    }

    @Test
    public void testMul() throws Exception {
        ComplexValueParser p = new ComplexValueParser();
        ComplexValue val1 = (ComplexValue) p.parse("2+3i");
        ComplexValue val2 = (ComplexValue) p.parse("-6-7i");
        assert val1.mul(val2).equals(p.parse("9-32i"));
    }

    @Test
    public void testDiv() throws Exception {
        ComplexValueParser p = new ComplexValueParser();
        ComplexValue val1 = (ComplexValue) p.parse("1+0i");
        ComplexValue val2 = (ComplexValue) p.parse("0+5i");
//        System.out.println(val1.div(val2).toString());
        assert val1.div(val2).equals(p.parse("0-0.2i"));
    }
}