import java.util.HashMap;

public class Question227 {
    class StringIter{

    }
    /**
     * 获得从指针处开始的第一个符号/数字，自动跳过空白
     * @param s 目标字符串
     * @param pos 指针
     * @return 第一个数字或者符号的字符串
     * */
    private String getElement(String s, int pos){
        StringBuilder res = new StringBuilder();
        while(pos<s.length()&&s.charAt(pos)==' '){
            pos++;
        }
        if(!(pos<s.length()))return res.toString();
        else{
            if(s.charAt(pos)>='0'&&s.charAt(pos)<='9'){
                while(pos<s.length()&&s.charAt(pos)>='0'&&s.charAt(pos)<='9'){
                    res.append(s.charAt(pos));
                    pos++;
                }
            }else{
                res.append(s.charAt(pos));
            }
        }
        return res.toString();
    }
    public int calculate(String s) {

    }
}
