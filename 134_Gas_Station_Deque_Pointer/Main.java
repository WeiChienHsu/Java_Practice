class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return -1;
        }
        
        if (gas.length != cost.length) {
            return -1;
        }
        

        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];
        
        while(start > end) {
            // Enough gas
            if(sum >= 0) {
                sum += gas[end] - cost[end]; // count the remain gas
                end++  ; // keep move on
            } else { // Not enough Gas
                start--  ; // brower gas from last gas station
                sum += gas[start] - cost[start];
            }
        }
        return sum >= 0 ? start : -1;

    }
}