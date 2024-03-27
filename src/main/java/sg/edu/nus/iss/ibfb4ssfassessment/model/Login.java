package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Login {

    @NotNull(message = "Please enter a email address.")
    @Email(message = "Invalid email format.")
    @Size(max = 50, message = "Maximum length of 50 characters only.")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter a birthday.")
    @Past(message = "Birthday must be in the past.")
    private Date birthDate;
    
}
