import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bucket_sort {

    public static void main(String[] args) {
        int [] nums = {1,3,3,2,4,3,5,2};
        int k = 2;
        System.out.println(topKFrequent(nums, k));
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {

        // Create a bucket with space to stand for the frequency

        List<Integer>[] bucket = new List[nums.length+1];

        // Create a HashMap to record the frequency <num, frequency>

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n,0) +1 );
        }

        // Put frequency of number into Bucket -> null,[1, 4, 5], [2] , [3] , null

        for(int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if(bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }

            bucket[frequency].add(key);
        }

        // Create a result arraylist to collect k number from the end

        List<Integer> result = new ArrayList<>();

        for(int pos = bucket.length - 1; pos >= 0 && result.size() < k; pos--) {
            if(bucket[pos] != null) {
                result.addAll(bucket[pos]);
            }
        }

        return result;
    }
}

