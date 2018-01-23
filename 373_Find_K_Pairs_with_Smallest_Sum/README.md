# Find K Pairs with Smallest Sums
```
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
```

## 
- Change two array into a matrix and find the K smallest sun by going through the matrix
```
    2   4   6
1   3   5   7

7   9  11   13

11  13  15   17
```
- Used a minHeap and Hashset(to record the visited point)
- Used a class Sum
```java
class Sum{
  

  int a;
  int b;
  int sum;
  public Sum(int a, int b, int sum) {
    this.a = a;
    this.b = b;
    this.sum =  sum;
  }
}
```
- Try to Optimize this clss by transforing it into int[]
- 用 int[] 存入”座標“

```
int[first num, second sum]
int[] a = new int[1,0] (value(1,2)那個點是num1的第一個和num2的第一個)
-> sum(點1 + ) = nums1[a[0]] + nums2[a[1]]
```
```java
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int sum1 = nums1[o1[0]] + nums2[o1[1]];
                int sum2 = nums1[o2[0]] + nums2[o2[1]];
                if(sum1 == sum2) {
                    return 0;
                } else {
                    return sum1 < sum2? -1 : 1;
                }
            }});
```

- Try to Optimize the Hashset by:
- 1. Addin the first line (3,9,13) in the first column firstly
- 2. Only go right to add in the numbers into minHeap
- 3. In minHeap:
```

    3(1,2)  <- cur
    9(7,2)
    13(11,2)

-> pop out the peek and give the value to cur
-> add in 5(4,1)
    
    5(4,1)
    9(7,2)
    13(11,2)

```
```java
        for(int i = 0; i < nums1.length; i++) {
            minHeap.add(new int[]{i,0});
        }

        // Only Go right for next node
        while(!minHeap.isEmpty() && k > 0) {
            int[] cur = minHeap.poll();
            list.add(new int[] {nums1[cur[0]], nums2[cur[1]]});
            if(++cur[1] < nums2.length) {
                // Go Right
                minHeap.add(cur);
            }
            k--;
        }
        return list;
```