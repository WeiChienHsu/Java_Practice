import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Movie m1 = new Movie("hey", "Comedy", 9);
        Movie m2 = new Movie("Man", "Comedy", 4);
        Movie m3 = new Movie("KKK", "Byte Club", 12);
        List<Movie> movieList = new ArrayList<>();
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.sort((o1, o2) -> o1.getRating() - o2.getRating());
        for(int i = 0 ; i < movieList.size(); i++){
            movieList.get(i).playMovie();
        }
    }
}
