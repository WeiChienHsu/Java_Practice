# Minimum Height Trees


## Solution
The actual implementation is similar to the BFS topological sort. Remove the leaves(aka degree = 1), update the degrees of inner vertexes. Then remove the new leaves. Doing so level by level until there are 2 or 1 nodes left. What’s left is our answer!

- The time complexity and space complexity are both O(n).

