public class BeautifulArrangement {
  public static void main(String[] args) {
      int N = 5;
      int result = countArrangement(N);
      System.out.println(result);
  }

  public static int countArrangement(int  N){
      return backtracking(new boolean[N + 1], N, 1);
  }

  public static int backtracking(boolean[] visited, int N, int index) {
      /* Base case: meet the end */
      if(index == visited.length) {
          return 1;
      }

      int count = 0;

      for(int i = 1; i <= N; i++) {
          if(!visited[i] && (i % index == 0 || index % i == 0)) {
              visited[i] = true;
              count += backtracking(visited, N, index + 1);
              visited[i] = false;
          }
      }
      return count;

  }
}
