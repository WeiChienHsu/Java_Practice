import java.util.Scanner;

public class string {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println(simplifyPath(path));
    }


    public static String simplifyPath(String path){
        if (path == null || path.length() ==0) {
            return new String();
        }

        path = path.trim();
        StringBuilder sb = new StringBuilder();
        String[] strs = path.split("/");

        int count = 0;
        // Pointer from right to left
        for(int i = strs.length - 1; i >= 0; i--) {
            if(strs[i].equals("..")){
                count ++;
            } else if (!strs[i].equals(".") && !strs[i].equals("")) {
               // Meet number, if count = 0, insert in sb (first index)
                // If count > 0, ignore the words
               if(count > 0) {
                   count --;
               } else {
                   sb.insert(0, "/" + strs[i]);
               }
            }
        }
        // if there is no words in sb, give a "/", or transfer it into String
        return sb.length() == 0 ? new String("/") : sb.toString();
    }




}
