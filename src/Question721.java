import java.util.*;

public class Question721 {
    private final HashMap<String, TreeSet<String>> mail_mails = new HashMap<>();
    private final HashMap<TreeSet<String>, String> mails_user = new HashMap<>();
    //通过每一个用户的信息，获得邮箱集合
    private TreeSet<String> getMails(List<String> user){
        TreeSet<String> result = new TreeSet<>();
        int len = user.size();
        int i;
        if(len != 1){
            for(i=1;i<len;i++){
                result.add(user.get(i));
            }
        }
        return result;
    }

    //把一个用户加入到邮箱集合中
    private void addUser(List<String> user){
        TreeSet<String> mails = this.getMails(user);
        boolean existed = false;
        String overlap = "";//重复的邮箱
        for(String s:mails){
            if(this.mail_mails.containsKey(s)){//此用户存在（邮箱已经存在）
                existed = true;
                overlap = s;
                break;
            }
        }
        if(existed){//不需要创建新的用户
            TreeSet<String> cur_mails = this.mail_mails.get(overlap);
            cur_mails.addAll(mails);
        }
        else{
            for(String s:mails){
                this.mail_mails.put(s,mails);
                this.mails_user.put(mails,user.get(0));
            }
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        for(List<String> u:accounts){
            this.addUser(u);
        }
        Set<TreeSet<String>> mails = this.mails_user.keySet();
        for(TreeSet<String> s: mails){
            ArrayList<String> user = new ArrayList<String>();
            user.add(this.mails_user.get(s));
            for(String m:s){
                user.add(m);
            }
            result.add(user);
        }
        return result;
    }
}
