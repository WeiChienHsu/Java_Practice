class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
      int size = gas.length;
        int sum = 0;
        int start = 0;
        int left_gas = 0;
        
        for(int i = 0; i < size; ++i){
            sum += gas[i] - cost[i];
            if( sum < 0){
                left_gas += sum;
                sum = 0;
                start = i+1;
            }
        }
        left_gas += sum;
        return left_gas < 0? -1 : start;
    }
}