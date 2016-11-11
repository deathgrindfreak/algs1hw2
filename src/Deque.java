import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null!");
        if (first == null) {
            first = new Node(item);

            // Link in the last item
            last = first;
        } else {
            Node<Item> oldFst = first;

            // Create a new node
            first = new Node(item);

            // Link in the new item
            oldFst.prev = first;
            first.next = oldFst;
        }

        // increment size
        ++size;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new NullPointerException("Item cannot be null!");
        if (last == null) {
            last = new Node(item);

            // Link in the first item
            first = last;
        } else {
            Node<Item> oldLst = last;

            // Create a new node
            last = new Node(item);

            // Link in the new item
            oldLst.next = last;
            last.prev = oldLst;
        }

        // increment size
        ++size;
    }

    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException("Cannot remove element when queue is empty!");
        Item item = first.item;

        // Delete the links
        first = first.next;
        if (first != null)
            first.prev = null;

        // decrement size
        --size;
        return item;
    }

    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException("Cannot remove element when queue is empty!");

        Item item = last.item;

        // Delete the link
        last = last.prev;
        if (last != null)
            last.next = null;

        // decrement size
        --size;
        return item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> pos = first;

            @Override
            public boolean hasNext() {
                return pos != null;
            }

            @Override
            public Item next() {
                if (pos == null)
                    throw new NoSuchElementException("Cannot remove element when queue is empty!");
                Item item = pos.item;
                pos = pos.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove is not supported for this class!");
            }
        };
    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    public void printQueue() {
        System.out.print("(");
        Iterator<Item> it = this.iterator();
        while(it.hasNext()) {
            System.out.print(it.next());
            if (it.hasNext())
                System.out.print(", ");
        }
        System.out.println(") size: " + this.size());
    }

    public static void main(String[] args)  {
        Deque<String> q = new Deque<String>();
        q.addFirst("bob");
        q.printQueue();
        q.addLast("fred");
        q.printQueue();
        q.addFirst("joe");
        q.printQueue();
        q.addLast("beverly");
        q.printQueue();
        q.removeFirst();
        q.printQueue();
        q.removeLast();
        q.printQueue();
        q.removeFirst();
        q.printQueue();
        q.removeFirst();
        q.printQueue();

        q.addFirst("bob");
        q.addLast("fred");
        q.addFirst("joe");
        q.addLast("beverly");

        for (String s : q) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}