class Solution {
  public int findContentChildren(int[] g, int[] s) {
      Arrays.sort(g);
      Arrays.sort(s);
      int child = 0;
      /* Loop through each size of cookies */
      for(int i = 0; i < s.length && child < g.length; i++) {
          if(s[i] >= g[child]) {
              child++;
          }
      }
      return child;
  }
}