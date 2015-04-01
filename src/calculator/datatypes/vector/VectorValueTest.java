package calculator.datatypes.vector;

import calculator.datatypes.integer.IntegerValue;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class VectorValueTest {

    @Test
    public void testAdd() throws Exception {
        VectorValue<IntegerValue> val1 = new VectorValue<>(
                Arrays.asList(new IntegerValue(1), new IntegerValue(2), new IntegerValue(3)));
        VectorValue<IntegerValue> val2 = new VectorValue<>(
                Arrays.asList(new IntegerValue(2), new IntegerValue(0), new IntegerValue(5)));
//        System.out.println(val1.add(val2).toString());
//        System.out.println(new VectorValue<>(
//                Arrays.asList(new IntegerValue(3), new IntegerValue(2), new IntegerValue(8))).toString());
        assert val1.add(val2).equals(new VectorValue<>(
                Arrays.asList(new IntegerValue(3), new IntegerValue(2), new IntegerValue(8))));
    }

    @Test
    public void testSub() throws Exception {
        VectorValue<IntegerValue> val1 = new VectorValue<>(
                Arrays.asList(new IntegerValue(1), new IntegerValue(2), new IntegerValue(3)));
        VectorValue<IntegerValue> val2 = new VectorValue<>(
                Arrays.asList(new IntegerValue(2), new IntegerValue(0), new IntegerValue(5)));
        assert val1.sub(val2).equals(new VectorValue<>(
                Arrays.asList(new IntegerValue(-1), new IntegerValue(2), new IntegerValue(-2))));
    }

    @Test
    public void testEquals() throws Exception {
        VectorValue<IntegerValue> val1 = new VectorValue<>(
                Arrays.asList(new IntegerValue(1), new IntegerValue(2), new IntegerValue(3)));
        VectorValue<IntegerValue> val2 = new VectorValue<>(
                Arrays.asList(new IntegerValue(1), new IntegerValue(2), new IntegerValue(3)));
        assert !val1.equals(new VectorValue<>(
                Arrays.asList(new IntegerValue(-50), new IntegerValue(2), new IntegerValue(3))));
        assert val1.equals(val2);

    }

    //    @Test
//    public void testMul() throws Exception {calculator.datatypes.vector.VectorValueParserTest
//
//    }
//
//    @Test
//    public void testDiv() throws Exception {
//
//    }
}