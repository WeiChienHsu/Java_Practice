class Solution {
  public String[] uncommonFromSentences(String A, String B) {
      Map<String, Integer> map = new HashMap<>();
      List<String> results = new ArrayList<>();
      for(String s : A.split(" ")) {
          map.put(s, map.getOrDefault(s, 0) + 1);
      }
      
      for(String s : B.split(" ")) {
          if(!map.containsKey(s)) {
              map.put(s, 1);
          }
          else {
              map.put(s, -1);
          }       
      }
      
      for(String s : map.keySet()) {
          if(map.get(s) == 1) {
              results.add(s);
          }
      }
      
      String[] resultArray = new String[results.size()];
      for(int i = 0; i < results.size(); i++) {
          resultArray[i] = results.get(i);
      }
      
      return resultArray;
  }
}