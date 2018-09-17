/*
1. Put all Characters into a Map to record its count number.
2. If count > S.length() + 1 / 2, return an empty String (which maens it would not make an end string)
3. Put all characters formated as int[character(to ACSII), count] into a PQ (sorted by count)
4. Get the characters from PQ, use a StringBuilder to build up the result
5. Situation1 : when sb.length() == 0 || current peek doesn't equal to the last word in the sb
6. Situation2 : we need to get the second low character from PQ.
7. Each time, we get a number from PQ, modify its cound (not equal 0) and put back to the PQ. 
8. Add back the first int[] back if didn't use it.

*/


class Solution {
  public String reorganizeString(String S) {
      Map<Character, Integer> map = new HashMap<>();
      
      for(char c : S.toCharArray()) {
          map.put(c, map.getOrDefault(c, 0) + 1);
          if(map.get(c) > ((S.length() + 1) / 2)) return "";
      }
      
      PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
      
      for(char c : map.keySet()) {
          int[] character = new int[]{c, map.get(c)};
          pq.add(character);
      }
      
      StringBuilder sb = new StringBuilder();
      
      while(!pq.isEmpty()) {
          /* if sb.length == 0 and first[0] doesn't equal to the last word in sb */
          int[] first = pq.poll();
          if(sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
              /* Convert integer into char */
              sb.append((char)first[0]);
              /* Add back the current character if it still have */
              if(--first[1] > 0) {
                  pq.add(first);
              }   
          }
          else {
              int[] second = pq.poll();
              sb.append((char)second[0]);
              if(--second[1] > 0) {
                  pq.add(second);
              }
              /* add back the first pair which we didin't use */
              pq.add(first);
          }
      }
      
      return new String(sb.toString());
  }
}