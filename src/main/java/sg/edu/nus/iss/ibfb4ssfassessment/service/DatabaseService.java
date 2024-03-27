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

    // TODO: Task 2 (Save to Redis Map)
    public void saveRecord(Movie movie) {
        HashOperations<String, Integer, String> hashOps = template.opsForHash();
        hashOps.put(Utils.KEY_MOVIE, Integer.parseInt(movie.getMovieId().toString()), movie.toJsonString());

    }

    // TODO: Task 3 (Map or List - comment where necesary)
    public long getNumberOfEvents() {

        return 0L;
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
