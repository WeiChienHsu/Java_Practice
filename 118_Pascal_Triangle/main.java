class Solution {
  public List<List<Integer>> generate(int numRows) {
      List<Integer> res = new ArrayList<Integer>();
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      
      for(int i = 0; i < numRows; i++) {
          
          res.add(0, 1);
          
          for(int j = 1; j < res.size() - 1; j++) {
              res.set(j, res.get(j) + res.get(j+1));
          }
          
          result.add(new ArrayList<Integer>(res));
      }
      return result;
  }
}