public class binarySearch {

    int star = 0;
    int end = Integer_MAX_VALUE;

    while(start <= end) {
        int mid = start +(end-start)/2;
        if(arr[mid] == target) {
            System.out.println(mid);
            break;
        } else if (arr[mid] < target) {
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }

    while(start < end - 1) {
        int mid = start + (end - start) / 2;
        if(arr[mid] <= target) {
            start = mid;
        } else {
            end = mid;
        }

        if(arr[start] = target) {
            System.out.println(start);
        } else {
            return -1;
        }
    }
}
