package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.City;
import ua.org.ecity.services.CityService;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    CityService cityService;

    @RequestMapping(value = "/game/move/set", method = RequestMethod.POST)
    public String set(
            @RequestParam("id") int id,
            @RequestParam("city") String city) {
        return "{\"id\":\"" + id + "\"," +
                "\"city\":\"" + city + "\"," +
                "\"massage\":\"City name is invalid\"}";
    }

    @RequestMapping(value = "/game/move/get", method = RequestMethod.POST)
    public List<City> get(@RequestParam("id") int id) {
        return cityService.getCitiesByName("Одесса");
    }

}
