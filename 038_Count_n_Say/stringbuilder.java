class Solution {
  public String countAndSay(int n) {
      // Base Case 
      if(n == 1) return "1";
      
      String currentString = countAndSay(n - 1); // Get into recursive step to get current String
      int count = 0;
      StringBuilder sb = new StringBuilder();
      // 將 String 轉換成 char array
      char[] c = currentString.toCharArray();
      
      for(int i = 0; i <= c.length; i++) {
        // 當 i 超過 邊界，或是在邊界內且與前個char相同時，處理加入
          if(i == c.length || (i - 1 >= 0 && c[i] != c[i - 1])) {
              sb.append(count);
              sb.append(c[i - 1]);
              count = 0;
          }
          // 預設第一個數字已經被計算
          count ++;
      }
      return sb.toString();
  }
}