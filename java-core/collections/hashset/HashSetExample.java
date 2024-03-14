package hashset;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
//      Creating hashSet
        Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 1, 5, 4, 6, 7, 8, 9, 1, 10));
        Set<Integer> setTwo = new HashSet<>();
//      Adding One Set List Into Another List
        setTwo.addAll(set);
//      Removing Value From List
        System.out.println(set.remove(1));
        System.out.println(set);

        Iterator itr = set.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
