public class Question8 {
    /**
     * 把字符串转换成数字
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        StringBuffer number = new StringBuffer();
        int i = 0, len = str.length();
        /*排出空字符*/
        while(i<len&&str.charAt(i)==' '){
            i++;
        }
        /*如果空字符之后啥都没有，返回0*/
        if(i>=len)return 0;
        boolean isNegative = false;
        /*判断第一个字符是什么*/
        /*不是数字也不是符号，返回0*/
        if((str.charAt(i)>'9'||str.charAt(i)<'0')&&str.charAt(i)!='+'&&str.charAt(i)!='-'){
            return 0;
        }
        else if(str.charAt(i)=='-'){
            number.append('-');
            isNegative = true;
        }
        else if(str.charAt(i)=='+')isNegative = false;
        else number.append(str.charAt(i));
        i++;
        /*把剩下的数字加上*/
        while(i<len){
            if(!(str.charAt(i)>='0')||!(str.charAt(i)<='9')){
                break;
            }
            number.append(str.charAt(i));
            i++;
        }
        /*没有有效数字*/
        if((isNegative&&number.length()==1)||number.length()==0)return 0;
        else{
            int result;
            try{
                result = Integer.parseInt(number.toString());
                return result;
            }catch(NumberFormatException e){
                //超边界而且是负数
                if(isNegative)return Integer.MIN_VALUE;
                else return Integer.MAX_VALUE;
            }
        }
    }
}
