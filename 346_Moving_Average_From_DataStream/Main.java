import java.util.Deque;

public class MovingAverage {
    private Deque<Integer> queue;
    private int sum;
    private int size;

    /** Initialize data structure */
    public MovingAverage(int size) {
        this.queue = new ArrayDeque<>();
        this.sum = 0;
        this.size = size;
    }

    publid double next(int val) {
        if(queue.size() == size) {
            int last = queue.pullFirst();
            sum -= last;
        }

        queue.offerLast(val);
        sum += val;
        return sum * 1.0 / queue.size();
    }
}