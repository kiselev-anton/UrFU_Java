package dataStructures.PriorityQueue;

import org.junit.Test;

import java.util.Random;

public class PriorityQueueTest {

    @Test
    public void testRemove() throws Exception {
        PriorityQueue<Integer, Long> q = new PriorityQueue<>();
        q.add(1, (long) 135);
        q.add(2, (long) 1);
        int value = q.remove();
        assert value == 2;
    }

    @Test
    public void testPeek() throws Exception {
        PriorityQueue<Integer, Integer> q = new PriorityQueue<>();
        q.add(1, 1);
        int value = q.peek();
        assert value == 1;
    }

    @Test
    public void testSize() throws Exception {
        PriorityQueue<Integer, String> q = new PriorityQueue<>();
        q.add(1, "1");
        assert q.size() == 1;
    }

    @Test
    public void testIterator() throws Exception {
        Random rand = new Random();
        PriorityQueue<Integer, Integer> q = new PriorityQueue<>();
        for (int i = 0; i < 1000; i++)
            q.add(i, rand.nextInt());
        int result = 0;
        for (int i : q)
            result += i;
    }

    @Test
    public void testAdd() throws Exception {
        PriorityQueue<String, Integer> q = new PriorityQueue<>();
        q.add("сорок два", 42);
        q.add("пятнадцать", 15);
        q.add("сотня", 100);
        q.add("один", 1);
        q.add("дюжина", 12);
        for (String str : q)
            System.out.println(str);
        System.out.println();
        while (q.size() != 0)
            System.out.println(q.remove());

        PriorityQueue<Integer, Integer> sizeTest = new PriorityQueue<>();
        for (int i = 0; i < 100000; i++)
            sizeTest.add(i, i);
        for (int i = 0; i < 100000; i++)
            sizeTest.remove();
        System.out.println(sizeTest.size());
    }

}