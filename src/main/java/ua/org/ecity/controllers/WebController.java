package ua.org.ecity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {

    @GetMapping
    public String index(Model model) {
        return "redirect:app/index.html";
    }

    @GetMapping
    @RequestMapping("/manager")
    public String manager(Model model) {
        return "redirect:manager/admin-panel.html";
    }
}
