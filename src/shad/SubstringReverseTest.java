package shad;

import org.junit.Test;

public class SubstringReverseTest {

    @Test
    public void testReverseSubstring() throws Exception {
        String reversed = SubstringReverse.reverseSubstring("vjhoamkts", 7, 8);
        assert reversed.equals("vjhoamtks");

        reversed = SubstringReverse.reverseSubstring("abcdefghijklmn", 5, 9);
        System.out.println(reversed);
        assert reversed.equals("abcdihgfejklmn");
    }
}