package dataStructures.PriorityQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue<TValue, TPriority extends Object & Comparable<? super TPriority>>
        implements Iterable<TValue> {
    private TValue[] heap;
    private TPriority[] prioritiesHeap;
    private int size;

    @SuppressWarnings("unchecked")
    public PriorityQueue(int maxSize) {
        heap = (TValue[]) new Object[0];
        prioritiesHeap = (TPriority[]) new Object[0];
        size = 0;
        growTo(maxSize);
    }

    public PriorityQueue() {
        this(11);
    }

    private void growTo(int size) {
        if (size > heap.length) {
            int newLength = 2 * heap.length;
            if (size > newLength)
                newLength = size;
            heap = Arrays.copyOf(heap, newLength);
            prioritiesHeap = Arrays.copyOf(prioritiesHeap, newLength);
            //System.out.println("Queue:" + this.toString() + ", grown to " + newLength);
        }
    }

    public Iterator<TValue> iterator() {
        return new Iterator<TValue>() {
            int position = 0;

            public boolean hasNext() {
                return position != size;
            }

            public TValue next() {
                return heap[position++];
            }
        };

    }


    public boolean add(TValue value, TPriority priority) {
        if (size >= heap.length)
            growTo(heap.length * 2);
        heap[size] = value;
        prioritiesHeap[size] = priority;
        siftUp(size, value, priority);
        size++;
        return true;
    }


    private void siftUp(int position, TValue element, TPriority priority) {
        while (position > 0) {
            int parent = (position - 1) >>> 1;
            TValue parentValue = heap[parent];
            TPriority parentPriority = prioritiesHeap[parent];
            if (priority.compareTo(parentPriority) >= 0)
                break;
            heap[position] = parentValue;
            prioritiesHeap[position] = parentPriority;
            position = parent;
        }
        heap[position] = element;
        prioritiesHeap[position] = priority;
    }

    private void siftDown(int position, TValue element, TPriority priority) {
        int half = size >>> 1;
        while (position < half) {
            int child = (position << 1) + 1; // left child
            TValue valueChild = heap[child];
            TPriority priorityChild = prioritiesHeap[child];
            int right = child + 1;
            if (right < size &&
                    (priorityChild.compareTo(prioritiesHeap[right]) > 0)) {
                valueChild = heap[child = right];
                priorityChild = prioritiesHeap[child];
            }
            if (priority.compareTo(priorityChild) <= 0)
                break;
            heap[position] = valueChild;
            prioritiesHeap[position] = priorityChild;
            position = child;
        }
        heap[position] = element;
        prioritiesHeap[position] = priority;
    }

    private void heapify() {
        for (int i = 0; i < (size >>> 1); i++)
            siftDown(i, heap[i], prioritiesHeap[i]);
    }

    public TValue remove() {
        if (size > 0) {
            size--;
            TValue returnValue = heap[0];
            System.arraycopy(heap, 1, heap, 0, size);
            System.arraycopy(prioritiesHeap, 1, prioritiesHeap, 0, size);
            heapify();
            return returnValue;
        } else {
            throw new NoSuchElementException();
        }

//        if (size > 0) {
//            int s = size--;
//            TValue movedValue = heap[s];
//            TPriority movedPriority = prioritiesHeap[s];
//            heap[s] = null;
//            siftDown(0, movedValue, movedPriority);
//            if (heap[0] == movedValue &&
//                    prioritiesHeap[0] == movedPriority) {
//                siftUp(0, movedValue, movedPriority);
//                if (heap[0] != movedValue || prioritiesHeap[0] != movedPriority)
//                    return movedValue;
//            }
//            return null;
//        }
//        else throw new NoSuchElementException();
    }

    public TValue peek() {
        return (size == 0) ? null : heap[0];
    }

    public int size() {
        return size;
    }

}
