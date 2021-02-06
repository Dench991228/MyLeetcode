import java.util.LinkedList;

public class Question32 {
    public int longestValidParentheses(String s) {
        LinkedList<Character> parentheses = new LinkedList<>();//记录向右遍历的过程中的配对情况
        LinkedList<Integer> numbers = new LinkedList<>();//记录在这个括号的右边已经有多少是已经配对了的
        parentheses.addLast(')');
        numbers.addLast(0);
        int i=0, len = s.length();
        int max_match = 0;
        while(!parentheses.isEmpty()&&!numbers.isEmpty()&&i<len){
            if(s.charAt(i)==')'&&parentheses.peekLast()=='('){//出栈一个括号和一个数量，并且更新出栈的括号的匹配数，放到栈顶对应的位置上
                int current_match = numbers.pollLast()+2;
                parentheses.pollLast();
                int former_match = numbers.pollLast();
                numbers.addLast(current_match+former_match);
                max_match = Math.max(current_match+former_match, max_match);
            }else{// 入栈一个符号，不论怎样都顺便入栈一个0
                parentheses.addLast(s.charAt(i));
                numbers.addLast(0);
            }
            i++;
        }
        return max_match;
    }
}
