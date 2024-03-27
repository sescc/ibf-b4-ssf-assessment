package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Movie {
    
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
        this.movieId = Integer.parseInt(UUID.randomUUID().toString());
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

}
