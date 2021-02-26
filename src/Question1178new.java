import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Question1178new {
    class trieNode{
        private boolean isTerminal;
        private int count;
        private final HashMap<Character, trieNode> transferMap;

        public trieNode(boolean terminal){
            isTerminal = terminal;
            transferMap = new HashMap<>();
            this.count = 0;
        }

        /**
         * 判断trie树里面有没有这个单词，如果有的话，返回count，否则是0
         * */
        public int contains(String s){
            trieNode current = this;
            for(int i=0;i<s.length();i++){
                current = current.transferMap.getOrDefault(s.charAt(i), null);
                if(current==null)return 0;
            }
            return current.isTerminal?current.count:0;
        }

        /**
         * 放置一个单词到当前的字典中
         * */
        public void addWord(String s){
            trieNode current = this;
            for(int i=0;i<s.length();i++){
                if (!current.transferMap.containsKey(s.charAt(i))) {
                    current.transferMap.put(s.charAt(i), new trieNode(false));
                }
                current = current.transferMap.get(s.charAt(i));
            }
            current.isTerminal = true;
            current.count++;
        }
    }
    /**
     * 把这个字符串变成一个没有重复字符的字符串，按字典序从小到大排列
     * */
    private String shorten(String s){
        boolean[] contained = new boolean[26];
        StringBuilder result = new StringBuilder();
        for(int i=0;i<s.length();i++){
            contained[s.charAt(i)-'a'] = true;
        }
        for(int i=0;i<26;i++){
            if(contained[i]){
                result.append((char)(i+'a'));
            }
        }
        return result.toString();
    }
    /**
     * 根据后面数字的各个数位是不是1来取得字符串的子串
     * */
    private String subString(String s, int number, char must_have){
        int len = s.length();
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for(int i=0;i<len;i++){
            if((number&(1<<i))!=0){
                result.append(s.charAt(i));
                if(s.charAt(i)==must_have)flag = true;
            }
        }
        return flag?result.toString():null;
    }
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        trieNode src = new trieNode(false);
        /*构建trie树*/
        for(String word:words){
            word = shorten(word);
            src.addWord(word);
        }
        /*开始猜谜*/
        LinkedList<Integer> result = new LinkedList<>();
        for(String puzzle:puzzles){
            char c = puzzle.charAt(0);
            puzzle = shorten(puzzle);
            int upper_bound = 1 << puzzle.length();
            int sum = 0;
            for(int i=0;i<upper_bound;i++){
                String sub = subString(puzzle, i,c);
                if(sub!=null){
                    sum+=src.contains(sub);
                }
            }
            result.addLast(sum);
        }
        return result;
    }
}
