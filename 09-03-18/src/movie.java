import java.util.Arrays;
import java.util.Comparator;

public class movie {
    public static void main(String[] args) {
        int[] durations = new int[]{20, 40, 90, 80, 70, 50}; /* 2, 0*/
        int flightDuration = 140;
        int[] pairs = getMovieIndices(durations, flightDuration);

        for(int num : pairs)
            System.out.print(num + " ");
    }

    public static int[] getMovieIndices(int[] durations, int flightDuration) {
        TwoMovies[] twoMovies = createTwoMoviesArray(durations);

        /* Sort the array in decrement order */
        Arrays.sort(twoMovies, new Comparator<TwoMovies>() {
            @Override
            public int compare(TwoMovies o1, TwoMovies o2) {
                return o2.movieDuration - o1.movieDuration;
            }
        });

        int largestSum = Integer.MIN_VALUE;
        int[] result = new int[]{-1, -1};

        int left = 0;
        int right = twoMovies.length - 1;

        /* Two Pointers method to find the closest index of value in the sorted decrement array */
        /* 90 80 70 50 40 20 */
        while(left < right) {
            int sum = twoMovies[left].movieDuration + twoMovies[right].movieDuration;
            if(sum > flightDuration - 30) {
                /* Invalid : Need to decrease the sum Duration */
                left++;
            } else if(sum < flightDuration - 30) {
                if(sum > largestSum) {
                    largestSum = sum; /* Update the current largest sum Duration */
                    result = new int[]{twoMovies[left].index, twoMovies[right].index};
                }
                /* Try a larger number to get a largest valid sum */
                right--;
            } else {
                /* No need to consider other pair which would have same value as flightDuration - 30 */
                /* Since we just need to get the one with the longest duration  */
                return new int[]{twoMovies[right].index, twoMovies[left].index};
            }
        }
        return result;

    }

    public static TwoMovies[] createTwoMoviesArray(int[] durations) {
        TwoMovies[] twoMovies = new TwoMovies[durations.length];
        for(int i = 0; i < durations.length; i++) {
            twoMovies[i] = new TwoMovies(i, durations[i]);
        }
        return twoMovies;
    }
}


class TwoMovies {
    int index;
    int movieDuration;

    public TwoMovies(int index, int movieDuration) {
        this.index = index;
        this.movieDuration = movieDuration;
    }
}