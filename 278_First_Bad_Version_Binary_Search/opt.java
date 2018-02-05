/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

      public class Solution extends VersionControl {
        public int firstBadVersion(int n) {
            int start = 1;
            int end = n;
            while(start + 1 < end) {
                int mid = start + (end - start) / 2;
                if(isBadVersion(mid)) { //Know if left side still have bad versions
                    end = mid;
                } else {
                    start = mid;
                }
            }
            
            if(isBadVersion(start)) {
                return start;
            }
            
            if(isBadVersion(end)) {
                return end;
            }
            
            return n;   
        }
    }