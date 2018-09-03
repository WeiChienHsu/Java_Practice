class Solution {
  public String mostCommonWord(String paragraph, String[] banned) {
      Map<String, Integer> map = new HashMap<>();
      Set<String> set = new HashSet<>(Arrays.asList(banned));
      String result = " ";
      int max = 0;
      
      String[] strs = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
      
      for(int i = 0; i < strs.length; i++) {
          map.put(strs[i], map.getOrDefault(strs[i], 0) + 1);
      }
      
      for(String str : map.keySet()) {
          if(set.contains(str)) continue;
          if(max < map.get(str)) {
              result = str;
              max = map.get(str);
          }
      }
      
      return result;
  }
}