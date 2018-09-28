/* 
  3 Conditions:
    1) One the strings is empty:
      dp[i][j] = 
        if i == 0 : sum_ascii(s2)
        if j == 0 : sum_ascii(s1)

    2) If the last character in the String is the same, 
        ("cba", "zha") same as ("cb", "zh")
        
        if s1[i] == s2[j]
        dp[i][j] = 
          dp[i - 1][j - 1]
    
    3) Depends on the previous sum 
       Choose one of the three options: remove one in s1, remove one in s2, remove both of them

       MIN(
         ASCII(s1.last) + dp[i - 1][j],
         ASCII(s2.last) + dp[i][j - 1],
         ASCII(s1.last) + ASCII(s2.last) +dp[i - 1][j - 1],
         )

*/


class Solution {
  public int minimumDeleteSum(String s1, String s2) {
      int m = s1.length();
      int n = s2.length();
      int[][] dp = new int[m + 1][n + 1];
      
      for(int i = 0; i <= m; i++) {
          for(int j = 0; j <= n; j++) {
              /* Condition1 */
              if(i == 0 || j == 0) {
                  int sum = 0;
                  for(int c = 1; c <= Math.max(i, j); c++) {
                      sum += (i == 0? s2.charAt(c - 1) : s1.charAt(c - 1));
                  }
                  
                  dp[i][j] = sum;
                  
              } else if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                  /* Condition2 */
                  dp[i][j] = dp[i - 1][j - 1];
                  
              } else {
                  dp[i][j] = Math.min(s1.charAt(i - 1) + dp[i - 1][j], 
                                      s2.charAt(j - 1) + dp[i][j - 1]);
                  dp[i][j] = Math.min(dp[i][j], 
                                      s1.charAt(i - 1) + s2.charAt(j - 1) + dp[i - 1][j - 1]);
              }
          }
      }   
      
      return dp[m][n];
  }
}
