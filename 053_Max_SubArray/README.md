# Maximum Subarray

```
For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
```

# Solution - Record MaxSoFar and MaxEndHere 
- Used nextNum and MaxEndHere + nextNum to compare, choose either start from next number or keep counting
- location -> choose num (num,num) choose MaxEndHere + num (_ , num++)
```
                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num               -2*  
MaxEndHere + num  
MaxEndHere        -2
MaxSoFar          -2
location         (0,0)

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                    1*  
MaxEndHere + num      -1
MaxEndHere        -2   1
MaxSoFar          -2   1
location              (1,1)

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                        -3  
MaxEndHere + num           -2*
MaxEndHere        -2   1   -2
MaxSoFar          -2   1    1
location                   (1,2)

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                             4*  
MaxEndHere + num                2
MaxEndHere        -2   1   -2   4
MaxSoFar          -2   1    1   4
location                       (3,3)

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                                 -1  
MaxEndHere + num                     3*
MaxEndHere        -2   1   -2   4    3
MaxSoFar          -2   1    1   4    4
location                            (3,4)

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                                      2  
MaxEndHere + num                         5*
MaxEndHere        -2   1   -2   4    3   5
MaxSoFar          -2   1    1   4    4   5
location                               (3,5)

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                                          1  
MaxEndHere + num                             6*
MaxEndHere        -2   1   -2   4    3   5   6
MaxSoFar          -2   1    1   4    4   5   6
location                                    (3,6)*

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                                              -5   
MaxEndHere + num                                 -1*
MaxEndHere        -2   1   -2   4    3   5   6   -1
MaxSoFar          -2   1    1   4    4   5   6    6
location                                         (7,7)

                  -2 , 1 , -3 , 4 , -1 , 2 , 1 , -5 , 4
num                                                   4*   
MaxEndHere + num                                      3
MaxEndHere        -2   1   -2   4    3   5   6   -1   4
MaxSoFar          -2   1    1   4    4   5   6    6   6
location                                         (7,7)
```
- output: 6
- output: nums[3:6]