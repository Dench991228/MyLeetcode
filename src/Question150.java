import java.util.LinkedList;

public class Question150 {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> numbers = new LinkedList<>();
        for(String s:tokens){
            try{
                int num = Integer.parseInt(s);
                numbers.addLast(num);
            }catch(NumberFormatException e){
                int latter = numbers.pollLast();
                int former = numbers.pollLast();
                if(s.compareTo("-")==0){
                    numbers.addLast(former-latter);
                }
                else if(s.compareTo("+")==0){
                    numbers.addLast(former+latter);
                }
                else if(s.compareTo("*")==0){
                    numbers.addLast(former*latter);
                }
                else{
                    numbers.addLast(former/latter);
                }
            }
        }
        return numbers.pollLast();
    }
}
