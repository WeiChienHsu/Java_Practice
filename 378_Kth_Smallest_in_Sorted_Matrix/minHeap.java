import java.util.Comparator;
import java.util.PriorityQueue;

public class smallestK {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
        };

        int k = 8;

        System.out.println("The "+k+"th Smallest number in the matrix is:  "+ kthSmallest(matrix, k));
    }

    public static int kthSmallest(int[][] matrix, int k) {
        if(matrix == null) return Integer.MIN_VALUE;
        int row = matrix.length;
        int col = matrix[0].length;

        boolean[][] marked = new boolean[row][col];
        PriorityQueue<Point> minHeap = new PriorityQueue<>(k, new myComparator());

        minHeap.offer(new Point(0, 0, matrix[0][0]));
        marked[0][0] = true;
        int res = Integer.MIN_VALUE;

        while(k > 0) {
            Point cur = minHeap.poll();
            int x = cur.x;
            int y = cur.y;

            if(x + 1 < row && !marked[x+1][y]) {
                minHeap.offer(new Point(x+1, y, matrix[x+1][y]));
                marked[x+1][y] = true;
            }

            if(y + 1 < col && !marked[x][y+1]) {
                minHeap.offer(new Point(x, y+1, matrix[x][y+1]));
                marked[x][y+1] = true;
            }

            res = cur.val;
            k--;
        }

        return res;
    }
}

class Point {
    int x;
    int y;
    int val;

    public Point(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

class myComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        if(o1.val < o2.val) {
            return -1;
        } else if(o1.val > o2.val) {
            return 1;
        } else {
            return 0;
        }
    }
}
