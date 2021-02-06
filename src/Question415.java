import java.util.LinkedList;

public class Question415 {
    private String stackToNumber(LinkedList<Integer> digits){
        StringBuilder number = new StringBuilder();
        int over = 0;
        while(!digits.isEmpty()){
            int temp = digits.pollFirst();
            temp+=over;
            if(temp>10){
                over = temp/10;
                temp%=10;
            }
            number.insert(0,String.valueOf(temp));
        }
        return number.toString();
    }
    public String addStrings(String num1, String num2) {
        String longer = num1.length()>=num2.length()?num1:num2;
        String shorter = num1.length()<num2.length()?num1:num2;
        LinkedList<Integer> stack = new LinkedList<>();
        int longerLength = longer.length();
        int shorterLength = shorter.length();
        int i = longerLength-1;
        int j = shorterLength-1;
        /*先把二者共同的部分给搞定*/
        while(i>=0&&j>=0){
            int temp = 0;
            try{
                int a = Integer.parseInt(longer.substring(i,i));
                int b = Integer.parseInt(shorter.substring(j,j));
                temp = a + b;
            }catch(NumberFormatException e){
                System.out.println("hehe");
            }
            stack.addFirst(temp);
            i--;
            j--;
        }
        /*再搞定二者不同的部分*/
        while(i>=0){
            int temp = 0;
            try{
                temp = Integer.parseInt(longer.substring(i,i));
            }catch(NumberFormatException e){
                System.out.println("hehe");
            }
            stack.addFirst(temp);
            i--;
        }
        return this.stackToNumber(stack);
    }
}
