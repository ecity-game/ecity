package ua.org.ecity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping("/admin/city/delete/{id}")
    public String deleteCity(@PathVariable("id") Integer id) {

        cityService.deleteCity(id);
        return "successfully";
    }



    @RequestMapping(value = "/admin/city/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editCity(@RequestParam int id, @RequestParam String name, @RequestParam int longitude,
                          @RequestParam int latitude, @RequestParam int population, @RequestParam Date establishment,
                          @RequestParam String url) {

        City city = cityService.getCityByID(id);

        city.setName(name);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishment);
        city.setUrl(url);

        cityService.saveCity(city);

        return  name + "City has been added!!";
    }

    @RequestMapping(value = "/admin/city/add", method = RequestMethod.POST)
    @ResponseBody
    public String addCity(@RequestParam String name, @RequestParam int longitude,
                          @RequestParam int latitude, @RequestParam int population, @RequestParam Date establishment,
                          @RequestParam String url) {

        City city = new City();

        city.setName(name);
        city.setLongitude(longitude);
        city.setLatitude(latitude);
        city.setPopulation(population);
        city.setEstablishment(establishment);
        city.setUrl(url);

        cityService.saveCity(city);

        return  name + "City has been changed!!";
    }

}
