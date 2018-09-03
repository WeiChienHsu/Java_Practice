class Solution {
  public boolean lemonadeChange(int[] bills) {
      Map<Integer, Integer> map = new HashMap<>();
      map.put(5, 0);
      map.put(10, 0);
      map.put(20, 0);
      
      for(int bill : bills) {
          switch(bill) {
              case 5:
                  map.put(5, map.get(5) + 1);
                  break;
                  
              case 10:
                  if(map.get(5) == 0) return false;
                  map.put(10, map.get(10) + 1);
                  map.put(5, map.get(5) - 1);
                  break;
              
              case 20:
                  if((map.get(10) == 0 && map.get(5) < 3) || (map.get(5) == 0)) return false;
                  map.put(20, map.get(20) + 1);
                  if(map.get(10) != 0) {
                      map.put(10, map.get(10) - 1);
                      map.put(5, map.get(5) - 1);
                  }
                  else {
                      map.put(5, map.get(5) - 3);
                  }
                  break;
          }
      }
      return true;
  }
}