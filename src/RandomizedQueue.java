import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null!");

        if (size == queue.length)
            resizeArray(2 * queue.length);

        // Set the next Item item
        queue[size++] = item;
    }

    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException("Cannot remove element when queue is empty!");

        int i = getRndInd();
        Item itm = queue[i];

        // Move the last item to the vacant space
        queue[i] = queue[size-1];
        queue[size-1] = null;

        // Decrement the size
        --size;

        // Resize the array if it has grown too small
        if (size < queue.length / 4)
            resizeArray(queue.length / 2);

        return itm;
    }

    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException("Cannot remove element when queue is empty!");
        return queue[getRndInd()];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator<Item>(this);
    }

    private void resizeArray(int newSize) {
        Item[] newArr = (Item[]) new Object[newSize];
        for (int i = 0; i < min(queue.length, newSize); i++)
            newArr[i] = queue[i];
        queue = newArr;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    private int getRndInd() {
        return StdRandom.uniform(size);
    }

    private static class RandomQueueIterator<Item> implements Iterator<Item> {

        private Item[] queue;
        private int pos;

        public RandomQueueIterator(RandomizedQueue<Item> q) {
            pos = 0;
            queue = (Item[]) new Object[q.size()];

            int i = 0;
            while(!q.isEmpty())
                queue[i++] = q.dequeue();
        }

        @Override
        public boolean hasNext() {
            return pos < queue.length;
        }

        @Override
        public Item next() {
            if (pos >= queue.length)
                throw new NoSuchElementException("Cannot remove element when queue is empty!");
            return queue[pos++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported for this class!");
        }
    };

    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        for (int i = 0; i < 20; i++)
            q.enqueue(i);

        for (int i : q)
            System.out.print(i + " ");
    }
}
