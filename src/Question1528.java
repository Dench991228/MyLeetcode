public class Question1528 {
    public String restoreString(String s, int[] indices) {
        /*做好快排的准备*/
        int len = indices.length;
        char[] chars = new char[len];
        int i;
        for(i=0;i<len;i++){
            chars[indices[i]]=s.charAt(i);
        }
        return String.valueOf(chars);
    }
}
