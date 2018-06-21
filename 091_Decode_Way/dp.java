public class Solution {
  public int numDecodings(String s) {
      if(s == null || s.length() == 0) {
          return 0;
      }
      int n = s.length();
      int[] dp = new int[n+1];
      dp[0] = 1;
      dp[1] = s.charAt(0) != '0' ? 1 : 0;
      
      for(int i = 2; i <= n; i++) {
          int oneletter = Integer.valueOf(s.substring(i-1, i));
          int twoletters = Integer.valueOf(s.substring(i-2, i));
          
          // 測試單獨一個是否可以沿用前面累積的解
          // "270" & "5"
          // 如果符合條件：沿用 "270" 可組成的解
          if(oneletter >= 1 && oneletter <= 9) {
             dp[i] += dp[i-1];  
          }
          
          // 測試與前面一個是否可組成， "03" 就無法組成
          // "27" & "05"
          // 如果符合條件，沿用"27"可以組成的解
          
          if(twoletters >= 10 && twoletters <= 26) {
              dp[i] += dp[i-2];
          }
      }
      
      return dp[n];
  }
}