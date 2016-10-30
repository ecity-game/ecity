package ua.com.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.ecity.entities.City;
import ua.com.ecity.repository.CityRepository;
import ua.com.ecity.services.CityService;

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

    @RequestMapping("/hello")
    public List<City> hello() {
        cityService.addCities();
        return cityService.getCitiesByName("Odessa");
    }

    @RequestMapping("/another")
    public String another() {
        return "Another one";
    }
}
