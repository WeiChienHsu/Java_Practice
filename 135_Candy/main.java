class Solution {
    public int candy(int[] ratings) {
        if(ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int len = ratings.length;
        int[] num = new int[len];
        
        // Get an array from left to right 
        // [ 3 2 1 2 3 ]
        //   1 1 1 2 3
        //**
        
        for(int i = 0; i < len; i++) {
            if(i > 0 && ratings[i - 1] < ratings[i])  {
                num[i] = num[i-1] + 1;
            } else {
                num[i] = 1;
            }
        }
        
        // Compare to the array, if the new number is greater than old one, add in sum
        // Dont need to consider the last number, since it will always smaller or equal to the number in array
        // [ 3 2 1 2 3 ]
        //   3 2 1 1 1
        // ->3 2 1 2 3 = 11
        //**
        
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