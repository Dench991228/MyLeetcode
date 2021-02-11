import java.util.LinkedList;

public class Question1106 {
    /**
     * 解析布尔表达式：弄一个栈寄存相关的信息即可
     * @param expression 待解析的表达式
     * */
    public boolean parseBoolExpr(String expression) {
        LinkedList<Character> stack = new LinkedList<>();
        int len = expression.length();
        boolean current_computation = false;
        for(int i=0;i<len;i++){
            if(expression.charAt(i)!=')'&&expression.charAt(i)!=','){//只要不是右括号，就不停地推栈
                stack.addLast(expression.charAt(i));
            }else if(expression.charAt(i)==')'){//右括号：从栈里面把东西弹出来，弹到上一个是
                LinkedList<Character> temp = new LinkedList<>();//记录本次运算的运算数
                char current = stack.pollLast();
                while(current!='('){//弹完栈之后，顶端是运算符
                    temp.addFirst(current);
                    current = stack.pollLast();
                }
                char operand = stack.pollLast();
                boolean temp_result = false;
                switch (operand){
                    case '|':
                        temp_result = false;
                        for(char c:temp){
                            temp_result |= (c=='t');
                        }
                        break;
                    case '&':
                        temp_result = true;
                        for(char c:temp){
                            temp_result &= (c=='t');
                        }
                        break;
                    case '!':
                        for(char c:temp){
                            temp_result = (c!='t');
                        }
                        break;
                }
                current_computation = temp_result;
                stack.addLast(temp_result ?'t':'f');
            }
        }
        return current_computation;
    }
}
