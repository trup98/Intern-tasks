package foreach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForeachExample {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(Arrays.asList("a","v","d"));
        list.forEach(letter-> System.out.println(letter));
        List<Integer> num = Arrays.asList(4,4,5,4);
        num.forEach(n-> System.out.println(n+n));
    }



}
