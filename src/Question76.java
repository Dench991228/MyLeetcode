import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Question76 {
    class Solution {
        /**
         * 把新的字符添加到表中
         * @param stat 记录表
         * @param c 新字符
         */
        private void addChar(HashMap<Character,Integer> stat, char c){
            if(stat.containsKey(c)){
                stat.replace(c,stat.get(c)+1);
            }
            else{
                stat.put(c,1);
            }
        }

        /**
         * 从统计表中删除一个字符
         * @param stat 统计表
         * @param c 目标字符
         */
        private void removeChar(HashMap<Character, Integer> stat, char c){
            if(stat.containsKey(c)){
                if(stat.get(c)==1)stat.remove(c);
                else stat.replace(c,stat.get(c)-1);
            }
        }

        /**
         * 比较两个集合
         * @param stat
         * @param src
         * @return
         */
        private boolean compare(Map<Character,Integer> stat, Map<Character,Integer> src){
            for(char c:src.keySet()){
                if(!stat.containsKey(c)||stat.get(c)<src.get(c))return false;
            }
            return true;
        }
        /**
         * 找出字符串s中包含t中全部字母的最小连续子串
         * @param s
         * @param t
         * @return 最小子串，如果没有符合条件的字符串，就返回空
         */
        public String minWindow(String s, String t) {
            /*保存字符串t中出现过的全部字符，以及他们的出现频率*/
            Map<Character,Integer> chars = new HashMap<>();
            int len = t.length();
            /*初始化字符集合*/
            for(int i=0;i<len;i++){
                if(chars.containsKey(t.charAt(i))){
                    chars.replace(t.charAt(i),chars.get(t.charAt(i))+1);
                }
                else chars.put(t.charAt(i),1);
            }
            /*滑动窗口左边界和有边界*/
            int start = 0,end = 0;
            /*当前找到的最短的子字符串的长度*/
            int min_length = Integer.MAX_VALUE;
            /*当前窗口中有哪些字符，分别出现了几次*/
            HashMap<Character,Integer> containedChars = new HashMap<>();
            /*返回的最终结果*/
            String result = "";
            while(start<=end&&start<s.length()){
                //只要右边界没出界或者当前窗口内的字符与目标串相等，就跳出循环。否则继续右移
                while(end<s.length()&&!this.compare(containedChars,chars)){
                    this.addChar(containedChars,s.charAt(end));
                    end++;
                }
                //如果当前已经包含目标串所有字符，那就与之前的统计结果比较
                if(this.compare(containedChars,chars)){
                    if(end-start<min_length){
                        result = s.substring(start,end);
                        min_length=end-start;
                    }
                }
                this.removeChar(containedChars,s.charAt(start));
                start++;
            }
            return result;
        }
    }
}
