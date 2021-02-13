import java.util.LinkedList;

public class Question224 {
    class StringIter{
        String string;
        int pos;
        public StringIter(String s){
            this.string = s;
            this.pos = 0;
        }
        /**
         * 获得从指针处开始的第一个符号/数字，自动跳过空白
         * @return 第一个数字或者符号的字符串
         * */
        private String getElement(){
            StringBuilder res = new StringBuilder();
            while(pos<string.length()&&string.charAt(pos)==' '){
                pos++;
            }
            if(!(pos<string.length()))return res.toString();
            else{
                if(string.charAt(pos)>='0'&&string.charAt(pos)<='9'){
                    while(pos<string.length()&&string.charAt(pos)>='0'&&string.charAt(pos)<='9'){
                        System.out.println(string.charAt(pos));
                        res.append(string.charAt(pos));
                        pos++;
                    }
                }else{
                    res.append(string.charAt(pos));
                    pos++;
                }
            }
            return res.toString();
        }
    }
    public int calculate(String s) {
        StringIter iter = new StringIter(s);
        LinkedList<Integer> number_stack = new LinkedList<>();//数字栈
        LinkedList<Character> operand_stack = new LinkedList<>();//符号栈
        String element;
        while((element =(iter.getElement())).compareTo("")!=0){
            System.out.println(element);
            if(element.charAt(0)>='0'&&element.charAt(0)<='9'){//是个数字
                int number = Integer.parseInt(element);
                number_stack.addLast(number);
            }else{
                if(element.charAt(0)=='('){
                    operand_stack.addLast(element.charAt(0));
                }
                else if(element.charAt(0)=='+'||element.charAt(0)=='-'){
                    while(!operand_stack.isEmpty()&&operand_stack.peekLast()!='('){
                        int b = number_stack.pollLast();
                        int a = number_stack.pollLast();
                        char opr = operand_stack.pollLast();
                        int result = 0;
                        switch (opr){
                            case '+':
                                result = a+b;
                                break;
                            case '-':
                                result = a-b;
                                break;
                            case '*':
                                result = a*b;
                                break;
                            case '/':
                                result = a/b;
                                break;
                        }
                        number_stack.addLast(result);
                    }
                    operand_stack.addLast(element.charAt(0));
                }else if(element.charAt(0)=='*'||element.charAt(0)=='/'){
                    while(!operand_stack.isEmpty()&&(operand_stack.peekLast()!='-'&&operand_stack.peekLast()!='+')&&operand_stack.peekLast()!='('){
                        int b = number_stack.pollLast();
                        int a = number_stack.pollLast();
                        char opr = operand_stack.pollLast();
                        int result = 0;
                        switch (opr){
                            case '*':
                                result = a*b;
                                break;
                            case '/':
                                result = a/b;
                                break;
                        }
                        number_stack.addLast(result);
                    }
                    operand_stack.addLast(element.charAt(0));
                }
                else{//右括号的情况，不断弹栈，直到找出左括号
                    while(!operand_stack.isEmpty()&&operand_stack.peekLast()!='('){
                        int b = number_stack.pollLast();
                        int a = number_stack.pollLast();
                        char opr = operand_stack.pollLast();
                        int result = 0;
                        switch (opr){
                            case '+':
                                result = a+b;
                                break;
                            case '-':
                                result = a-b;
                                break;
                            case '*':
                                result = a*b;
                                break;
                            case '/':
                                result = a/b;
                                break;
                        }
                        number_stack.addLast(result);
                    }
                    operand_stack.pollLast();
                }
            }
        }
        /*计算完成了，如果是只有一个数，那么operand_stack是空的，否则就是还剩最后一次运算*/
        if(operand_stack.isEmpty()){
            return number_stack.peekLast();
        }else{
            int result = 0;
            while(!operand_stack.isEmpty()){
                int b = number_stack.pollLast();
                int a = number_stack.pollLast();
                char opr = operand_stack.pollLast();
                switch (opr){
                    case '+':
                        result = a+b;
                        break;
                    case '-':
                        result = a-b;
                        break;
                    case '*':
                        result = a*b;
                        break;
                    case '/':
                        result = a/b;
                        break;
                }
                number_stack.addLast(result);
            }
            return result;
        }
    }
}
