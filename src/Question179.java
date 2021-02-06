import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class Question179 {
    public String largestNumber(int[] nums) {
        ArrayList<String> strings = new ArrayList<>();
        for(int i:nums){
            strings.add(String.valueOf(i));
        }
        strings.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int temp = o1.compareTo(o2);
                if(temp<0)return -1;
                else if(temp>0)return 1;
                else return 0;
            }
        });
        StringBuilder result = new StringBuilder();
        for(String s:strings){
            result.append(s);
        }
        return result.toString();
    }
}
