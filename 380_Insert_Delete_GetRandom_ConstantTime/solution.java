/* 
 List : Record all element in the RandomSet -> List.removeLast() : O(1) time complexity 
 Map : Record Value and Index pair in List -> Map.containsKey() : O(1) time complexity
 
 Get Random element in the List : 
 
 java.util.Random rand = new java.util.Random();
 int index = rand.nextInt(nums.size()) : O(1) time complexity

 Design::

 RandomSet.insert()::
    Check if the value is in the Map 
      Map: put value and index into the map
      List: add value into the List
  
  RandomSet.remove()::
    Check ig the value is in the Map
      Map: get the index of removed value
      List: Switch the index of removed value provided by Map with the lastIndex of element in List
      List: Get the privous lastElement value before switching
      Map: Update the previous lastElement in the Map for a new index
      List: Remove the current last element O(1)
  
  Random.get()::
    return a random index in the list
*/

class RandomizedSet {
    
  public Map<Integer, Integer> map;
  public List<Integer> list;
  Random rand = new java.util.Random(); 
  
  /** Initialize your data structure here. */
  public RandomizedSet() {
      map = new HashMap<>();
      list = new ArrayList<>();
  }
  
  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
      if(map.containsKey(val)) {
          return false;
      } else {
          map.put(val, list.size());
          list.add(val);
      }
      return true;
  }
  
  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
      if(!map.containsKey(val)) {
          return false;
      } else {
          /* get the removed index and remove that val from map */
          int index = map.get(val);
          map.remove(val);
          
          /* Need to Switch the last element when the removed one not in the last position */
          if(index < list.size() - 1){
                          /* Switch the removed element with the last element */
              int lastElement = list.get(list.size() - 1);
              list.set(list.size() - 1, list.get(index)); /* Change the last element */
              list.set(index, lastElement);   /* Give the empth space previous last element */
              map.put(lastElement, index);
          }
          /* Remove the last element constantsly */
          list.remove(list.size() - 1);
      }
      return true;
  }
  
  /** Get a random element from the set. */
  public int getRandom() {
      return list.get(rand.nextInt(list.size()));
  }
}

/**
* Your RandomizedSet object will be instantiated and called as such:
* RandomizedSet obj = new RandomizedSet();
* boolean param_1 = obj.insert(val);
* boolean param_2 = obj.remove(val);
* int param_3 = obj.getRandom();
*/