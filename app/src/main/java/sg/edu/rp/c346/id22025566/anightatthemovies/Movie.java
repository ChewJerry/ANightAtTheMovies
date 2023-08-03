package sg.edu.rp.c346.id22025566.anightatthemovies;



import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String rating;

    public Movie() {
        // Empty constructor required for SQLite operations
    }

    public Movie(String title, String genre, int releaseYear, String rating) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }




}
