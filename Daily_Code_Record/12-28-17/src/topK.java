import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class topK {
    
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,4,5,2,1};
        int k = 2;
        List<Integer> list = topKFrequent(nums, k);
        for(int i = 0; i < list.size(); i++ ) {
            System.out.println(list.get(i));
        }

    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer>[] bucket = new ArrayList[nums.length +1];
        List<Integer> res = new ArrayList<>();

        // Map
        for(int i = 0; i < nums.length; i ++) {
            int count = freq.containsKey(nums[i]) ? freq.get(nums[i]) : 0;
            freq.put(nums[i], count + 1);
        }

        // Bucket
        for(int key : freq.keySet()) {
            int frequency = freq.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // Result
        for(int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if(bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

}
