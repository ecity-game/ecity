package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/admin/city/delete/{id}")
    public String deleteCity(@PathVariable("id") Integer id) {

        cityService.deleteCity(id);
        return "successfully";
    }

    @RequestMapping(value = "/admin/city/add", method = RequestMethod.POST)
    //@ResponseBody
    public String addCity(@RequestParam("city") String city) {


        parse(city);
        return city;
    }

    private void parse (String string) {
        City city = new City();
        String url = string;
        String[] str = string.split(",");
        for(String temp : str) {
            str = temp.split(":");
        }
        city.setName("name");
        city.setLongitude(1);
        city.setLatitude(1);
        city.setPopulation(1);
        city.setEstablishment(null);
        city.setUrl(url);
        cityService.saveCity(city);

    }
}
