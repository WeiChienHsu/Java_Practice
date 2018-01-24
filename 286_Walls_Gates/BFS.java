private void bfsHelper(int[][] rooms, int i, int j) {
  int row = rooms.length;
  int col = rooms[0].lenght;
  Deque<int[]> queue = new ArrayDeque<>();
  queue.offer(new int[]{i,j});
  while(!queue.isEmpty()) {
    int[] cur = queue.pollFirst();
    int x = cur[0];
    int y = cur[1];

    if (isValid(x+1, y, row, col) && rooms[x+1][y] > rooms[x][y] + 1) {
      rooms[x+1][y] = room[x][y] + 1;
      queue.offerLast(new int[] {x+1, y});
    }
    if (isValid(x, y+1, row, col) && rooms[x][y+1] > rooms[x][y] + 1) {
      rooms[x][y+1] = room[x][y] + 1;
      queue.offerLast(new int[] {x, y+1});
    }
    if (isValid(x-1, y, row, col) && rooms[x-1][y] > rooms[x][y] + 1) {
      rooms[x-1][y] = room[x][y] + 1;
      queue.offerLast(new int[] {x-1, y});
    }
    if (isValid(x, y-1, row, col) && rooms[x][y-1] > rooms[x][y] + 1) {
      rooms[x][y-1] = room[x][y] + 1;
      queue.offerLast(new int[] {x, y-1});
    }
  }

  private boolean isValid(int x, int y, int row, int col) {
    return x >= 0 && y >= 0 && x < row && y < col;
  }
}