class Solution {
    public boolean backspaceCompare(String S, String T) {
        StringBuilder sb_s = new StringBuilder();
        StringBuilder sb_t = new StringBuilder();
        
        for(int i = 0; i < S.length(); i++) {
            if(S.charAt(i) == '#' && sb_s.length() > 0) {
                sb_s.deleteCharAt(sb_s.length() - 1);
            } else if(S.charAt(i) != '#') {
                sb_s.append(S.charAt(i));
            }
        }
        
        for(int i = 0; i < T.length(); i++) {
            if(T.charAt(i) == '#' && sb_t.length() > 0) {
                sb_t.deleteCharAt(sb_t.length() - 1);
            } else if(T.charAt(i) != '#') {
                sb_t.append(T.charAt(i));
            }
        }
        
        return Objects.equals(sb_t.toString(), sb_s.toString());
    }
}