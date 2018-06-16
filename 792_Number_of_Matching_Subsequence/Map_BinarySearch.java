class Solution {
  public int numMatchingSubseq(String S, String[] words) {
      Map<Character, List<Integer>> map = new HashMap<>();
      // Put all charactors into the Map 
      for(int i = 0; i < S.length(); i++) {
          if(!map.containsKey(S.charAt(i))) {
              map.put(S.charAt(i), new ArrayList<Integer>());
          }
          map.get(S.charAt(i)).add(i);
      }
      
      int count = 0;
      
      for(int i = 0; i < words.length; i++) {
          if(Solution.processSingleWord(words[i], map)) count++;
      }
      
      return count;        

  }
  
  public static boolean processSingleWord(String s, Map<Character, List<Integer>> map){
      int previousIndex = -1;
      for(int i = 0; i < s.length(); i++) {
          char currentChar = s.charAt(i);
          // Could not find the charactor in the Map
          if(!map.containsKey(currentChar)) return false;
          
          // Binary Search the index more than previousIndex
          int currentIndex = Solution.binarySearchForNextIndex(map.get(currentChar), previousIndex);
          
          // The index of current charactor is less the the previous charactor
          if(currentIndex < 0) return false;
          previousIndex = currentIndex;
      }
      
      return true;
  }
  
  public static int binarySearchForNextIndex(List<Integer> list, int previousIndex) {
      int start = 0, end = list.size() - 1;
      while(start + 1 < end) {
          int mid = start + (end - start) / 2;
          if(list.get(mid) > previousIndex) {
              end = mid;
          } else {
              start = mid;
          }
      }
      return list.get(end) <= previousIndex? -1 : list.get(start) > previousIndex? list.get(start) : list.get(end);
  }
  
}