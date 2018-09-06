/* 

Adder -> Sum = A XOR B 
      -> Carry = A and B

 | A | B | Sum | Carry |
 | 0 | 0 |  0  |   0   |
 | 0 | 1 |  1  |   0   |
 | 1 | 0 |  1  |   0   |
 | 1 | 1 |  0  |   1   |  
 
*/


class Solution {
  public int getSum(int a, int b) {
      while(b != 0) {
          int carry = a & b;
          a = a ^ b;
          b = carry << 1;
      }
      return a;
  }
}

