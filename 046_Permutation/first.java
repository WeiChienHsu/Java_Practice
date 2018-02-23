List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return res;
        res.add(new ArrayList<Integer>());

        for(int i = 0; i < nums.length; i++) {
            // Next level
            List<List<Integer>> nextRes = new ArrayList<List<Integer>>();
            // for each list in res
            for(List<Integer> list : res) {
                for(int j = 0; j < list.size() + 1; j++ ){
                    // Copy a list to nextList
                    List<Integer> nextList = new ArrayList<>(list);
                    // Add each position in list
                    nextList.add(j,nums[i]);
                    nextRes.add(nextList);
                }
            }
            // Move to next level
            res  = nextRes;
        }
        return res;
    }