# Single Number
Given an array of integers, every element appears twice except for one. Find that single one.

## Solution
- we use bitwise XOR to solve this problem :
```
first , we have to know the bitwise XOR in java

0 ^ N = N
N ^ N = 0
So… if N is the single number

N1 ^ N1 ^ N2 ^ N2 ^…^ Nx ^ Nx ^ N

= (N1^N1) ^ (N2^N2) ^…^ (Nx^Nx) ^ N

= 0 ^ 0 ^ …^ 0 ^ N

= N
```