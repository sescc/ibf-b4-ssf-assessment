package sg.edu.nus.iss.ibfb4ssfassessment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repo.MovieRepo;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;
import sg.edu.nus.iss.ibfb4ssfassessment.service.FileService;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Utils;

// TODO: Put in the necessary code as described in Task 1 & Task 2
@SpringBootApplication
public class IbfB4SsfAssessmentApplication implements CommandLineRunner {

	// @Autowired
	// DatabaseService databaseService;

	public static void main(String[] args) {
		SpringApplication.run(IbfB4SsfAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FileService fs = new FileService();
		//MovieRepo movieRepo = new MovieRepo();
		DatabaseService databaseService = new DatabaseService();

		// Task 01
		// System.out.println(fs.readFile(Utils.MOVIE_JSON).toString());
		List<Movie> movielist = fs.readFile(Utils.MOVIE_JSON);
		System.out.println(movielist.toString());

		// Task 02
		for (Movie movie: movielist) {
			databaseService.saveRecord(movie);
		}
		
	}

}
