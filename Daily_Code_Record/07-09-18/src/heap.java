import java.util.Comparator;
import java.util.PriorityQueue;

public class heap {

    public static void main(String[] args) {
        Movie m1 = new Movie(100);
        Movie m2 = new Movie(30);
        Movie m3 = new Movie(70);
        Movie m4 = new Movie(400);
        Movie m5 = new Movie(60);

        movieHeap heap = new movieHeap();
        heap.pushMovie(m1);
        heap.pushMovie(m2);
        heap.pushMovie(m3);
        heap.pushMovie(m4);
        heap.pushMovie(m5);

        while(!heap.isEmpty()) {
            heap.pop();
        }

    }


}

class movieHeap {
    PriorityQueue<Movie> maxHeap;

    public movieHeap() {
        maxHeap = new PriorityQueue<>(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.score - o1.score;
            }
        });
    }

    public void pushMovie(Movie movie) {
        maxHeap.add(movie);
    }

    public Movie pop() {
        System.out.println("Movie score: " + maxHeap.peek().score);
        return maxHeap.poll();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

}

class Movie {
    int score;
    public Movie(int score) {
        this.score = score;
    }
}
