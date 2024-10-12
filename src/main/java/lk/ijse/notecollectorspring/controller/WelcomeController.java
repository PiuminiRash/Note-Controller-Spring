package lk.ijse.notecollectorspring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/welcome")
public class WelcomeController {
    @GetMapping
    public String viewWelcomeScreen(Model model) {
        model.addAttribute("messages", "Welcome to Note Collector!");
        return "welcome";
    }
}
