import java.util.TreeMap;

class StreamChecker {
    private class Trie {
        /**
         * 内部类，trie树节点
         */
        private class TrieNode{
            /*节点上的字符*/
            private char val;
            /*之后的字符*/
            private TreeMap<Character, TrieNode> successor;
            private TrieNode(){
                super();
            }
            private TrieNode(char c){
                this.val = c;
                this.successor = new TreeMap<>();
            }
        }
        /*全部节点*/
        private TreeMap<Character, TrieNode> Nodes;
        private TrieNode EndNode;
        /** Initialize your data structure here. */
        public Trie() {
            Nodes = new TreeMap<>();
            EndNode = new TrieNode('\0');
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word.length()==0)return;
            int i, len = word.length();
            TrieNode cur = null;
            /*找出根节点，如果没有就创建*/
            if(!this.Nodes.containsKey(word.charAt(0))){
                cur = new TrieNode(word.charAt(0));
                this.Nodes.put(word.charAt(0),cur);
            }
            else{
                cur = this.Nodes.get(word.charAt(0));
            }
            /*遍历剩下的部分*/
            for(i=1;i<len;i++){
                if(cur.successor.containsKey(word.charAt(i))){
                    cur = cur.successor.get(word.charAt(i));
                }
                else{
                    TrieNode tn = new TrieNode(word.charAt(i));
                    cur.successor.put(word.charAt(i),tn);
                    cur = tn;
                }
            }
            cur.successor.put('\0',EndNode);
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if(word.length()==0)return true;
            int i, len = word.length();
            TrieNode root;
            if(this.Nodes.containsKey(word.charAt(0))){
                root = this.Nodes.get(word.charAt(0));
            }
            else{
                return false;
            }
            for(i=1;i<len;i++){
                if(root.successor.containsKey(word.charAt(i))){
                    root = root.successor.get(word.charAt(i));
                }
                else return false;
            }
            return root.successor.containsKey('\0');
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if(prefix.length()==0)return false;
            TrieNode root;
            int i, len = prefix.length();
            if(this.Nodes.containsKey(prefix.charAt(0))){
                root = this.Nodes.get(prefix.charAt(0));
            }
            else{
                return false;
            }
            for(i=1;i<len;i++){
                if(root.successor.containsKey(prefix.charAt(i))){
                    root = root.successor.get(prefix.charAt(i));
                }
                else return false;
            }
            return true;
        }

        /**
         * target的前缀是否包含trie里面的单词
         * @param target
         * @return 是否
         */
        public boolean include(String target){
            if(target.length()==0)return true;
            int i, len = target.length();
            TrieNode root;
            if(this.Nodes.containsKey(target.charAt(0))){
                root = this.Nodes.get(target.charAt(0));
            }
            else return false;
            for(i=1;i<len;i++){
                if(root.successor.containsKey('\0'))return true;
                else if(root.successor.containsKey(target.charAt(i))){
                    root = root.successor.get(target.charAt(i));
                }
                else{
                    return false;
                }
            }
            return root.successor.containsKey('\0');
        }
    }
    private Trie dict;
    StringBuffer cur_query;
    public StreamChecker(String[] words) {
        this.dict = new Trie();
        for(String s:words){
            StringBuffer sbf = new StringBuffer(s);
            sbf.reverse();
            this.dict.insert(sbf.toString());
        }
    }

    public boolean query(char letter) {
        cur_query.insert(0,letter);
        return this.dict.include(this.cur_query.toString());
    }
}
public class Question1032 {
}
