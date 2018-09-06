class Solution {
  public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
      Arrays.sort(A);
      Arrays.sort(B);
      Arrays.sort(C);
      Arrays.sort(D);
      
      Map<Integer, Integer> map = new HashMap<>();
      int result = 0;
      
      for(int i = 0; i < A.length; i++) {
          for(int j = 0; j < B.length; j++) {
              int target = 0 - A[i] - B[j];
              map.put(target, map.getOrDefault(target, 0) + 1);
              if(target > C[C.length - 1] + D[D.length - 1]) continue;
          }
      }
      
      for(int i = 0; i < C.length; i++) {
          for(int j = 0; j < D.length; j++) {
              result += map.getOrDefault((C[i] + D[j]), 0);
          }
      }
      
      return result;
  }
}