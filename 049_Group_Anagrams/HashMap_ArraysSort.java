class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
     
      List<List<String>> result = new ArrayList<>();
      Map< String, List<String>> map = new HashMap<>();
      
      for(int i = 0; i < strs.length; i++) {
          String currentString = strs[i];
          
          // Transfer String into Char Array inorder to sort them
          char[] currentCharArray = currentString.toCharArray();
          
          // Sorted the characters array and format it to our key
          // Make the order doesn't matter
          // Used String.valueOf instead toString() 
          Arrays.sort(currentCharArray);
          String currentKey = String.valueOf(currentCharArray);
          
          if(!map.containsKey(currentKey)) {
              map.put(currentKey, new ArrayList<String>());
              map.get(currentKey).add(currentString);
          } else {
              map.get(currentKey).add(currentString);
          }
      }
      
      // Used those key String we stored in map to get the results
      for(String key : map.keySet()){
          result.add(map.get(key));
      }
      
      return result;
  }    
}