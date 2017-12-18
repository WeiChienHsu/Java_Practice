# Top K Frequent Elements

## Given a non-empty array of integers, return the k most frequent elements.

### Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

```
For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].
```

## Solution - Bucket Sort
- Scaned and Used HashMap to record the number
- Map<number, frequency>
```
n        1     2    3

count    3     2    1
```
```java
 // Create a HashMap to record the frequency <num, frequency>

Map<Integer, Integer> frequencyMap = new HashMap<>();
for(int n : nums) {
    frequencyMap.put(n, frequencyMap.getOrDefault(n,0) +1 );
}
```

- Create a bucket list with space n + 1 to save the number

```
frequency  0     1   2   3   4    5    6  

number     null [3] [2] [1] null null null
```

```java
// Create a bucket with space to stand for the frequency
List<Integer>[] bucket = new List[nums.length+1];
// Put frequency of number into Bucket -> null,[1, 4, 5], [2] , [3] , null
for(int key : frequencyMap.keySet()) {
    int frequency = frequencyMap.get(key);
    if(bucket[frequency] == null) {
        bucket[frequency] = new ArrayList<>();
    }

    bucket[frequency].add(key);
}
```

- Create a Result ArrayList and Return the result
```java
List<Integer> result = new ArrayList<>();

for(int pos = bucket.length - 1; pos >= 0 && result.size() < k; pos--) {
    if(bucket[pos] != null) {
        result.addAll(bucket[pos]);
    }
}
return result;
```

