# Candy
## There are N children standing in a line. Each child is assigned a rating value.

```
You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
```


## Solution
### Wrogn Solution -> I thought the first one is 1 
- I only considered one situation that starting from 1. But in the real world, it might be the smallest one on seond or third or etc. And then we could set the first one 1.

```java
class Solution {
    public int candy(int[] ratings) {
        int res = 1;
        int value = 1;
        for(int i = 0; i < ratings.length - 1; i++) {
            if(ratings[i + 1] > ratings[i]) {
                value ++;
                res += value;
            } else if (ratings[i + 1] < ratings[i]) {
                value = 1;
                res += value;
            } else {
                res += value;
            }
        }
        
        return res;
    }
}
```
## How to solve this issus?
- Scan from left to right and save the result into an array
- Use that array to get the max value by scanning from right to left again
* if the next [i+1] or [i-1] number is greater than i, value +1, small or equal than value = 1
* Used two different direction for loop to finish the comarision.

```
Kid    3   2   1   3   4

->     1   1   1   2   3

       3   2   1   1   1   <-

max    3   2   1   2   3  = 11
```      


- Get an array from left to right 

```
[ 3 2 1 2 3 ]
  1 1 1 2 3

```
```java 
        int len = ratings.length;
        int[] num = new int[len];
```

```java    
for(int i = 0; i < len; i++) {
    if(i > 0 && ratings[i - 1] < ratings[i])  {
        num[i] = num[i-1] + 1;
    } else {
        num[i] = 1;
    }
}
```
- Compare to the array, if the new number is greater than old one, add in sum
- Dont need to consider the last number, since it will always smaller or equal to the number in array
```
[ 3 2 1 2 3 ]
  3 2 1 1 1
 ->3 2 1 2 3 = 11
```

```java        
int sum = 0;   
for(int i = len - 1; i >= 0; i--) {
    if(i < len - 1 && ratings[i] > ratings[i+1] ) {                
        num[i] = Math.max( num[i+1] + 1 , num[i]);
    }         
    sum += num[i];       
} 
return sum;
}
}
```