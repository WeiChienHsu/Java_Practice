import java.util.ArrayDeque;
import java.util.Deque;

public class RoundRobin {
    public static void main(String[] args) {
        int[] arrTime = new int[]{0,1,3,5,6};
        int[] exeTime = new int[]{5,3,6,1,4};
        int quanTime = 3;

        float averageWaitingTime = countAveWaitTime(arrTime, exeTime, quanTime);
        System.out.println(averageWaitingTime);
    }

    public static float countAveWaitTime(int[] arrTime, int[] exeTime, int q) {
        // Init current Time, waiting Time and processor pointer
        if(arrTime == null || exeTime == null || arrTime.length != exeTime.length) return 0;

        int curTime = 0, waitTime = 0, index = 0;
        int length = arrTime.length;
        Deque<process> queue = new ArrayDeque<>();

        // While there are some processes in the queue
        // haven't been handled or the processes have never been used
        while(!queue.isEmpty() || index < length) {
            // There are still have some processes in the Queue
            if(!queue.isEmpty()) {
                process curProcess = queue.pollFirst();
                // Update the waiting Time when we are starting using a process
                waitTime += curTime - curProcess.arrTime;
                // Avoid the execution Time exceed the quan Time limitation
                curTime += Math.min(curProcess.exeTime, q);

                // Put those rest of processes which's arrival time has already exceed current Time
                for(; index < length && arrTime[index] <= curTime; index++) {
                    queue.offerLast(new process(arrTime[index], exeTime[index]));
                }
                // If the current processor didn't run to the end, put it back
                // into Queue with new arrTime and exeTime
                if (curProcess.exeTime > q) {
                    queue.offerLast(new process(curTime, curProcess.exeTime - q));
                }
            }
            // There is no process in the Queue but the index doesn't not point to the end
            else {
                queue.offerLast(new process(arrTime[index], exeTime[index]));
                // Update only the current Time, no need to change the total waiting time
                curTime = arrTime[index];
                // Point to the next process
                index++;
            }
        }
        return (float) waitTime / length;
    }
}

class process {
    int arrTime;
    int exeTime;
    process(int arr, int exe) {
        this.arrTime = arr;
        this.exeTime = exe;
    }
}