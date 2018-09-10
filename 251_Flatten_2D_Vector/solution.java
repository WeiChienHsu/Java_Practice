public class Vector2D implements Iterator<Integer> {
    
  List<List<Integer>> list;
  int currentRow;
  int currentCol;

  public Vector2D(List<List<Integer>> vec2d) {
      this.list = vec2d;
      currentRow = 0;
      currentCol = -1;
  }

  @Override
  public Integer next() {
      return list.get(currentRow).get(currentCol);
  }

  @Override
  public boolean hasNext() {
      if(list.size() == 0 ) return false;
      
      /* Current row has next element */
      if(currentCol + 1 < list.get(currentRow).size()) {
          currentCol ++;
          return true;
      } 
      
      while (currentRow + 1 < list.size()) {
          if(list.get(currentRow + 1).size() == 0) {
              currentRow++;
              currentCol = 0;            
          } else {
              /* There is a next Row */
              currentRow++;
              currentCol = 0;       
              return true;    
          }
      }  
      
      
      
      return false;
  }
}

/**
* Your Vector2D object will be instantiated and called as such:
* Vector2D i = new Vector2D(vec2d);
* while (i.hasNext()) v[f()] = i.next();
*/