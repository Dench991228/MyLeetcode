import java.util.TreeMap;

class Trie {
    /**
     * 内部类，trie树节点
     */
    private class TrieNode{
        /*节点上的字符*/
        private char val;
        /*之后的字符*/
        private TreeMap<Character,TrieNode> successor;
        private TrieNode(){
            super();
        }
        private TrieNode(char c){
            this.val = c;
            this.successor = new TreeMap<>();
        }
    }
    /*全部节点*/
    private TreeMap<Character,TrieNode> Nodes;
    /** Initialize your data structure here. */
    public Trie() {
        Nodes = new TreeMap<>();
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
        cur.successor.put('\0',new TrieNode());
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
}