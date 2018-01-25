class Solution {
  public int maxProfit(int[] prices) {
      int maxCur = 0;
      int maxSoFar = 0;
      for (int i = 0; i < prices.length - 1; i++){
          maxCur = Math.max(0, maxCur += prices[i+1] - prices[i]);
          maxSoFar = Math.max(maxSoFar, maxCur);
      }
      return maxSoFar;
      }
  }
