public class Question1529 {
    public int minFlips(String target) {
        int i, len = target.length();
        /*前面那个节点是什么*/
        char former = '0';
        /*前面那个节点翻了几次*/
        int num_flip = 0;
        for(i=0;i<len;i++){
            if(target.charAt(i)!=former){//和前面的不一样
                former = target.charAt(i);
                num_flip++;
            }
        }
        return num_flip;
    }
}
