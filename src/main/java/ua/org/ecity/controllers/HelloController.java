package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.org.ecity.entities.City;
import ua.org.ecity.entities.Name;
import ua.org.ecity.services.CityService;

import java.util.ArrayList;
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
    public
    @ResponseBody
    List<City> city(@RequestParam(value = "name") String name) {
        return cityService.getCitiesByName(name);
    }

    @RequestMapping("/city/{id}")
    public City cityInfo(@PathVariable("id") int id) {
        return cityService.getCityByID(id);
    }

    @RequestMapping("/cities")
    public
    @ResponseBody
    List<City> cities() {
        return cityService.getCities();
    }


    @RequestMapping("/names")
    public
    @ResponseBody
    List<Name> names() {
        List<City> cities = cityService.getCities();
        List<Name> names = new ArrayList<>();
        for (City city : cities) {
            Name name = new Name();
            name.setId(city.getId());
            name.setName(city.getName());
            names.add(name);
        }
        return names;
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
