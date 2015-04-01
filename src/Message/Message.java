package Message;

import java.util.*;

public class Message
{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        message = msg;
    }

    public Message() {
        new Message("");
    }

    public Message(String msg) {
        setMessage(msg);
    }

    public int count(char chr) {
        int iCount = 0;
        for (char c : getMessage().toCharArray()) {
            if (c == chr) iCount++;
        }
        return iCount;
    }

    public int countWords() {
        return getMessage().trim().split(" ").length;
    }

    public String reverse() {
        return new StringBuilder(getMessage()).reverse().toString();
    }

    public String encode(int shift) {
        char[] msg = getMessage().toCharArray();
        for (int i = 0; i < msg.length; i++) { msg[i] += shift; }
        return new String(msg);
    }

    private List<Character> openBrackets = Arrays.asList('<', '{', '(', '[');
    private List<Character> closeBrackets = Arrays.asList('>', '}', ')', ']');
    private boolean isOpenBracket(char c) { return openBrackets.contains(c); }
    private boolean isCloseBracket(char c) { return closeBrackets.contains(c); }

    public boolean isValid() {
        Stack<Character> brackets = new Stack<Character>();
        for (Character chr : getMessage().toCharArray()) {
            if (isOpenBracket(chr))
                brackets.push(chr);
            else if (!brackets.empty() &&
                    openBrackets.indexOf(brackets.peek()) == closeBrackets.indexOf(chr))
                brackets.pop();
            else if (isCloseBracket(chr)) return false;
        }
        return brackets.empty();
    }

    public static void main(String [] args) {
        Message a = new Message();
        System.out.println(a.getMessage());
        Message helloWorld = new Message("Hello, World!");
        System.out.println(helloWorld.count('W'));
        System.out.println(helloWorld.countWords());
        System.out.println(helloWorld.reverse());
        System.out.println(helloWorld.encode(5));

        Message program = new Message("" +
                "    public boolean isValid() {\n" +
                "        Stack<Character> brackets = new Stack<Character>();\n" +
                "        for (Character chr : getMessage().toCharArray()) {\n" +
                "            if (isOpenBracket(chr)) \n" +
                "                brackets.add(chr);\n" +
                "            if (!brackets.empty() &&\n" +
                "                    openBrackets.indexOf(brackets.peek()) == closeBrackets.indexOf(chr))\n" +
                "                brackets.pop();\n" +
                "        }\n" +
                "        return brackets.empty();\n" +
                "    }");
        System.out.println(program.isValid());
    }
}
