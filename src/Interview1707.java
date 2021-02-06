import java.util.HashMap;

public class Interview1707 {
    /**
     * 并查集的查询父亲节点操作
     * @param parent 保存并查集信息的哈希表
     * @param target 被查询的节点
     * @return 所属的并查集
     * */
    private String getParent(HashMap<String, String> parent, String target){
        if(parent.get(target).compareTo(target)!=0){
            parent.put(target, getParent(parent, parent.get(target)));
        }
        return parent.get(target);
    }

    /**
     * 合并两个集合，在这里务必把字典序较小的字符串放在前面
     * @param parent 储存并查集信息的哈希表
     * @param former 字典序较小的字符串
     * @param latter 字典序较大的字符串
     * */
    private void merge(HashMap<String, String> parent, String former, String latter){
        String root_former = parent.get(former);
        String root_latter = parent.get(latter);
        parent.put(root_latter, root_former);
    }

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, Integer> name_number = new HashMap<>();
        for(String s:names){
            String[] parts = s.split("\\(");
            parent.put(parts[0], parts[1]);
            name_number.put(parts[0], Integer.parseInt(parts[1].substring(0, parts[1].length()-1)));
        }
        for(String s:synonyms){
            s = s.substring(1, s.length()-1);
            String[] name = s.split(",");
            if(name[0].compareTo(name[1])<0)merge(parent, name[0], name[1]);
            else merge(parent, name[1], name[0]);
        }
        HashMap<String, Integer> result = new HashMap<>();
        for(String s: parent.keySet()){
            String root = getParent(parent, s);
            if(!result.containsKey(root))result.put(root, 0);
            result.put(root, result.get(root)+name_number.get(s));
        }
        String[] outputs = new String[result.size()];
        int i = 0;
        for(String s: result.keySet()){
            outputs[i] = s+"("+result.get(s)+")";
            i++;
        }
        return outputs;
    }
}
