# Top K Frequent Elements _ Bucket Sort

## Given a non-empty array of integers, return the k most frequent elements.

### Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

```
For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].
```

***
## Good to know this way to init the map from Array !!!!!! 
```java
for(int n: nums) {
    map.put(n, map.getOrDefault(n, 0) + 1);
    }
```

## Concept of Heap- O(nlogK) !!!!!
```
num     2  1  7  5  3  12

count   5  6  1  3  2   7 

A Heap with size.() = k

      /    \
     /      \ 
    /  2-5   \
   /   1-6    \


7-1 / 5-3 / 3 -2 couldn't add in
But we could add in 12-7 since 7 > 5

      /     \
     /       \ 
    /  12-7   \
   /   1-6     \

0 n(lognK)

```

## Quick Serach - Amanitize O(n)
```
num     2  1  7  5  3  12   4

count   5  6  1  3  2   7   4

Chose 4 to be a pivot
Two pointers to be the boundry, larger than 5 will be on the right, less than 5 will be on the left


num     2  1  7  5  3  12   4

count   5  6  1  3  2   7   4
        *
        l                   r

count   5  4  1  3  2   7   6
           *
        l               r       

count   4  5  1  3  2   7   6
              *
           l            r      

count   4  1  5  3  2   7   6
                 *
              l         r          

count   4  1  3  5  2   7   6
                    *
                 l      r                    

count   4  1  3  2  5   7   6
                        *
                    l   r     

count   4  1  3  2  5   7   6

```

## Bucket Sort!!!!!!



***


## Solution - Bucket Sort


* At least use O(n) to go through the array.
```
class Num{
    int val;
    int count;
}
```
* If we need to sort the array, it should take O(nlogn).

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

## Heap 堆


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

