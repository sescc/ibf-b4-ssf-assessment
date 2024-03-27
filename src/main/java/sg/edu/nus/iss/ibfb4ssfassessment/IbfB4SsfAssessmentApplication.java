package sg.edu.nus.iss.ibfb4ssfassessment;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

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

	public static void main(String[] args) {
		SpringApplication.run(IbfB4SsfAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		FileService fs = new FileService();
		MovieRepo movieRepo = new MovieRepo();
		DatabaseService databaseSvc = new DatabaseService();

		List<Movie> movielist = fs.readFile(Utils.MOVIE_JSON);

		// try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
		// 	JsonReader reader = Json.createReader(is);
		// 	JsonArray jArray = reader.readArray();
			
		// 	for(int i = 0; i < jArray.size(); i++){
		// 		String jsonObject = jArray.getJsonObject(i).toString();				
		// 		Movie movie = movieRepo.convertJsonToMovieObject(jsonObject);				
		// 		databaseSvc.saveRecord(movie);
		// 	}
		// } 
		
		System.out.println("Data saved to Redis successfully.");
	}

}
