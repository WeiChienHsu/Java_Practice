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
        int res = Integer.MIN_VALUE;
        boolean[][] visited = new boolean[row][col];
        PriorityQueue<Point> minHeap = new PriorityQueue<>(k, new myComparator());

        minHeap.offer(new Point(0,0,matrix[0][0]));
        visited[0][0] = true;

        while(k > 0) {
            Point cur = minHeap.poll();
            int curX = cur.x;
            int curY = cur.y;
            if(curX + 1 < row && !visited[curX +1][curY]) {
                minHeap.offer(new Point(curX+1, curY, matrix[curX+1][curY]));
                visited[curX+1][curY] = true;
            }

            if(curY + 1 < col && !visited[curX][curY+1]) {
                minHeap.offer(new Point(curX, curY+1, matrix[curX][curY+1]));
                visited[curX][curY+1] = true;
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

    public Point(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

class myComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        if(p1.val == p2.val) return 0;
        return p1.val - p2.val;
    }
}

