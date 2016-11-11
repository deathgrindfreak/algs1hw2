import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] deqArgs = StdIn.readLine().split(" ");

        RandomizedQueue<String> q = new RandomizedQueue<>();
        for (String str : deqArgs)
            q.enqueue(str);

        for (int i = 0; i < k; i++)
            StdOut.println(q.dequeue());
    }
}
