# Binary Search

## You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

```
You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
```

## Binary Search
```
Bad Version = 5 6 7

1  2  3  4  5  6  7

1  2  3  4  5  6  7
s         *       e
isBadVersion(4) => false
------> check the right and delete left side my moving pointer
s  =  mid

1  2  3  4  5  6  7
x  x  x  s  *     e

isBadVersion(5) => true
-------> which means the number less then 5 could be bad version, move the end point to 5


1  2  3  4  5  6  7
x  x  x  s  e  x  x

----> start = end -1
check isBadVersion(start)? start: end

```
- Need to know if we need to go right or left
- Avoid a died circle, need to use (end - 1), when it's the end, start will near by end but not equal.
- In the end, check again from start since we didn't scan start and end points.
```java
int start = 1;
int end = n;
while(start < end - 1) {
    int mid = start + (end - start) / 2;
    if(!isBadVersion(mid)) {
        start = mid;
    } else {
        end = mid;
    }
}
return isBadVersion(start)? start: end;    
```

```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while(end > start) {
            int mid = start + (end - start) / 2;
            if(isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return isBadVersion(start)? start:end;
    }
}
```