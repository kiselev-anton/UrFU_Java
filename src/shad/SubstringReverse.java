package shad;

import java.util.Scanner;

public class SubstringReverse {

    public static String reverseSubstring(String str, int i, int j) {
        return str.substring(0,i-1) +
                new StringBuilder(str.substring(i-1, j)).reverse().toString() +
                str.substring(j);
    }

    public static void main(String[] args) {
        // IO
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int i = in.nextInt();
        int j = in.nextInt();

        // logic
        String result = reverseSubstring(str, i, j);

        // result
        System.out.println(result);
    }

}
