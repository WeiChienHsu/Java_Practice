// 1. 將字串內的character以及其index(開始與結束)記錄在LinkedHashMap當中
// 2. 把Map遍歷一次，並作條件判斷
// - newFirst > oldFirst && newFirst < oldEnd : 判斷 newEnd > oldEnd 需要更新oldEnd，不然直接continue
// - 如果不符合以上條件，代表最新的字母在原本的區間範圍外(newFisrt > oldEnd)，紀錄當時的長度
// - res.add(oldEnd - oldStart + 1)
// - 更新oldEnd和oldStart都等於oldEnd

class Solution {
  public List<Integer> partitionLabels(String S) {
      Map<Character, List<Integer>> map = new LinkedHashMap<>();
      for(int i = 0; i < S.length(); i++) {
          char n = S.charAt(i);
          if(!map.containsKey(n)) map.put(n, new ArrayList<Integer>());
          List<Integer> list = map.get(n);
          
          // Add number into the list
          if(list.size() < 2) {
              list.add(i);
              list.add(i);
          } else {
              list.set(1, i);
          } 
      }
      
      List<Integer> res = new ArrayList<>();
      int oldStart = -1;
      int oldEnd = -1;

      for(char n : map.keySet()) {
          int newStart = map.get(n).get(0);
          int newEnd = map.get(n).get(1);
          
          // Init the First Boundry
          if(oldStart == -1 && oldEnd == -1) {
              oldStart = newStart;
              oldEnd = newEnd;
          }
          
          // 如果新的開始大於目前的結尾，必須要紀錄目前為止的區間
          if(newStart > oldEnd) {
              res.add(oldEnd - oldStart + 1);
              oldEnd = newEnd;
              oldStart = newStart;
          } else {
              // 如果新的開始在目前結尾前，要檢查新的結尾是否超過原本區間，更新區間
              if(newEnd > oldEnd) {
                  oldEnd = newEnd;
              }
          }
      }
      
      res.add(oldEnd - oldStart + 1);

      return res;
  }
}