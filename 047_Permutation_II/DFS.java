/*
    Sorted the array to make sure the situation likes [3,3,0,3] which might not deal with the last 3 in my method

    Maintains only one variables:
        - tempList: record the current add-in result
        - boolean visited[]: record the index of number that has been added
        
    Only one step to do:
        - Add in a new element after checking its not exist in the list
    
    Base case:
        - If the length of temp list is equal the origin arr, add them into the result list

*/


class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfsHelper(results, nums, visited, new ArrayList<>());
        return results;
    }
    
    public void dfsHelper(List<List<Integer>> results, int[] nums, boolean[] visited, List<Integer> tempList) {
        
        if(tempList.size() == nums.length) {
            results.add(new ArrayList<>(tempList));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            
            if(i != 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            
            tempList.add(nums[i]);
            visited[i] = !visited[i];
            dfsHelper(results, nums, visited, tempList);
            visited[i] = !visited[i];
            tempList.remove(tempList.size() - 1);
        }
    }
}