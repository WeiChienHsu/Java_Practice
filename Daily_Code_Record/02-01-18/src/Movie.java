public class Movie {
    String title;
    String grnre;
    int rating;

    public Movie(String title, String grnre, int rating) {
        this.title = title;
        this.grnre = grnre;
        this.rating = rating;
    }

    public void playMovie() {
        System.out.println(this.title +" ( "+ this.rating+ " ): is playing");
    }


    public String getTitle() {
        return title;
    }

    public int getRating() {
        return rating;
    }
}
