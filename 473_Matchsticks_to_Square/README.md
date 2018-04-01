 # Matchsticks_to_Square
 

## Solution

### Partition Problems
the partition problem (or number partitioning) is the task of deciding whether a given multiset S of positive integers can be partitioned into two subsets S1 and S2 such that the sum of the numbers in S1 equals the sum of the numbers in S2. 
- The partition problem is NP-complete.

### Without using DP- (instead DFS)
When I trying to think how to apply dynamic programming solution of above problem to this one (difference is divid S into 4 subsets), I took another look at the constraints of the problem:

- The length sum of the given matchsticks is in the range of 0 to 10^9.
- The length of the given matchstick array will not exceed 15.
- Sounds like the input will not be very large… Then why not just do DFS? In fact, DFS solution passed judges.

