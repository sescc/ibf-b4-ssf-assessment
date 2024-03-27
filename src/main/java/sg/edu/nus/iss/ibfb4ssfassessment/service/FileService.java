package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repo.MovieRepo;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Utils;

@Service
public record FileService() {

    // Task 1
    public List<Movie> readFile(String fileName) throws IOException, ParseException {
        StringBuilder sboutput = new StringBuilder();
        File file = Paths.get(fileName).toFile();

        if (!file.exists()) {
            System.err.println("The file does not exist: " + fileName);
        } else if (file.isDirectory()) {
            System.err.println("This is not a file.");
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                
                String line = br.readLine();
        
                while (line != null) {
                    sboutput.append(line);
                    line = br.readLine();
                }                    
                
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }

        String jString = sboutput.toString();
        // TODO: removetest
        System.out.println(jString);

        List<Movie> movielist = new ArrayList<>();

        try (InputStream is = new ByteArrayInputStream(jString.getBytes())) {
			JsonReader reader = Json.createReader(is);
			JsonArray jArray = reader.readArray();
            MovieRepo movieRepo = new MovieRepo();
			
			for (int i = 0; i < jArray.size(); i++) {
				String jObjectString = jArray.getJsonObject(i).toString();
				Movie movie = movieRepo.convertJsonToMovieObject(jObjectString);
                movielist.add(movie);
			}
		} catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("ParseException: " + e.getMessage());
        }
        
        return movielist;

    }

}
