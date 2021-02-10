public class Question567 {
    private boolean compareArr(short[] arr1, short[] arr2){
        for(int i=0;i<arr1.length;i++){
            if(arr1[i]!=arr2[i])return false;
        }
        return true;
    }
    /**
     * 维护一个固定宽度的滑动窗口
     * */
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length())return false;
        int start = 0, end = 0, tLen = s2.length(), pLen = s1.length();
        short[] stat_s1 = new short[26];
        short[] stat_current = new short[26];
        /*统计s1中的各种字符信息*/
        for(int i=0, length = s1.length();i<length;i++){
            stat_s1[s1.charAt(i)-'a']++;
        }
        /*初始化滑动窗口*/
        do{
            stat_current[s2.charAt(end)-'a']++;
            end++;
        }while(end-start!=pLen);
        if(compareArr(stat_s1, stat_current))return true;
        /*开始滑动*/
        while(end<tLen){
            stat_current[s2.charAt(end)-'a']++;
            stat_current[s2.charAt(start)-'a']--;
            end++;
            start++;
            if(compareArr(stat_s1, stat_current))return true;
        }
        return false;
    }
}
