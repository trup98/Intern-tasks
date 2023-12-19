package linkelist;

import java.util.*;

public class LinkeListExample {
    public static void main(String[] args) {
//      Creating linkedList
        List<Integer> list = new LinkedList<>(Arrays.asList(4,5,8,6));
//      Adding One List Into another List
        List<Integer> listTwo = new LinkedList<>(list);
//      Checking Element in List
        System.out.println(list.contains(4));
//      Removing Element in List Through Index
        System.out.println(list.remove(0));
//      Checking The Two List Size
        System.out.println(list.equals(listTwo));
//      Sorting The listTwo
        Collections.sort(listTwo);
        System.out.println(listTwo);
//      Accessing Element In List By Index
        System.out.println(listTwo.get(2));
    }


}
