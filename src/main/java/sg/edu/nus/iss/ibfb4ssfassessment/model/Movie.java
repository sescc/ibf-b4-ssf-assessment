package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Movie implements Serializable {
    
    private Integer movieId;
    private String title;
    private String year;
    private String rated;
    private Long releaseDate;   // epoch milliseconds
    private String runTime;
    private String genre;
    private String director;
    private Double rating;
    private Date formattedReleaseDate;
    private Integer count;
    
    public Movie() {
        this.movieId = Integer.parseInt(UUID.randomUUID().toString());
    }

    public Movie(Integer movieId, String title, String year, String rated, Long releaseDate, String runTime,
            String genre, String director, Double rating, Integer count) throws ParseException {
        this.movieId = movieId;
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.releaseDate = releaseDate;
        this.runTime = runTime;
        this.genre = genre;
        this.director = director;
        this.rating = rating;
        this.count = count;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.formattedReleaseDate = sdf.parse(sdf.format(releaseDate));
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getFormattedReleaseDate() {
        return formattedReleaseDate;
    }

    public void setFormattedReleaseDate(Date formattedReleaseDate) {
        this.formattedReleaseDate = formattedReleaseDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String toJsonString() {

        JsonObjectBuilder movieBuilder = Json.createObjectBuilder()
                .add("id", movieId)
                .add("title", title)
                .add("year", year)
                .add("rated", rated)
                .add("releaseDate", releaseDate)
                .add("runtime", runTime)
                .add("genre", genre)
                .add("director", director)
                .add("rating", rating)
                .add("formattedReleaseDate", formattedReleaseDate.getTime())
                .add("count", count);
                                
        JsonObject movieObj = movieBuilder.build(); 

        return movieObj.toString();

    }

}
// TODO: remove
// "Id": 12333,
// "Title": "Avatar",
// "Year": "2009",
// "Rated": "PG-13",
// "Released": 1261094400000,
// "Runtime": "162 min",
// "Genre": "Action, Adventure, Fantasy",
// "Director": "James Cameron",
// "Rating": 7.9,
// "Count": 0

// private Integer movieId;
// private String title;
// private String year;
// private String rated;
// private Long releaseDate;
// private String runTime;
// private String genre;
// private String director;
// private Double rating;
// private Date formattedReleaseDate;
// private Integer count;