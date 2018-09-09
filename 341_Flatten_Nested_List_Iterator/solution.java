/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

  public List<NestedInteger> nestedList;
  public List<Integer> list;
  public int current;
  
  public NestedIterator(List<NestedInteger> nestedList) {
      this.nestedList = nestedList;
      this.list = new ArrayList<>();
      this.current = 0;
      getNumberInList(nestedList, list);

  }
  
  public void getNumberInList(List<NestedInteger> nestedList, List<Integer> list) {
      for(int i = 0; i < nestedList.size(); i++) {
          if(nestedList.get(i).isInteger()) {
              list.add(nestedList.get(i).getInteger());
          } else {
              getNumberInList(nestedList.get(i).getList(), list);
          }
      }
  }

  @Override
  public Integer next() {
      return list.get(current++);
  }

  @Override
  public boolean hasNext() {
      return current < list.size();
  }
}

/**
* Your NestedIterator object will be instantiated and called as such:
* NestedIterator i = new NestedIterator(nestedList);
* while (i.hasNext()) v[f()] = i.next();
*/