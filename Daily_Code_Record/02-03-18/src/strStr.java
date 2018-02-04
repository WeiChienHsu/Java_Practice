public class strStr {
    public static void main(String[] args) {
        String source = "";
        String target = "";
        System.out.println(strStr(source, target));
    }

        public static int strStr(String haystack, String needle) {
            if (haystack == null || needle == null) {
                return -1;
            }

            if(haystack.equals(needle)) {
                return 0;
            }

            for (int i = 0; i <= (haystack.length() - needle.length()); i++) {
                for (int j = 0; j < needle.length(); j++) {
                    if (haystack.charAt(i + j) != needle.charAt(j)) {
                        break;
                    }
                    if(j == needle.length() - 1) {
                        return i;
                    }
                }


            }
            return -1;
        }
    }
