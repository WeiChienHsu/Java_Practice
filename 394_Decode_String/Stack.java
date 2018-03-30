class Solution {
  public String decodeString(String s) {
      Deque<Integer> multiStack = new ArrayDeque<>();
      Deque<StringBuilder> charStack = new ArrayDeque<>();
      charStack.offerLast(new StringBuilder());
      int multi = 0;
      
      for(int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          if(c == '['){ 
              // 將累積的"倍數"，加入"multi"的Stack中，並且歸零
              // 加入一個空的StringBuilder在charStack中，讓之後的char可以append
              multiStack.offerLast(multi);
              multi = 0;
              charStack.offerLast(new StringBuilder());
              
          } else if (Character.isDigit(c)) {
              // 累積目前的"倍數"，將先前累積的 *10 + curNumber
              multi = multi * 10 + Character.getNumericValue(c);
          } else if(c == ']') {
              // pop multiStack 當做要乘的倍數
              // pop charStack 是我們要乘以的char 
              // 相乘之後加入一個新的StringBuilder 
              //結束後將這結果重新加入已在charStack內的值
              
              int times = multiStack.pollLast();
              StringBuilder multiChar = charStack.pollLast();
              StringBuilder tempStr = new StringBuilder();
              for(int j = 0; j < times; j++ ) {
                  tempStr.append(multiChar);
              }
              charStack.offerLast(charStack.pollLast().append(tempStr));
          } else {
              // Meet char
              // 將此char append進入
              charStack.offerLast(charStack.pollLast().append(c));
          }
      }
      return charStack.peek().toString();
  }
}