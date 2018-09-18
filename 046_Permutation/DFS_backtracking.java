/*
    Maintains only one variables:
        - tempList: record the current add-in result
        
    Only one step to do:
        - Add in a new element after checking its not exist in the list
    
    Base case:
        - If the length of temp list is equal the origin arr, add them into the result list

*/


class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        dfsHelper(results, nums, new ArrayList<>());
        return results;
    }
    
    public void dfsHelper(List<List<Integer>> results, int[] nums, List<Integer> tempList) {
        if(tempList.size() == nums.length) {
            results.add(new ArrayList<>(tempList));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(tempList.contains(nums[i])) continue;
            tempList.add(nums[i]);
            dfsHelper(results, nums, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}