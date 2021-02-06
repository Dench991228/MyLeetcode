import java.util.LinkedList;
import java.util.List;

public class Question22 {
    /**
     * 生成由n个括号构成，最外侧被一对括号包住的组合
     * */
    private List<String> embraced(int n){
        LinkedList<String> result = new LinkedList<>();
        if(n==1){
            result.addLast("()");
        }
        else{
            List<String> minusOne = generateParenthesis(n-1);
            for(String s:minusOne){
                result.addLast("("+s+")");
            }
        }
        return result;
    }
    public List<String> generateParenthesis(int n) {
        LinkedList<String> result = new LinkedList<>();
        if(n==1){
            result.addLast("()");
            return result;
        }
        result.addAll(embraced(n));
        for(int i=n-1;i>=1;i--){
            List<String> embracing = embraced(i);
            List<String> lasted = generateParenthesis(n-i);
            for(String e:embracing){
                for(String l:lasted){
                    result.add(e+l);
                }
            }
        }
        return result;
    }
}
