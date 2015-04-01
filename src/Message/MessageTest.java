package Message;

import org.junit.Test;

public class MessageTest {

    @Test
    public void testGetMessage() throws Exception {
        Message message = new Message("test");
        assert message.getMessage().equals("test");
    }

    @Test
    public void testSetMessage() throws Exception {
        Message message = new Message("test");
        message.setMessage("test2");
        assert message.getMessage().equals("test2");
    }

    @Test
    public void testCount() throws Exception {
        Message message = new Message("Simple test. Bla-bla-bla.");
        assert message.count('.') == 2;
    }

    @Test
    public void testCountWords() throws Exception {
        Message message = new Message("    This is the simple sentence, that contains 9 words.   ");
        // words -> any sequence of chars split by space
        assert message.countWords() == 9;
    }

    @Test
    public void testReverse() throws Exception {
        Message message = new Message("test");
        assert message.reverse().equals("tset");
    }

    @Test
    public void testEncode() throws Exception {
        Message message = new Message("123456");
        assert message.encode(1).equals("234567");
    }

    @Test
    public void testIsValid() throws Exception {
        Message message1 = new Message("<([(){[]}])>");
        assert message1.isValid();
        Message message2 = new Message("<{{}}}>");
        assert !message2.isValid();
        Message message3 = new Message("<{{[}}]>");
        assert !message3.isValid();
    }
}