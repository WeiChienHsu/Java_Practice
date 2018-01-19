import java.util.Comparator;
import java.util.PriorityQueue;

public class minHeap {
    public static void main(String[] args) {
        int[] nums = {5,3,2,1,6,10,33,5};
        int k = nums.length;

        PriorityQueue<Integer> heap = new PriorityQueue<>(k, new MyComparator());

        for(int num : nums) {
            heap.add(num);
        }

        for(int i = 0; i < k; i++) {
            System.out.println(heap.poll());
        }

    }

}

class MyComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer a, Integer b) {
        if(a == b) return 0;
        else return a - b;
    }
}
