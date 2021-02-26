import java.util.*;

class trieNode{
    private int suspended;//滞留在这一层的更新
    private int word;//状态压缩之后的单词，最后26位表示26个字母出现的情况
    private int id;//标识符
    HashSet<trieNode> children;//子节点，当前单词出现过的字母，子节点的单词中都出现过
    public trieNode(String s, int identifier){
        this.id = identifier;
        this.word = trieNode.change(s);
        this.suspended = 0;
    }
    /**
     * 插入一个节点，到以当前节点为根节点的树上
     * */
    public void insert(trieNode node){
        for(trieNode n:this.children){
            if((n.word&node.word)==n.word){
                n.insert(node);
                return ;
            }
        }
        this.children.add(node);
    }
    /**
     * 判断一个节点是不是这个节点的子节点
     * */
    public boolean isSonOf(trieNode node){
        return (this.word&node.word)==node.word;
    }
    /**
     * 判断一个节点是不是这个节点的父节点
     * */
    public boolean isFatherOf(trieNode node){
        return (this.word&node.word)==this.word;
    }
    /**
     * 开始比较输入的字符集合(整数形式)和当前节点是不是完美匹配，如果是，就更新当前节点，不往下走
     * 否则还需要继续往下
     * */
    public void update(int number){
        if((this.word&number)==number){//当前节点包含全部word中的字符，那么底下的也全都能包含word中的字符
            this.suspended ++;
        }else{
            for(trieNode n:this.children){
                n.update(number);
            }
        }
    }
    /**
     * 把一个单词转换成一个整数
     * */
    public static int change(String s){
        boolean[] exist = new boolean[26];
        for(int i=0;i<s.length();i++){
            exist[s.charAt(i)-'a'] = true;
        }
        int number = 0;
        for(int i=0;i<26;i++){
            if(exist[i]){
                number^=(1<<i);
            }
        }
        return number;
    }
    /**
     * 判断一个单词中是否包含c
     * */
    public static boolean containsChar(int number, char c){
        int pos = c - 'a';
        return (number & (1 << pos)) == (1 << pos);
    }
    /**
     * 更新suspended
     * */
    public void offer(){
        for(trieNode n:this.children){
            n.suspended+=this.suspended;
            n.offer();
        }
    }
    /**
     * 遍历整棵树，顺便更新一下相关信息
     * */
    public void inOrder(HashMap<Integer, Integer>data){
        data.put(this.id, this.suspended);
        for(trieNode t:this.children){
            t.inOrder(data);
        }
    }
    /**
     * 把数字转换成二进制
     * */
    public static String toBinary(int number){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<32;i++){
            result.insert(0, ((number>>i)&1)==1?'1':'0');
        }
        return result.toString();
    }
    @Override
    public String toString() {
        return toBinary(this.word)+": "+this.suspended+this.children;
    }
}
public class Question1178 {
    int lowBit(int number){
        return number&(-number);
    }
    /**
     * words中的单词如果要作为puzzles的谜底，必须
     * 1. 包含puzzles的首字母
     * 2. word中每一个字母，puzzles都包含
     * */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        HashSet<trieNode>[] roots = new HashSet[26];
        for(int i=0;i<26;i++){
            roots[i] = new HashSet<>();
        }
        /*构建字典树*/
        for(int i=0;i< puzzles.length;i++){
            String puzzle = puzzles[i];
            trieNode t = new trieNode(puzzle, i);
            boolean flag = false;
            for(trieNode n:roots[puzzle.charAt(0)-'a']){
                if(t.isSonOf(n)){//可以作为根
                    roots[puzzle.charAt(0)-'a'].remove(n);
                    t.insert(n);
                    roots[puzzle.charAt(0)-'a'].add(t);
                    flag = true;
                    break;
                }else if(t.isFatherOf(n)){//某个节点的子节点
                    n.insert(t);
                    flag = true;
                    break;
                }
            }
            if(!flag)roots[puzzle.charAt(0)-'a'].add(t);
        }
        /*开始猜谜*/
        /*更新相关数据*/
        for(String word:words){
            int number = trieNode.change(word);
            for(char c='a';c<='z';c++){
                if(trieNode.containsChar(number, c)){
                    for(trieNode n:roots[c-'a']){
                        n.update(number);
                    }
                }
            }
        }
        /*更新一下suspended，顺便统计一下*/
        HashMap<Integer, Integer> id_suspend = new HashMap<>();
        for(HashSet<trieNode> s:roots){
            for(trieNode n:s){
                n.offer();
                n.inOrder(id_suspend);
            }
        }
        LinkedList<Integer> result = new LinkedList<>();
        for(int i=0;i<puzzles.length;i++){
            result.addLast(id_suspend.get(i));
        }
        return result;
    }
}
