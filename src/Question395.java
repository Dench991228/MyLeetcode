import java.util.TreeSet;

public class Question395 {
    /**
     * 找出字符串中，所有字符都出现了最少k次的最长的子串
     * 使用滑动窗口的办法，计算出不同字符数量不超过t的上述字符串
     * @param s 目标字符串
     * @param k 最少出现次数
     * */
    public int longestSubstring(String s, int k) {
        boolean[] set = new boolean[26];
        for(int i=0;i<s.length();i++){
            set[s.charAt(i)-'a'] = true;
        }
        int diff_char = 0;//有多少不同的字符
        for(int i=0;i<26;i++){
            if(set[i]){
                diff_char++;
            }
        }
        int number_diff = 1;//最长子串中可以出现的最多的不同字符
        int max_length = 0;
        for(number_diff = 1; number_diff<=diff_char;number_diff++){
            int curr_diff = 0;//当前有多少不同的字符
            int[] number_appearance = new int[26];//各个字符出现了多少次
            int satisfied = 0;//当前已经满足了多少
            int left = 0, right = 0;
            while(right<s.length()){
                int cur = s.charAt(right)-'a';
                //边界条件：已经有超过number_diff种字符了
                if(number_appearance[cur]==0&&curr_diff==number_diff){
                    while(curr_diff==number_diff){
                        int temp = s.charAt(left)-'a';
                        if(number_appearance[temp]--==k){
                            satisfied--;
                        }
                        if(number_appearance[temp]==0){
                            curr_diff--;
                        }
                        left++;
                    }
                    continue;
                }
                if(number_appearance[cur]++==0){
                    curr_diff++;
                }
                if(number_appearance[cur]==k){
                    satisfied++;
                }
                if(satisfied==number_diff){
                    max_length = Math.max(max_length, right-left+1);
                }
                right++;
            }
        }
        return max_length;
    }
}
