# Search for a Range
```
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
```

## Solution
#### Find the First target:
- start + 1 < end
- if(nums[mid] == target) { end = mid  } : want to see if there is another target in front
- if(nums[start] == target) reutn start
- if(nums[end] == end) return end

#### Find the Last Target
- start + 1 < end
- if(nums[mid] == target) { start = mid  } : want to see if there is another target next
- if(nums[end] == end) return end
- if(nums[start] == target) reutn start
