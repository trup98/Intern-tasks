package hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
//      Creating hashMap
        Map<Integer,String> mp = new HashMap<>();
        mp.put(1,"a");
        mp.put(2,"g");
        mp.put(3,"sds");
//      Accessing Key
        System.out.println(mp.get(1));
//      Checking key for given value
        System.out.println(mp.containsValue("g"));
//      Replacing particular key's value
        mp.replace(2,"jb");
        System.out.println(mp);

        for(Map.Entry m : mp.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }
}
