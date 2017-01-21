package ua.org.ecity.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.Result;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public Result login() {
        return new Result(true);
    }

    @RequestMapping("/login-error")
    public String loginError() {
        return "error";
    }
}
