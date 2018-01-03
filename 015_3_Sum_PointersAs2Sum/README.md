# 3 Sum
## Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

```
For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```

## Solution O(n^2)
- Like 2 Sum, used the pointers to solve the problem
- Sum > target -> hi move left
- Sum < target -> lo move right
```
4 6 10 12 15
|
  |lo     |hi
```

- Conditions:
* Duplicate? Need to skip the same numbers when using pointer
```java
Skip array[i] == array[i-1]
```
* Sort
```java
Arrays.sort(num)
```
- Choose one number by for loop and give a remian number (target number)

```java
List<List<Integer>> res = new LinkedList<>(); 
```

- Pointers
* Set up a pointer
```java
for (int i = 0; i < num.length-2; i++) {
        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
            int lo = i+1, hi = num.length-1, sum = 0 - num[i];
```


* if Sum = target, then we need to see if the next pointer is the same? After checking it by while condition, we move both pointers to the next position.

* If Sum > target -> lo++
* If sum < target -> hi--

```java
while (lo < hi) {
    if (num[lo] + num[hi] == sum) {
        res.add(Arrays.asList(num[i], num[lo], num[hi]));
        while (lo < hi && num[lo] == num[lo+1]) lo++;
        while (lo < hi && num[hi] == num[hi-1]) hi--;
        lo++; hi--;
    } else if (num[lo] + num[hi] < sum) lo++;
    else hi--;
}
```
