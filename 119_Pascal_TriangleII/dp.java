class Solution {
  public List<Integer> getRow(int rowIndex) {
      List<Integer> res = new ArrayList<>();
      if(rowIndex < 0) return res;
      
      for(int i = 0; i < rowIndex + 1; i++) {
          
          // Add number 1 in the first position
          res.add(0, 1);
          
          // Sum the two consecutive number from index of 1 to the length - 1
          for(int j = 1; j < res.size() - 1; j++) {
              res.set(j, res.get(j) + res.get(j + 1));
          }
      }
      return res;
  }
}