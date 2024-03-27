package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;

@Controller
@RequestMapping
public class MovieController {

    @Autowired
    DatabaseService databaseService;

    // TODO: Task 8
    @GetMapping("/movielisting")
    public String displayMovies(Model model, HttpSession sess, HttpServletResponse response) {
        if(null != sess.getAttribute("email") && null != sess.getAttribute("birthDate")) {
            List<Movie> movielist = databaseService.getAllMovies();
            model.addAttribute("movies", movielist);

            return "view2";
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "redirect:/view0";
        }
        
    }

    // TODO: Task 9
    @GetMapping(path = "/book/{id}")
    public String bookMovie(@PathVariable("id") Integer id, 
            Model model, HttpSession sess, 
            Movie movie, Login login) {
        
        LocalDate localBirthDate = login.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period p = Period.between(localBirthDate, LocalDate.now());
        int age = p.getYears();

        if ("PG-13".equals(movie.getRated()) && age < 13) {
            return "BookError";
        }
        if ("R".equals(movie.getRated()) && age < 18) {
            return "BookError";
        }

        model.addAttribute("title", movie.getTitle());
        model.addAttribute("count", movie.getCount() + 1);

        return "BookSuccess";
    }

    // TODO: Task 9
    // @GetMapping("/booksuccess")
    // public String success(Model model, HttpSession sess, Movie movie) {
    //     model.addAttribute("title", movie.getTitle());

    //     return "BookSuccess";
    // }

}
