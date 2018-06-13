class Solution {
  public int[] findRedundantConnection(int[][] edges) {
      UnionFindSet set = new UnionFindSet(edges.length);
      for(int[] edge : edges) {
          int u = edge[0];
          int v = edge[1];
          System.out.println(u);
          System.out.println(v);
          
          if(!set.Union(u, v)) {
              return edge;
          }
      }
      return new int[]{};
  }
}

class UnionFindSet {
  private int[] ranks;
  private int[] parents;
  public UnionFindSet(int n) {
      this.ranks = new int[n + 1];
      this.parents = new int[n + 1];
      for(int i = 0; i < n + 1; i++) {
          ranks[i] = 1;
          parents[i] = i;
      }    
  }
  
  public boolean Union(int u, int v) {
      int rootU = Find(u);
      int rootV = Find(v);
      
      if(rootU == rootV) return false;
      
      if(this.ranks[rootU] > this.ranks[rootV]) {
          this.parents[rootV] = rootU;
      } else if(this.ranks[rootU] < this.ranks[rootV]) {
          this.parents[rootU] = rootV;
      } else {
          parents[rootV] = rootU;
          this.ranks[rootU]++;
      }
      
      return true;
  }
  
  public int Find(int u) {
      while(this.parents[u] != u) {
          this.parents[u] = this.parents[this.parents[u]];
          u = this.parents[u];
      }
      return u;
  }
}