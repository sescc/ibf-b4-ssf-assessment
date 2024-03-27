package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Login implements Serializable {

    @NotNull(message = "Please enter a email address.")
    @Email(message = "Invalid email format.")
    @Size(max = 50, message = "Maximum length of 50 characters only.")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please enter a birthday.")
    @Past(message = "Birthday must be in the past.")
    private Date birthDate;

    public Login() {
    }

    public Login(String email, Date birthDate) {
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
}
