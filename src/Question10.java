import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Question10 {
    class State{
        boolean isTerminal;
        HashMap<Character, HashSet<State>> transferTable;// 正常情况下是a-z的字符，如果是！就表名是epsilon
        State epsilon;//通过epsilon可以转移到的状态
        char current;//当前这个状态的字符
        boolean isStar;
        boolean isDot;
        public State(boolean flag, boolean star, char c, boolean dot){
            isTerminal = flag;
            transferTable = new HashMap<>();
            epsilon = null;
            current = c;
            isStar = star;
            isDot = dot;
        }
        public String toString(){
            return "terminal? "+this.isTerminal+"\n current: "+this.current+"\n isStar: "+this.isStar+"\n epsilon: "+this.epsilon+"\n"+this.transferTable;
        }
        public void addTransfer(char c, State target){
            if (!this.transferTable.containsKey(c)) {
                this.transferTable.put(c, new HashSet<>());
            }
            this.transferTable.get(c).add(target);
        }
    }
    /**
     * 获得一个状态的epsilon闭包
     * @param s 目标状态
     * @return epsilon-closure
     * */
    private HashSet<State> epsilonClosure(State s){
        State current = s;
        HashSet<State> result = new HashSet<>();
        while(current!=null){
            result.add(current);
            current = current.epsilon;
        }
        return result;
    }
    /**
     * NFA的状态转移
     * @param current 当前的状态
     * @param c 新的字符
     * @return 转移出来的状态
     * */
    private HashSet<State> transfer(HashSet<State> current, char c){
        HashSet<State> result = new HashSet<>();
        for(State s:current){
            HashSet<State> target = s.transferTable.getOrDefault(c,null);
            if(target!=null){
                for(State state:target){
                    result.addAll(epsilonClosure(state));
                }
            }
        }
        return result;
    }
    /**
     * p是模式串（支持.和*的正则表达式），s是待匹配的字符串，判断p能不能匹配s，使用状态图法解决问题
     * @param s 待匹配的字符串
     * @param p 模式串
     * @return 是不是能匹配
     * */
    public boolean isMatch(String s, String p) {
        /*解析模式串*/
        LinkedList<State> states = new LinkedList<>();
        State start_state = new State(false, false,'\0', false);//初始状态
        states.addLast(start_state);
        int len = p.length();
        for(int i=0;i<len;i++){
            if(p.charAt(i)=='*'){//把最后一个状态改成*
                State last = states.peekLast();
                last.isStar = true;
            }else if(p.charAt(i)=='.'){//加入一个.状态
                State dot = new State(false, false, '.', true);
                states.addLast(dot);
            }else{//加入一个普通状态
                State new_state = new State(false, false, p.charAt(i), false);
                states.addLast(new_state);
            }
        }
        State terminal = states.peekLast();
        terminal.isTerminal = true;
        /*整理当前的状态*/
        while(!states.isEmpty()){
            State current = states.pollFirst();
            if(current.isStar){//当前状态是星号，那就可以不断的复制自己
                if(current.isDot){//如果是.那就所有字符都行
                    for(char c='a';c<='z';c++){
                        current.addTransfer(c, current);
                    }
                }else{
                    current.addTransfer(current.current, current);
                }
            }
            if(!states.isEmpty()){//后面还有状态，如果后面的状态是正常的状态，那就是一个字符跳出去，如果是.那就是各种字符跳出去，否则是epsilon
                State next = states.peekFirst();
                if(next.isStar){
                    current.epsilon = next;
                }else if(next.isDot){
                    for(char c='a';c<='z';c++){
                        current.addTransfer(c, next);
                    }
                }else{
                    current.addTransfer(next.current, next);
                }
            }
        }
        /*开始判断能不能匹配*/
        HashSet<State>current_states = new HashSet<>();
        current_states.addAll(epsilonClosure(start_state));
        for(int i=0;i<s.length();i++){
            current_states = transfer(current_states, s.charAt(i));
        }
        for(State state: current_states){
            if(state.isTerminal)return true;
        }
        return false;
    }
}
