class Solution {
  public boolean repeatedSubstringPattern(String s) {
      for(int i = 1; i <= s.length() / 2 + 1 ; i++) {
          
          
          // 如果擷取的重複字串已經大於元字串一半長，後面不需要判斷
          if(i * 2 > s.length()) return false;
          // 如果擷取的字串無法整除原字串長度，不需要考慮後面是否相同
          if(s.length() % i != 0) continue;
          
          // 驗證後面是否出現不同的數目
          
          String currentRecord = s.substring(0, i);
          boolean pass = true;
          
          int k;
          for(k = i; k < s.length() - i + 1; k = k + i) {
              String nextRecord = s.substring(k, k + i);
              if(!nextRecord.equals(currentRecord)) {
                  pass = false;
                  break;
              }
          }
          
          if(pass) return true;
          pass = true;
      }
      return false;
  }
}