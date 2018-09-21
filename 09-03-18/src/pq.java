import java.util.PriorityQueue;

public class pq {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(2);
        int[] nums = new int[]{8, 4, 5, 2};
        for(int num : nums) queue.add(num);
        System.out.println(queue.size());

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
