package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.org.ecity.entities.City;
import ua.org.ecity.services.CityService;

import java.util.Date;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    CityService cityService;

    @RequestMapping("/admin/cities")
    public
    @ResponseBody
    List<City> cities() {
        return cityService.getCities();
    }
/*
    @RequestMapping("/admin/city")
    public void addCity(@RequestParam("name") String name, @RequestParam("longitude") int longitude,
                        @RequestParam("latitude") int latitude, @RequestParam("population") int population,
                        @RequestParam("establishment") Date establishment, @RequestParam("url") String url) {

        city.setId(2222);
        city.setName(name);
        city.setLatitude(latitude);
        city.setLongitude(longitude);
        city.setPopulation(population);
        city.setEstablishment(establishment);
        city.setUrl(url);

        cityService.addCity(city);
    }
    */

}
