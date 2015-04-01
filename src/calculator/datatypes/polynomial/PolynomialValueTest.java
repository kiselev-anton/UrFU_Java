package calculator.datatypes.polynomial;

import org.junit.Test;

import java.util.HashMap;

public class PolynomialValueTest {

    @Test
    public void testGetDegree() throws Exception {
        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(0, -1.0);
        newValues.put(1,0.0);

        PolynomialValue test1 = new PolynomialValue(newValues);
//        System.out.println(test1);
//        System.out.println(test1.getDegree());
        assert test1.getDegree() == 0;
    }

    @Test
    public void testAdd() throws Exception {
        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(0, 5.0);
        newValues.put(5,2.0);
        newValues.put(8,9.0);
        PolynomialValue test1 = new PolynomialValue(newValues);
        System.out.println(test1);

        newValues = new HashMap<>();
        newValues.put(0, -5.0);
        newValues.put(3,4.0);
        newValues.put(2,2.0);
        PolynomialValue test2 = new PolynomialValue(newValues);
//        System.out.println(test2);
        newValues.put(5, 2.0);
        newValues.put(8,9.0);
        newValues.remove(0);
        PolynomialValue assertValue = new PolynomialValue(newValues);
//        System.out.println(assertValue);

        assert test1.add(test2).equals(assertValue);
    }

    @Test
    public void testSub() throws Exception {
        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(0, 5.0);
        newValues.put(5,2.0);
        newValues.put(8,9.0);
        PolynomialValue test1 = new PolynomialValue(newValues);
//        System.out.println(test1);

        newValues = new HashMap<>();
        newValues.put(0, -5.0);
        newValues.put(3,4.0);
        newValues.put(2,2.0);
        PolynomialValue test2 = new PolynomialValue(newValues);
//        System.out.println(test2);

        newValues = new HashMap<>();
        newValues.put(0, 10.0);
        newValues.put(5,2.0);
        newValues.put(8,9.0);
        newValues.put(3,-4.0);
        newValues.put(2,-2.0);
        PolynomialValue assertValue = new PolynomialValue(newValues);
//        System.out.println(assertValue);
//        System.out.println(test1.sub(test2));

        assert test1.sub(test2).equals(assertValue);

    }

    @Test
    public void testMul() throws Exception {
        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(0, -1.0);
        newValues.put(1,1.0);

        PolynomialValue test1 = new PolynomialValue(newValues);
//        System.out.println(test1);

        newValues = new HashMap<>();
        newValues.put(0, -2.0);
        newValues.put(1,1.0);
        PolynomialValue test2 = new PolynomialValue(newValues);
//        System.out.println(test2);

        newValues = new HashMap<>();
        newValues.put(2, 1.0);
        newValues.put(1, -3.0);
        newValues.put(0, 2.0);
        PolynomialValue assertValue = new PolynomialValue(newValues);
        PolynomialValue testValue = (PolynomialValue) test1.mul(test2);
//        System.out.println(assertValue);
//        System.out.println(testValue);

        assert testValue.equals(assertValue);
    }

    @Test
    public void testDiv() throws Exception {
        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(0, -1.0);
        newValues.put(1,1.0);
        newValues.put(2,2.0);
        newValues.put(3,1.0);
        PolynomialValue test1 = new PolynomialValue(newValues);
//        System.out.println(test1);

        newValues = new HashMap<>();
        newValues.put(0, -1.0);
        newValues.put(1,1.0);
        PolynomialValue test2 = new PolynomialValue(newValues);
//        System.out.println(test2);

        newValues = new HashMap<>();
        newValues.put(0,4.0);
        newValues.put(1,3.0);
        newValues.put(2,1.0);
        PolynomialValue assertValue = new PolynomialValue(newValues);
//        System.out.println(assertValue);
//        System.out.println(test1.div(test2));

        assert test1.div(test2).equals(assertValue);

    }

    @Test
    public void testToString() throws Exception {
        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(12, -19.0);
        newValues.put(5,2.0);
        newValues.put(8,9.0);
        PolynomialValue test = new PolynomialValue(newValues);
//        System.out.println(test.toString());
        assert test.toString().equals("2.0*x^5+9.0*x^8-19.0*x^12");
    }

    @Test
    public void testEquals() throws Exception {
        HashMap<Integer, Double> newValues = new HashMap<>();
        newValues.put(0, 5.0);
        newValues.put(135, 0.0);
        PolynomialValue test1 = new PolynomialValue(newValues);

        newValues = new HashMap<>();
        newValues.put(0, 5.0);
        newValues.put(32, 0.0);
        PolynomialValue test2 = new PolynomialValue(newValues);
//        System.out.println("test1 = " + test1.toString());
//        System.out.println("test2 = " + test2.toString());
        assert test1.equals(test2);
    }
}