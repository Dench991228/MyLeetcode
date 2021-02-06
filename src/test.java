import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeMap;

public class test {
    public static void main(String[] args){
        TreeMap<Integer, Integer> newMap = new TreeMap<>();
        for(int i=0;i<20;i++){
            int a = new Random().nextInt();
            int b = new Random().nextInt();
            System.out.println(a+" "+b);
            newMap.put(a,b);
        }
        Integer[] numbers = newMap.keySet().toArray(new Integer[0]);
        for(Integer i:numbers){
            System.out.print(i+" ");
        }
    }
}
