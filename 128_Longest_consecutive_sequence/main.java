class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int longest = 0;
        for(int num : nums) {
            if(!map.containsKey(num)) {
                int left = map.containsKey(num - 1)? map.get(num - 1) : 0;
                int right = map.containsKey(num + 1)? map.get(num + 1) : 0;
                int sum = left + right + 1;
                map.put(num, sum);
                longest = Math.max(longest, sum);
                
                if(map.containsKey(num - left)) map.put(num - left, sum);
                if(map.containsKey(num + right)) map.put(num + right, sum);
            }
            else{
                continue;
            }
        }
        return longest;
    }
}