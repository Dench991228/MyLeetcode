import java.util.ArrayList;
import java.util.List;

public class Question93 {
    private void dfs(StringBuffer src, StringBuffer curString, List<String> result, int num_finished){
        if(src.length()>(4-num_finished)*3)return;
        int i, len = src.length();
        /*如果前面已经有三个数*/
        if(num_finished==3){
            int lastNum = Integer.parseInt("."+src.toString());
            if(lastNum<=255&&!(lastNum!=0&&src.charAt(0)=='0')&&!(lastNum==0&&src.length()>1)){
                curString.append(src);
                result.add(curString.substring(1));
            }
            return;
        }
        /*如果前面有不到三个数*/
        /*遍历这一次分出来的是几位数*/
        for(i=1;i<=3;i++){
            /*长度太长*/
            if(src.length()-i<1)continue;
            int cur_num = Integer.parseInt(src.substring(0,i));
            /*不能大于255*/
            /*不能有前导零*/
            /*不能全是0*/
            if(cur_num<=255&&!(cur_num!=0&&src.charAt(0)=='0')&&!(cur_num==0&&i>1)){
                /*新的源*/
                StringBuffer new_src = new StringBuffer(src.substring(i));
                /*下一次的当前状态*/
                StringBuffer new_string = new StringBuffer(curString.toString().concat("."+src.substring(0,i)));
                dfs(new_src,new_string,result,num_finished+1);
            }
        }
    }
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        this.dfs(new StringBuffer(s),new StringBuffer(),result,0);
        return result;
    }
}
