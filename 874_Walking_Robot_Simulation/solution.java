class Solution {
  public int robotSim(int[] commands, int[][] obstacles) {
      int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
      int currentDir = 0; /* 0, 1, 2, 3 */
      int[] currentPosition = new int[]{0, 0};
      int max = Integer.MIN_VALUE;
      
      /* Convert an int array to a String to recognize the obstacle */
      Set<String> set = new HashSet<>();
      for(int[] obstacle : obstacles) {
          set.add(obstacle[0] + ":" + obstacle[1]);
      }
      
      for(int command : commands) {
          if(command == -1 || command == -2) {
              /* Get new direction by the command */
              currentDir = getCurrentDirection(currentDir, command);
          } else if(command >= 1 && command <= 9) {
              int steps = command;
              int[] dir = dirs[currentDir];
              /* Step by step go forward to the destionation */
              for(int i = 0; i < steps; i++) {
                  currentPosition[0] += dir[0];
                  currentPosition[1] += dir[1];
                  /* check if the current position is in the obstaacles */
                  if(set.contains(currentPosition[0] + ":" + currentPosition[1])) {
                      /* Stop at pervious position*/
                      currentPosition[0] -= dir[0];
                      currentPosition[1] -= dir[1];                        
                      break;
                  }
              }
          }
          max = Math.max(max, ((currentPosition[0]) * (currentPosition[0])) +                                                     ((currentPosition[1]) * (currentPosition[1])));
      }
      
      return max;
  }
  
  
  public int getCurrentDirection(int currentDir, int command) {
      if(command == -2) {
          if(currentDir == 0) {
              return 3;
          } else {
              return --currentDir;
          }
      } else {
          if(currentDir == 3) {
              return  0;
          } else {
              return ++currentDir;
          }
      }
  }
}