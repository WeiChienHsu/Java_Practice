// 1. 先將字母表 t 內的 String 以 char 和 List<Integer>(index) 的形式存在 Map 當中 O(n)
// 2. 遍歷一次 s ， 從 Map 裡面找到對應的 List O(n)，並且帶著 previous Found Index進去比較， prev 必須要 < 找到的那個Index
// (如果Map當中沒有該char，直接Reture false)
// 3. 利用 Binary Search 找到在List內比prevous Index 大的那個 index，如果沒有，Return -1
// 4. 如果 Binary Search 出來的結果是 -1 ，直接return false，反之，更新previous Index value



class Solution {
  public boolean isSubsequence(String s, String t) {
      Map<Character, List<Integer>> map = new HashMap<>();
      // Put all charactors into the Map 
      for(int i = 0; i < t.length(); i++) {
          if(!map.containsKey(t.charAt(i))) {
              map.put(t.charAt(i), new ArrayList<Integer>());
          }
          map.get(t.charAt(i)).add(i);
      }
      
      int previousIndex = -1;
      for(int i = 0; i < s.length(); i++) {
          char currentChar = s.charAt(i);
          // Could not find the charactor in the Map
          if(!map.containsKey(currentChar)) return false;
          
          // Binary Search the index more than previousIndex
          int currentIndex = Solution.binarySearchForNextIndex(map.get(currentChar), previousIndex);
          
          // The index of current charactor is less the the previous charactor
          if(currentIndex < 0) return false;
          System.out.println(currentChar);
          System.out.println("At");
          System.out.println(currentIndex);
          
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
      return list.get(end) < previousIndex? -1 : list.get(start) > previousIndex? list.get(start) : list.get(end);
  }
  
}