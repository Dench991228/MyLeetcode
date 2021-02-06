public class Question392 {
    /**
     * 判断s是不是t的子字符串
     * @param s 字字符串
     * @param t 母字符串
     * @return 布尔量，是不是字字符串
     */
    public boolean isSubsequence(String s, String t) {
        /*用来遍历t*/
        int i = 0;
        /*用来遍历s*/
        int j = 0;
        int lens = s.length();
        int lent = t.length();
        boolean flag = true;
        while(i<lent&&j<lens){
            //在t中找到一个和s相匹配的字符
            while(i<lent&&s.charAt(j)!=t.charAt(i)){
                i++;
                //如果t中遍历完了都找不到匹配的
                if(i==lent)flag=false;
            }
            j++;
        }
        return j==lens&&flag;
    }
}
