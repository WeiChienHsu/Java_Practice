import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bucket {
    public static void main(String[] args) {

        int [] nums = {1,3,3,2,4,3,5,2};

        List<Integer>[] bucket = new List[nums.length+1];
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for(int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n,0) +1 );
        }

        for(int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if(bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }

            bucket[frequency].add(key);
        }

        List<Integer> result = new ArrayList<>();

        for(int pos = bucket.length - 1; pos >= 0 && result.size() < 2; pos--) {
            if(bucket[pos] != null) {
                result.addAll(bucket[pos]);
            }
        }

        System.out.println(result);
    }
}
