package com.example.it_market.web_or_presentation.controller;

import com.example.it_market.service_or_business.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final AuthService authService;

    public SignUpController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String signUpUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String repeatedPassword,
                             @RequestParam String mob_number,
                             @RequestParam String street,
                             @RequestParam String city) {
        try {
            this.authService.signUpUser(username,password,repeatedPassword, mob_number, street, city);
            return "redirect:/login?info=SuccessfulRegistration!";
        } catch (RuntimeException ex) {
            return "redirect:/signup?error=" + ex.getLocalizedMessage();
        }
    }
}
