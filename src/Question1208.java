public class Question1208 {
    /**
     * 使用双指针解决问题，[leftBound,rightBound)是目标子字符串
     * */
    public int equalSubstring(String s, String t, int maxCost) {
        int leftBound = 0;
        int rightBound = 0;
        int len = t.length();
        int currentCost = 0;
        int currentLength = 0;
        int maxLength = 0;
        while(rightBound<len&&leftBound<=rightBound){
            currentCost += Math.abs(s.charAt(rightBound)-t.charAt(rightBound));
            rightBound++;
            currentLength++;
            if(currentCost<=maxCost){
                maxLength = Math.max(currentLength, maxLength);
            }else{
                while(currentCost>maxCost&&leftBound<=rightBound){
                    currentCost-=(Math.abs(t.charAt(leftBound)-s.charAt(rightBound)));
                    leftBound++;
                    currentLength--;
                }
            }
        }
        return maxLength;
    }
}
