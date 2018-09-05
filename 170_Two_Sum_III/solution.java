/* 
 Large Add and Larget Find Trade Off.
 如果 Large Add : 要控制存儲的量，使用List來減少存取，並且不需要在每次存取後進行排序，Finding的時候用O(n) + O(1) Map access
 如果 Larget Find: 要優化Add，讓每次存取後直接排序，Finding的時候用O(1)的Binary Search
*/

class TwoSum {
    
  public List<Integer> list;
  public Map<Integer, Integer> map;

  /** Initialize your data structure here. */
  public TwoSum() {
      list = new ArrayList<>();
      map = new HashMap<>();
  }
  
  /** Add the number to an internal data structure.. */
  public void add(int number) {
      if(!map.containsKey(number)) {
          map.put(number, 1);
          list.add(number);
      } else if(map.get(number) < 2) {
          map.put(number, map.get(number) + 1);
      }
  }
  
  /** Find if there exists any pair of numbers which sum is equal to the value. */
  public boolean find(int value) {
      for(int i = 0; i < list.size(); i++) {
          int number1 = list.get(i);
          int number2 = value - number1;
          if((number2 == number1 && map.get(number1) > 1) || (number2 != number1 && map.containsKey(number2))) {
              return true;
          }
      }
      return false;
  }
}

/**
* Your TwoSum object will be instantiated and called as such:
* TwoSum obj = new TwoSum();
* obj.add(number);
* boolean param_2 = obj.find(value);
*/