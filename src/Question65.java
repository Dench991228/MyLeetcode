import javax.swing.plaf.nimbus.State;
import java.util.HashMap;

public class Question65 {
    class StateNode{
        boolean Accept;//这个状态是否可以被接受
        HashMap<Character, StateNode> TransferRelationship;//状态转换关系
        StateNode(boolean ok){
            this.Accept = ok;
            this.TransferRelationship = new HashMap<>();
        }
        void addTransfer(char c, StateNode target){
            this.TransferRelationship.put(c, target);
        }
        StateNode transfer(char c){
            return this.TransferRelationship.getOrDefault(c, null);
        }
    }
    public boolean isNumber(String s) {
        /*构建状态图*/
        StateNode start_node = new StateNode(false);//初始状态
        StateNode number_with_sign = new StateNode(false);//加了一个小数点后的状态
        start_node.addTransfer('+', number_with_sign);
        start_node.addTransfer('-', number_with_sign);
        StateNode number_dot = new StateNode(false);//小数点，从此之后就是小数了
        start_node.addTransfer('.', number_dot);//初始状态遇到小数点到这里
        number_with_sign.addTransfer('.', number_dot);//有一个符号的状态遇到小数点到这里
        StateNode integer_node = new StateNode(true);//整数状态，可以接受
        for(char c='0';c<='9';c++){//初始态和符号态遇到数字都能转移到这
            start_node.addTransfer(c, integer_node);
            number_with_sign.addTransfer(c, integer_node);
            integer_node.addTransfer(c, integer_node);
        }
        StateNode has_number_dot = new StateNode(true);//整数后面有一个小数点
        integer_node.addTransfer('.', has_number_dot);//整数遇到小数点变成有点的数
        StateNode float_no_sci = new StateNode(true);//没有E的浮点数
        for(char c='0';c<='9';c++){//初始态和符号态遇到数字都能转移到这
            number_dot.addTransfer(c, float_no_sci);
            float_no_sci.addTransfer(c, float_no_sci);
            has_number_dot.addTransfer(c, float_no_sci);
        }
        StateNode float_with_e = new StateNode(false);
        integer_node.addTransfer('e', float_with_e);
        integer_node.addTransfer('E', float_with_e);
        float_no_sci.addTransfer('e', float_with_e);
        float_no_sci.addTransfer('E', float_with_e);
        has_number_dot.addTransfer('e', float_with_e);
        has_number_dot.addTransfer('E', float_with_e);
        StateNode float_sci_has_sign = new StateNode(false);//e后面有符号
        float_with_e.addTransfer('-', float_sci_has_sign);
        float_with_e.addTransfer('+', float_sci_has_sign);
        StateNode float_sci = new StateNode(true);//完整的科学计数法浮点数
        for(char c='0';c<='9';c++){//初始态和符号态遇到数字都能转移到这
            float_with_e.addTransfer(c, float_sci);
            float_sci.addTransfer(c, float_sci);
            float_sci_has_sign.addTransfer(c, float_sci);
        }
        StateNode current = start_node;
        int length = s.length();
        for(int i=0;i<length;i++){
            current = current.transfer(s.charAt(i));
            if(current==null)return false;
        }
        return current.Accept;
    }
}
