package calculator;

import calculator.datatypes.integer.IntegerValueParser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CalculatorTest {

    @Test
    public void testCalculateReversePolish() throws Exception {

        IntegerValueParser p = new IntegerValueParser();
        Calculator calc = new Calculator(p);
        List<String> expression = Arrays.asList("1 2 + 4 * 3 +".split(" "));
        String result = calc.calculateReversePolish(expression);
        assert result.equals("15");
    }
}