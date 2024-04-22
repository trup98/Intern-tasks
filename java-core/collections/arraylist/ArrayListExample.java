package arraylist;

import java.util.*;

public class ArrayListExample {
  public static void main(String[] args) {
//      Creating arrayList
    List<String> ls = new ArrayList<>(Arrays.asList("sdds", "sdfs", "sfsfs", "zvsf", "vesvf"));
    List<String> list = new ArrayList<>();
//      Adding One list in another List
    list.addAll(ls);
    System.out.println(list);
    System.out.println(ls);
//      Accessing List Element Through particular Index
    System.out.println(ls.get(2));
//      Removing List Element Through Index
    ls.remove(0);
    System.out.println(ls);

    Iterator itr = ls.iterator();
    while (itr.hasNext()) {
      System.out.println(itr.next());

    }
  }
}
