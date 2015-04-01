package calculator.datatypes.rational;

import org.junit.Test;

import static org.junit.Assert.*;

public class RationalValueTest {
    @Test
    public void testAdd() throws Exception {
        RationalValue val1 = new RationalValue(1,5);
        RationalValue val2 = new RationalValue(4,5);
        assert val1.add(val2).equals(new RationalValue(1,1));
    }

    @Test
    public void testSub() throws Exception {
        RationalValue val1 = new RationalValue(1,5);
        RationalValue val2 = new RationalValue(4,5);
        assert val1.sub(val2).equals(new RationalValue(-3,5));
    }

    @Test
    public void testMul() throws Exception {
        RationalValue val1 = new RationalValue(1,5);
        RationalValue val2 = new RationalValue(4,5);
        assert val1.mul(val2).equals(new RationalValue(4,25));
    }

    @Test
    public void testDiv() throws Exception {
        RationalValue val1 = new RationalValue(1,5);
        RationalValue val2 = new RationalValue(4,5);
        assert val1.div(val2).equals(new RationalValue(1,4));
    }
}