package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repo.MovieRepo;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Utils;

@Service
public class DatabaseService {

    @Autowired
    MovieRepo repo;

    @Autowired
    @Qualifier(Utils.REDIS_MAP)
    RedisTemplate<String, String> template;

    HashOperations<String, Integer, String> hashOps;

    // Task 2 (Save to Redis Map)
    public void saveRecord(Movie movie) {
        hashOps = template.opsForHash();
        hashOps.putIfAbsent(Utils.KEY_MOVIE, Integer.parseInt(movie.getMovieId().toString()), movie.toJsonString());
        // TODO: removetest
        //System.out.printf("Movie %s saved to Redis.", movie.getTitle());
    }

    // Task 3 (Map)
    // Changed the given "getNumberOfEvents()" method name to "getNumberOfMovies()" as what's mentioned in the question
    public long getNumberOfMovies() {
        hashOps = template.opsForHash();

        return hashOps.size(Utils.KEY_MOVIE);
    }

    public Movie getMovie(Integer index) {
        return repo.getMovie(index);
    }

    // Task 4 (Map)
    public Movie getMovieById(Integer movieId) throws ParseException {
        hashOps = template.opsForHash();
        String movieStr = hashOps.get(Utils.KEY_MOVIE, movieId);

        return repo.convertJsonToMovieObject(movieStr);
    }

    // Task 5
    public List<Movie> getAllMovies() {
        hashOps = template.opsForHash();
        List<Movie> movielist = new ArrayList<>();
        Map<Integer, String> movieMap = hashOps.entries(Utils.KEY_MOVIE);

        for (String movie: movieMap.values()){
            try {
                movielist.add(repo.convertJsonToMovieObject(movie));
            } catch (ParseException e) {
                System.out.println("ParseException: " + e.getMessage());
            }
        }

        return movielist;
    }
}
