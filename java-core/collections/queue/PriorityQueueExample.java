package queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {
    public static void main(String[] args) {
//      Creating PriorityQueue
        Queue<String> q = new PriorityQueue<>(Arrays.asList("b","n","h"));
        System.out.println(q.element());
        System.out.println(q.peek());

        Iterator itr = q.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
