public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if(n <= 0) {
            return Integer.MIN_VALUE;
        }        
        
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
    }

}