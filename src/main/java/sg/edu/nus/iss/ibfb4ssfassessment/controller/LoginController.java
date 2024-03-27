package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {
    


    // TODO: Task 6
    @GetMapping(path={"/", "/index.html"})
    public String login(Model model) {
        model.addAttribute("login", new Login());

        return "view0";
    }

    // TODO: Task 7
    @PostMapping("/login")
    public String processlogin(HttpSession sess, Model model,
            @ModelAttribute("login") @Valid Login login, BindingResult bindings) {
                
        if (bindings.hasErrors()) {
            model.addAttribute("login", login);
            System.out.println("Global error: " + bindings.getGlobalErrors());
            System.out.println("Field error:" + bindings.getFieldErrors());
            
            return "view0";

        } else {
            //Login newLogin = todoService.createTodoWithoutDate(todo);
            //model.addAttribute("login", TodoWithId);
            //sess.setAttribute("user", login);
    
            return "view1";
        }
        
    }
    

    // For the logout button shown on View 2
    // On logout, session should be cleared
    public String logout() {

        // sess.invalidate();
        return "";
    }
    
}
