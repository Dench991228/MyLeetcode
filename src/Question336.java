import java.util.*;

/*用来统计回文串的字典树*/
class PalindromeTrie{
    private class PalindromeTrieNode{
        private char val;//当前节点的值
        private int depth;//当前节点是第几个字符
        private TreeSet<String> words;//包含这个节点的单词，匹配到这之后，根据这些单词的后缀情况即可判断回文串
        private TreeMap<Character, PalindromeTrieNode> successor;//之后的节点
        private PalindromeTrieNode(){
            super();
        }
        private PalindromeTrieNode(char c, int d){
            this.val = c;
            this.depth = d;
            this.words = new TreeSet<>();
            this.successor = new TreeMap<>();
        }
    }

    private TreeMap<Character, PalindromeTrieNode> dict = new TreeMap<>();

    public void insertWord(String word){
        if(word.length()==0)return;
        int i, len = word.length();
        PalindromeTrieNode root;
        if(!this.dict.containsKey(word.charAt(0))){
            root = new PalindromeTrieNode(word.charAt(0),0);
        }
        else{
            root = this.dict.get(word.charAt(0));
        }
        for(i=1;i<len;i++){
            root.words.add(word);//把当前单词添加到包含此节点的集合中
            if(root.successor.containsKey(word.charAt(i))){
                root = root.successor.get(word.charAt(i));
            }
            else{
                PalindromeTrieNode pn = new PalindromeTrieNode(word.charAt(i),i);
                root.successor.put(word.charAt(i),pn);
                root = pn;
            }
        }
        
    }
    public boolean tryMatch(String word, HashMap<String,HashSet<Integer>> records){
        int i, len = word.length();
        PalindromeTrieNode cur;
        if(this.dict.containsKey(word.charAt(0))){
            cur = this.dict.get(word.charAt(0));
        }
        else{
            return records.get(word).contains(0);
        }
    }
}
public class Question336 {
    /*需要想办法记录一个单词的前缀回文串和后缀回文串*/

    /**
     * 分析一个单词的后缀回文串情况
     * @param word
     * @return
     */
    private HashSet<Integer> analyzeSuffix(String word){

    }
    /**
     * 记录数组中的各个单词前后缀最长的回文串
     * @param words 全部单词的数组
     * @param suffix 记录后缀的回文串情况
     */
    private void analyzePalindrome(String[] words, HashMap<String,HashSet<Integer>> suffix){

    }
    public List<List<Integer>> palindromePairs(String[] words) {
        /*记录一个单词后缀的回文串情况*/
        HashMap<String, HashSet<Integer>> suffix_palindromes = new HashMap<>();
    }
}
