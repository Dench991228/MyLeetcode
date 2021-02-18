import java.util.HashSet;

public class Question3 {
    /**
     * 找到字符串中的不含重复字符的最长的序列，使用双指针即可
     * */
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, len = s.length();
        HashSet<Character> chars = new HashSet<>();
        int max_length = 0;
        while(right < len){
            char c = s.charAt(right);
            if(chars.contains(c)){
                max_length = Math.max(right-left, max_length);
                do{
                    chars.remove(s.charAt(left));
                    left++;
                }while(chars.contains(c));
            }else{
                chars.add(c);
                right++;
            }
        }
        return max_length;
    }
}
