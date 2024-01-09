package foreach;

import java.util.*;

public class ForeachExample {
    public static void main(String[] args) {
//      Adding elements to the list
        List<String> list = new ArrayList<>(Arrays.asList("java", "python", "c", "Java"));
        list.forEach(System.out::println);
//      Taking empty list
        List<String> originList = new LinkedList<String>();
//      Applying foreach with condition based
        list.forEach(n -> {
//            For addling with first letter of the word
//            if (n.startsWith("J") || n.startsWith("j"))
            if (Objects.equals(n, "java") || Objects.equals(n, "Java")) {
                originList.add(n);
            } else {
                originList.remove(n);
            }
        });
        System.out.println(originList);


        List<Integer> numbers = new ArrayList<Integer>();
        List<Integer> num = Arrays.asList(1, 8, 4, 4, 5, 4, 5);
//      Applying foreach with condition based
        num.forEach(i -> {
            if (i % 2 == 0) {
                numbers.add(i);
            } else {
                numbers.remove(i);
            }
        });
        System.out.println(numbers);
    }
}
