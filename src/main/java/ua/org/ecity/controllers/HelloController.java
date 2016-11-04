package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.org.ecity.entities.City;
import ua.org.ecity.services.CityService;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    CityService cityService;

    /*
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot";
    }
    */

    @RequestMapping("/city")
    public @ResponseBody List<City> city(@RequestParam(value = "name") String name) {
        return cityService.getCitiesByName(name);
    }

    @RequestMapping("/user/hello")
    public List<City> hello() {
        return cityService.getCitiesByName("Одесса");
    }


    @RequestMapping("/another")
    public String another() {
        return "Another one";
    }
}
