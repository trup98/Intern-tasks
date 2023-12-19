package linkedhashset;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LinkedHashSet {
    public static void main(String[] args) {
//      Creating linkedHashSet
        Set<String> list = new java.util.LinkedHashSet<>(Arrays.asList("b","s","s","sv","v"));
        Set<String> listTwo = new java.util.LinkedHashSet<>(list);
//      Adding Value In List Through add() method
        list.add("k");
//      Removing value From List
//      If it contains the given value then it will remove it and return True as output
        System.out.println(list.remove("sv"));

    }
}
