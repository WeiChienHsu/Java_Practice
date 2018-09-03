class Solution {
  public boolean judgeCircle(String moves) {
      Position p = new Position();
      for(int i = 0; i < moves.length(); i++) {
          char c = moves.charAt(i);
          switch(c){
              case 'U':
                  p.moveUp();
                  break;
              case 'D':
                  p.moveDown();
                  break;
              case 'R':
                  p.moveRight();
                  break;
              case 'L':
                  p.moveLeft();
                  break;
          }
              
      }    
      return p.inOriginalPoint();
  }
}

class Position {
  int row;
  int col;
  public Position() {
      this.row = 0;
      this.col = 0;
  }
  
  public void moveUp() {
      this.row -= 1;
  }
  
  public void moveDown() {
      this.row += 1;
  }
  
  public void moveLeft() {
      this.col -= 1;
  }
  
  public void moveRight() {
      this.col += 1;
  }
  
  public boolean inOriginalPoint() {
      return this.row == 0 && this.col == 0;
  }
  
}