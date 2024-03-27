package sg.edu.nus.iss.ibfb4ssfassessment.repo;

import java.io.StringReader;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Utils;

@Repository
public class MovieRepo {

    // @Autowired
    // @Qualifier(Utils.REDIS_MAP)
    // RedisTemplate<String, String> template;

    public Movie convertJsonToMovieObject(String jString) throws ParseException {
        JsonObject jsonObject = Json.createReader(new StringReader(jString)).readObject();
        
        Integer movieId = jsonObject.getJsonNumber("Id").intValue();
        String title = jsonObject.getString("Title");
        String year = jsonObject.getString("Year");
        String rated = jsonObject.getString("Rated");
        Long releaseDate = jsonObject.getJsonNumber("Released").longValue();
        String runTime = jsonObject.getString("Runtime");
        String genre = jsonObject.getString("Genre");
        String director = jsonObject.getString("Director");
        Double rating = jsonObject.getJsonNumber("Rating").doubleValue();
        Integer count = jsonObject.getJsonNumber("Count").intValue();

        return new Movie(movieId, title, year, rated, releaseDate, runTime, genre, director, rating, count);

    }

    public Movie getMovie(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMovie'");
    }
    
}