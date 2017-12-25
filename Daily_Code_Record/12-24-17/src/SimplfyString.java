
public class SimplfyString {
    public static void main(String[] args) {
        String s = "/.";

        System.out.println(simplifyPath(s));
    }

    public static String simplifyPath(String path){
        StringBuilder sb = new StringBuilder();

        path = path.trim();
        String[] strs = path.split("/");
        int count = 0;

        for(int i = strs.length - 1; i >= 0; i-- ) {
            if(strs[i].equals("..")) {
                count ++;
            } else if (!strs[i].equals("") && !strs[i].equals(".")) {
                if(count > 0) {
                    count--;
                } else {
                    sb.insert(0, "/" + strs[i]);
                }
            }
        }

        return sb.length() == 0 ? new String("/") : sb.toString();
    }
}