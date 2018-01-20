public static int climbStairs(int n) {
  if(n == 0) return 0;
  if(n == 1) return 1;
  if(n == 2) return 2;

  int solution_n_1 = 2;
  int solution_n_2 = 1;
  int solution_n = 0;
  for(int i = 3; i <= n; i++ ) {
      solution_n = solution_n_1 + solution_n_2;
      solution_n_2 = solution_n_1;
      solution_n_1 = solution_n;
  }
  return solution_n;

  }

}