package ua.com.ecity.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login-error")
    public String loginError() {
        return "error";
    }
}
