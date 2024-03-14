package method_reference;

import java.util.Arrays;
import java.util.List;

interface Animal {
    void dog();
}

public class MethodReference {
    public static void say() {
        System.out.println("fyudg");
    }

    public static void main(String[] args) {
        Animal nm = MethodReference::say;
        nm.dog();

        List<Integer> list = Arrays.asList(4, 5, 6, 7, 8);
        list.forEach(System.out::println);
    }
}
