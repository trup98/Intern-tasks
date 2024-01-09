package streamApi;

import DefaultList.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamApiExample {
    public static void main(String[] args) {

//      Applying stream on array
        Arrays.stream(Lists.namesArrays()).forEach(System.out::println);
//      Applying stream on numbers
        List<Integer> collect = Lists.numbers().stream().
                filter(i -> i % 2 == 0).
                sorted().
                map(b -> b * b).
                collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> number = Lists.numbers().
                stream().
                filter(i -> i % 2 == 0).
                sorted().
                collect(Collectors.toList());
        System.out.println(number);

//      FindAny method
        Optional<String> any = Lists.namesList().stream().findAny();
        System.out.println(any.get());
//      FindFirst method
        Optional<String> first = Lists.namesList().stream().findFirst();
        System.out.println(first.get());

    }
}
