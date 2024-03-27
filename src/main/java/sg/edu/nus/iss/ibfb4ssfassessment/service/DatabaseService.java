package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.util.List;

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
    RedisTemplate <String, String> template;

    // Task 2 (Save to Redis Map)
    public void saveRecord(Movie movie) {
        HashOperations<String, Integer, String> hashOps = template.opsForHash();
        hashOps.put(Utils.KEY_MOVIE, Integer.parseInt(movie.getMovieId().toString()), movie.toJsonString());
        // TODO: removetest
        System.out.printf("Movie %s saved to Redis.", movie.getTitle());
    }

    // TODO: Task 3 (Map)
    // Note to marker: I changed this given "getNumberOfEvents()" method name to "getNumberOfMovies()" as the latter was what's mentioned in the question
    public long getNumberOfMovies() {
        HashOperations<String, Integer, String> hashOps = template.opsForHash();

        return hashOps.size(Utils.KEY_MOVIE);
    }

    public Movie getMovie(Integer index) {
        return repo.getMovie(index);
    }

    // TODO: Task 4 (Map)
    public Movie getMovieById(Integer movieId) {

        return null;
    }

    // TODO: Task 5
    public List<Movie> getAllMovies() {

        return null;
    }
}
