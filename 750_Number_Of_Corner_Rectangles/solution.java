/*
          [i0, j0]    i = i0 + 1 ; j = j0 + 1
           /  \
          /    \
[i0, j]   \    / [i, j0]
           \  /
           [i, j] 

    - Find grid[i0][j0] equals to 1
    - Find grid[i][j]: check gird[i][j] equal to 1 -> count += grid[i0][j] * grid[i][j0]
*/