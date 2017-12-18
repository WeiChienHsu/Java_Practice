class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int start = 0, end = height.length - 1;
        while (start < end) {
            // dist(start, end) * min(start, end)
            max = Math.max(max, (end - start) * Math.min(height[end], height[start]) );
            if (height[start] > height[end]) {
                end --;
            } else {
                start ++;
            }
        }
        return max;
    }
}