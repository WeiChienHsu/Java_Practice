/*
    Maintains 4 Variables
        - StringBuilder sb: record the temporary String
        - List<String> list: record the result
        - int currentIndex: the index of String now is processing
        - int count: Number of words to be abbr.
        - String word: Original word
    
    Tow different steps
        - 1. Abbreviate more words -> backtracking (currentIndex + 1, count + 1)
        - 2. Record the current single words -> sb.append(num) if count > 0 and backtrackint (currentIndex + 1, count = 0)
    
    Backtracking:
        - After either Base case or Two differenct steps
        - Cleat the String Builder for a new count ex.(1ord, 2rd, 3d, 4)
    
    Base Case:
        - when current Index equals to the lenght of original String
        - append(count) if count > 0 and add the String in to the result list
*/

public class Solution {
  public List<String> generateAbbreviations(String word) {
      List<String> result = new ArrayList<>();
      StringBuilder sb = new StringBuilder();
      backtracking(result, word, sb, 0, 0);
      return result;
  }
  
  public void backtracking(List<String> result, String word, StringBuilder sb, int currentIndex, int count) {
      /* Base Case */
      if(currentIndex == word.length()) {
          if(count > 0) sb.append(count);
          result.add(sb.toString());
      } else {
          /* Abbreviate more words */
          backtracking(result, word, sb, currentIndex + 1, count + 1);
          
          /* Record the current single words */
          if(count > 0) sb.append(count);
          sb.append(word.charAt(currentIndex));
          backtracking(result, word, sb, currentIndex + 1, 0);
          
      }
      
      sb.setLength(word.length());
  }
}