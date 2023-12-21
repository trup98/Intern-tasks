package parallel_ArraySort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ParallelArraySorting {
    public static void main(String[] args) {
        List<Integer> numbers = new LinkedList<Integer>(Arrays.asList(4,8,9,7,3));
//      Arrays.parallelSort(numbers);
        for(int i:numbers){
            System.out.println(i);
        }
        int[] number = {7,1,5,8,4,2,3,8};
        Arrays.parallelSort(number);
        for(int i:number){
            System.out.println(i);
        }
    }
}
